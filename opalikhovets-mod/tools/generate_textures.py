#!/usr/bin/env python3
"""Generates the Opalikhovets textures (pure Python, no dependencies).

Outputs:
  src/main/resources/assets/opalikhovets/textures/entity/opalikhovets/opalikhovets_<n>.png  (64x64, one per outfit)
  src/main/resources/assets/opalikhovets/textures/item/phone.png                             (16x16)
  docs/preview.png  (front / side / back orthographic preview of every outfit)

The UV layout must match OpalikhovetsModel#createBodyLayer:
  head 8x10x8 @0,0 | nose 2x3x1 @24,0 | hair 8x10x8 @32,0 | body 8x12x4 @0,18 | jacket 8x12x4 @24,18
  arm 4x12x4 @48,18 | sleeve 4x12x4 @48,34 | leg 3x12x3 @0,34 | shoe 4x3x7 @12,34

Run: python3 tools/generate_textures.py
"""

import os
import random
import struct
import zlib

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
ASSETS = os.path.join(ROOT, "src", "main", "resources", "assets", "opalikhovets", "textures")

# ---------------------------------------------------------------------------
# PNG I/O
# ---------------------------------------------------------------------------


def write_png(path, width, height, pixels):
    """pixels: list of rows, each a list of (r, g, b, a)."""
    raw = bytearray()
    for row in pixels:
        raw.append(0)  # filter: none
        for r, g, b, a in row:
            raw += bytes((r, g, b, a))

    def chunk(tag, data):
        body = tag + data
        return struct.pack(">I", len(data)) + body + struct.pack(">I", zlib.crc32(body) & 0xFFFFFFFF)

    png = b"\x89PNG\r\n\x1a\n"
    png += chunk(b"IHDR", struct.pack(">IIBBBBB", width, height, 8, 6, 0, 0, 0))
    png += chunk(b"IDAT", zlib.compress(bytes(raw), 9))
    png += chunk(b"IEND", b"")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "wb") as f:
        f.write(png)


class Canvas:
    def __init__(self, width, height, fill=(0, 0, 0, 0)):
        self.w = width
        self.h = height
        self.px = [[fill for _ in range(width)] for _ in range(height)]

    def set(self, x, y, color):
        if color is None:
            return
        if len(color) == 3:
            color = (color[0], color[1], color[2], 255)
        if 0 <= x < self.w and 0 <= y < self.h:
            self.px[y][x] = tuple(max(0, min(255, int(c))) for c in color)

    def get(self, x, y):
        return self.px[y][x]

    def save(self, path):
        write_png(path, self.w, self.h, self.px)


# ---------------------------------------------------------------------------
# Box UV helpers (vanilla ModelPart.Cube layout)
# ---------------------------------------------------------------------------


def box_faces(u, v, w, h, d):
    """Texture rectangles (x, y, width, height) of every face of a box.

    top/bottom: column 0 = entity's right (-X), last row = front.
    right (-X): column 0 = back, last column = front.  left (+X): column 0 = front.
    front: column 0 = entity's right.  back: column 0 = entity's left.
    """
    return {
        "top": (u + d, v, w, d),
        "bottom": (u + d + w, v, w, d),
        "right": (u, v + d, d, h),
        "front": (u + d, v + d, w, h),
        "left": (u + d + w, v + d, d, h),
        "back": (u + d + w + d, v + d, w, h),
    }


HEAD = box_faces(0, 0, 8, 10, 8)
NOSE = box_faces(24, 0, 2, 3, 1)
HAT = box_faces(32, 0, 8, 10, 8)
BODY = box_faces(0, 18, 8, 12, 4)
JACKET = box_faces(24, 18, 8, 12, 4)
ARM = box_faces(48, 18, 4, 12, 4)
SLEEVE = box_faces(48, 34, 4, 12, 4)
LEG = box_faces(0, 34, 3, 12, 3)
SHOE = box_faces(12, 34, 4, 3, 7)


def paint(canvas, rect, fn):
    """Calls fn(col, row, width, height) for every pixel of the face and stores the returned color."""
    x0, y0, w, h = rect
    for row in range(h):
        for col in range(w):
            canvas.set(x0 + col, y0 + row, fn(col, row, w, h))


# ---------------------------------------------------------------------------
# Colors
# ---------------------------------------------------------------------------


def jitter(color, rng, amount):
    if color is None:
        return None
    d = rng.randint(-amount, amount)
    return tuple(c + d for c in color[:3])


def mix(a, b, t):
    return tuple(a[i] * (1 - t) + b[i] * t for i in range(3))


SKIN = (226, 176, 138)
SKIN_SHADE = (208, 157, 120)
SKIN_DARK = (186, 136, 102)
CHEEK = (226, 160, 132)
HAIR = (62, 43, 30)
HAIR_LIGHT = (88, 62, 43)
HAIR_DARK = (42, 28, 20)
HAIR_FADE = mix(HAIR, SKIN, 0.55)
EYE_WHITE = (240, 240, 240)
IRIS = (66, 48, 36)
MOUTH = (162, 98, 88)

DENIM = (54, 88, 156)
DENIM_DARK = (40, 66, 122)
DENIM_LIGHT = (80, 114, 178)

SHOE_WHITE = (245, 245, 245)
SHOE_SHADE = (224, 224, 228)
SOLE = (208, 208, 212)
SOLE_BOTTOM = (178, 178, 182)
STRIPE = (22, 22, 24)
LACE = (196, 196, 202)

# Outfit variants: a hoodie / cardigan / vest over a t-shirt.
VARIANTS = [
    {   # 0: grey zip hoodie over a white tee
        "name": "grey_hoodie",
        "jacket": (116, 118, 126), "jacket_dark": (90, 92, 100), "jacket_light": (140, 142, 150),
        "tee": (236, 236, 236), "tee_shade": (214, 214, 216),
        "sleeves": True, "style": "hoodie", "trim": (184, 184, 190),
    },
    {   # 1: green knitted cardigan over a light grey tee
        "name": "green_cardigan",
        "jacket": (62, 106, 72), "jacket_dark": (46, 84, 55), "jacket_light": (82, 128, 92),
        "tee": (196, 198, 204), "tee_shade": (174, 176, 182),
        "sleeves": True, "style": "cardigan", "trim": (226, 208, 168),
    },
    {   # 2: navy puffer vest over a red tee
        "name": "navy_puffer_vest",
        "jacket": (36, 48, 90), "jacket_dark": (24, 33, 66), "jacket_light": (56, 70, 118),
        "tee": (182, 46, 46), "tee_shade": (152, 36, 36),
        "sleeves": False, "style": "puffer", "trim": (170, 176, 196),
    },
    {   # 3: beige fleece vest over a black tee
        "name": "beige_fleece_vest",
        "jacket": (198, 178, 142), "jacket_dark": (170, 150, 116), "jacket_light": (216, 198, 164),
        "tee": (44, 44, 48), "tee_shade": (30, 30, 34),
        "sleeves": False, "style": "fleece", "trim": (120, 104, 80),
    },
]

# ---------------------------------------------------------------------------
# Body parts
# ---------------------------------------------------------------------------


def paint_head(c, rng):
    hair = lambda: jitter(HAIR, rng, 6)

    paint(c, HEAD["top"], lambda x, y, w, h: HAIR_LIGHT if rng.random() < 0.2 else hair())
    paint(c, HEAD["bottom"], lambda x, y, w, h: jitter(SKIN_SHADE, rng, 3))

    def front(x, y, w, h):
        if y <= 1:
            return hair()
        if y == 3 and x in (1, 2, 5, 6):
            return HAIR_DARK  # eyebrows
        if y == 4:
            return {1: EYE_WHITE, 2: IRIS, 5: IRIS, 6: EYE_WHITE}.get(x, SKIN)
        if y == 6 and x in (1, 6):
            return CHEEK
        if y == 8 and x in (3, 4):
            return MOUTH
        if y == 9 or x in (0, 7):
            return SKIN_SHADE
        return jitter(SKIN, rng, 2)

    paint(c, HEAD["front"], front)

    def side(front_col):
        def fn(x, y, w, h):
            # distance from the face edge: 0 = front, 7 = back
            depth = abs(x - front_col)
            if y <= 2:
                return hair()
            if y == 3:
                if depth >= 3:
                    return HAIR_DARK if depth >= 5 else HAIR_FADE  # short faded sides
                if depth == 1:
                    return HAIR_DARK  # sideburn
                return SKIN
            if y == 4 and depth >= 5:
                return HAIR_FADE
            if y == 4 and depth == 1:
                return HAIR_FADE
            if 4 <= y <= 6 and depth in (3, 4):
                return SKIN_DARK if (y == 5 and depth == 4) else SKIN_SHADE  # ear
            if y >= 8:
                return SKIN_SHADE
            return jitter(SKIN, rng, 2)

        return fn

    paint(c, HEAD["right"], side(front_col=7))
    paint(c, HEAD["left"], side(front_col=0))

    def back(x, y, w, h):
        if y <= 4:
            return hair()
        if y == 5:
            return HAIR_FADE
        return SKIN_SHADE if y >= 8 else jitter(SKIN, rng, 2)

    paint(c, HEAD["back"], back)


def paint_nose(c, rng):
    paint(c, NOSE["front"], lambda x, y, w, h: SKIN_SHADE if y == 2 else SKIN)
    for face in ("right", "left", "back", "top"):
        paint(c, NOSE[face], lambda x, y, w, h: SKIN_SHADE)
    paint(c, NOSE["bottom"], lambda x, y, w, h: SKIN_DARK)


def paint_hair_overlay(c, rng):
    """Crop haircut: textured top and a short, straight fringe."""
    hair = lambda: HAIR_LIGHT if rng.random() < 0.25 else jitter(HAIR, rng, 8)

    paint(c, HAT["top"], lambda x, y, w, h: hair())
    # Straight, blunt fringe: three full rows, perfectly even bottom edge.
    paint(c, HAT["front"], lambda x, y, w, h: (HAIR_DARK if y == 2 and x % 3 == 1 else hair()) if y <= 2 else None)

    def side(front_col):
        def fn(x, y, w, h):
            depth = abs(x - front_col)
            if y <= 1:
                return hair()
            if y == 2 and depth >= 4:
                return hair()
            return None

        return fn

    paint(c, HAT["right"], side(front_col=7))
    paint(c, HAT["left"], side(front_col=0))
    paint(c, HAT["back"], lambda x, y, w, h: hair() if y <= 3 else None)


def paint_body(c, rng, v):
    tee, tee_shade = v["tee"], v["tee_shade"]

    def torso(front_face):
        def fn(x, y, w, h):
            if y == 11:
                return DENIM_DARK if x % 3 == 0 else DENIM  # jeans waistband
            if y == 10:
                return tee_shade  # hem
            if front_face and y == 0 and x in (3, 4):
                return SKIN  # crew neck
            if front_face and y == 0 and x in (2, 5):
                return tee_shade
            return jitter(tee, rng, 3)

        return fn

    paint(c, BODY["front"], torso(True))
    for face in ("right", "left", "back"):
        paint(c, BODY[face], torso(False))
    paint(c, BODY["top"], lambda x, y, w, h: SKIN if (x in (3, 4) and y >= 1) else tee)
    paint(c, BODY["bottom"], lambda x, y, w, h: DENIM_DARK)


def paint_jacket(c, rng, v):
    base, dark, light, trim = v["jacket"], v["jacket_dark"], v["jacket_light"], v["trim"]
    style = v["style"]

    def fabric(x, y):
        if style == "cardigan":
            return light if x % 2 == 0 and rng.random() < 0.6 else jitter(base, rng, 4)  # knit ribs
        if style == "puffer":
            if y in (3, 6, 9):
                return dark  # quilting seams
            return light if y in (1, 4, 7) and rng.random() < 0.5 else jitter(base, rng, 3)
        if style == "fleece":
            return jitter(base, rng, 10)
        return jitter(base, rng, 4)

    def front(x, y, w, h):
        if y == 11:
            return None
        if y == 10:
            return dark if x not in (2, 3, 4, 5) else None  # waistband, open in the middle
        if x in (2, 3, 4, 5):
            if style == "hoodie" and x in (2, 5) and 1 <= y <= 4:
                return (240, 240, 240)  # drawstrings
            return None  # open front, t-shirt shows through
        if x in (1, 6):
            if style == "cardigan":
                if x == 1 and y in (2, 5, 8):
                    return trim  # buttons
                return dark  # button band
            if style == "hoodie" and y == 0:
                return dark
            return trim  # zipper
        if y == 0:
            return dark  # collar / hood lining
        return fabric(x, y)

    paint(c, JACKET["front"], front)

    def side(x, y, w, h):
        if y == 11:
            return None
        if y == 10:
            return dark
        return fabric(x, y)

    paint(c, JACKET["right"], side)
    paint(c, JACKET["left"], side)

    def back(x, y, w, h):
        if y == 11:
            return None
        if y == 10:
            return dark
        if style == "hoodie" and y <= 3 and 1 <= x <= 6:
            # folded hood
            if y == 3 or x in (1, 6):
                return dark
            return light
        if style != "hoodie" and y == 0:
            return dark  # collar
        return fabric(x, y)

    paint(c, JACKET["back"], back)
    paint(c, JACKET["top"], lambda x, y, w, h: None if (2 <= x <= 5 and y >= 1) else dark)


def paint_arms(c, rng, v):
    tee, tee_shade = v["tee"], v["tee_shade"]

    def arm(x, y, w, h):
        if y <= 2:
            return jitter(tee, rng, 3)
        if y == 3:
            return tee_shade  # short sleeve hem
        if y >= 10:
            return SKIN_SHADE if y == 11 else jitter(SKIN, rng, 2)  # hand
        return jitter(SKIN, rng, 2)

    for face in ("right", "front", "left", "back"):
        paint(c, ARM[face], arm)
    paint(c, ARM["top"], lambda x, y, w, h: tee)
    paint(c, ARM["bottom"], lambda x, y, w, h: SKIN_SHADE)

    if not v["sleeves"]:
        return  # vests have no sleeves: the sleeve overlay stays transparent

    base, dark = v["jacket"], v["jacket_dark"]

    def sleeve(x, y, w, h):
        if y == 11:
            return None  # hand stays visible
        if y == 10:
            return dark  # cuff
        if v["style"] == "cardigan" and x % 2 == 0 and rng.random() < 0.6:
            return v["jacket_light"]
        return jitter(base, rng, 4)

    for face in ("right", "front", "left", "back"):
        paint(c, SLEEVE[face], sleeve)
    paint(c, SLEEVE["top"], lambda x, y, w, h: base)


def paint_legs(c, rng):
    """Skinny blue jeans with a small turn-up above the sneakers."""

    def denim(x, y):
        return DENIM_LIGHT if rng.random() < 0.12 else jitter(DENIM, rng, 4)

    def front(x, y, w, h):
        if y == 8:
            return DENIM_LIGHT  # turn-up
        if y in (5, 6) and x == 1:
            return DENIM_LIGHT  # faded knee
        if y in (0, 1) and x == 1:
            return mix(DENIM, DENIM_LIGHT, 0.5)  # faded thigh
        return denim(x, y)

    def side(x, y, w, h):
        if y == 8:
            return DENIM_LIGHT
        if x == 1:
            return DENIM_DARK  # seam
        return denim(x, y)

    def back(x, y, w, h):
        if y == 8:
            return DENIM_LIGHT
        if y == 0:
            return DENIM_DARK
        return denim(x, y)

    paint(c, LEG["front"], front)
    paint(c, LEG["right"], side)
    paint(c, LEG["left"], side)
    paint(c, LEG["back"], back)
    paint(c, LEG["top"], lambda x, y, w, h: DENIM_DARK)
    paint(c, LEG["bottom"], lambda x, y, w, h: DENIM_DARK)


def paint_shoes(c, rng):
    """White sneakers, three plain black stripes on both sides, no logos."""

    def side(x, y, w, h):
        if y == 2:
            return SOLE
        if x in (1, 3, 5):
            return STRIPE
        return SHOE_WHITE

    paint(c, SHOE["right"], side)
    paint(c, SHOE["left"], side)
    paint(c, SHOE["front"], lambda x, y, w, h: SOLE if y == 2 else SHOE_WHITE)
    paint(c, SHOE["back"], lambda x, y, w, h: SOLE if y == 2 else (SHOE_SHADE if y == 0 else SHOE_WHITE))

    def top(x, y, w, h):
        # row 0 = heel, row 6 = toe
        if y in (4, 5) and x in (1, 2):
            return LACE
        if y == 0:
            return SHOE_SHADE
        return SHOE_WHITE

    paint(c, SHOE["top"], top)
    paint(c, SHOE["bottom"], lambda x, y, w, h: SOLE_BOTTOM if (x + y) % 2 else jitter(SOLE_BOTTOM, rng, 8))


def make_entity_texture(v, seed):
    rng = random.Random(seed)
    c = Canvas(64, 64)
    paint_head(c, rng)
    paint_nose(c, rng)
    paint_hair_overlay(c, rng)
    paint_body(c, rng, v)
    paint_jacket(c, rng, v)
    paint_arms(c, rng, v)
    paint_legs(c, rng)
    paint_shoes(c, rng)
    return c


def make_phone_texture():
    c = Canvas(16, 16)
    outline, body, edge = (26, 26, 30), (60, 62, 72), (82, 84, 96)
    x0, x1, y0, y1 = 5, 10, 2, 13
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            corner = (x in (x0, x1)) and (y in (y0, y1))
            if corner:
                continue
            border = x in (x0, x1) or y in (y0, y1)
            c.set(x, y, outline if border else (edge if x == x0 + 1 else body))
    # camera module with two lenses and a flash
    for y in range(3, 7):
        for x in range(6, 9):
            c.set(x, y, (124, 126, 142))
    c.set(6, 4, (16, 16, 20))
    c.set(6, 6, (16, 16, 20))
    c.set(8, 4, (250, 238, 180))
    c.set(8, 6, (40, 40, 46))
    # side button
    c.set(11, 5, outline)
    c.set(11, 6, outline)
    return c


# ---------------------------------------------------------------------------
# Orthographic preview (front / right side / back), for docs only
# ---------------------------------------------------------------------------

SUB = 2  # sub-pixels per model unit (legs sit on half-pixel positions)
VIEW_W, VIEW_H = 16, 36  # model units; y from -11 to 25


def blit(view, tex, rect, h0, y0, flip=False):
    rx, ry, rw, rh = rect
    for row in range(rh):
        for col in range(rw):
            color = tex.get(rx + (rw - 1 - col if flip else col), ry + row)
            if color[3] == 0:
                continue
            for sy in range(SUB):
                for sx in range(SUB):
                    view.set(int((h0 + col) * SUB) + sx, int((y0 + row + 11) * SUB) + sy, color)


def render_views(tex):
    views = []
    bg = (0, 0, 0, 0)

    # front: h = x + 8, entity's right (-X) on the left
    f = Canvas(VIEW_W * SUB, VIEW_H * SUB, bg)
    blit(f, tex, LEG["front"], 4.5, 12)
    blit(f, tex, LEG["front"], 8.5, 12, flip=True)
    blit(f, tex, SHOE["front"], 4, 21)
    blit(f, tex, SHOE["front"], 8, 21, flip=True)
    blit(f, tex, BODY["front"], 4, 0)
    blit(f, tex, JACKET["front"], 4, 0)
    for rect in (ARM["front"], SLEEVE["front"]):
        blit(f, tex, rect, 0, 0)
        blit(f, tex, rect, 12, 0, flip=True)
    blit(f, tex, HEAD["front"], 4, -10)
    blit(f, tex, HAT["front"], 4, -10)
    blit(f, tex, NOSE["front"], 7, -5)
    views.append(f)

    # right side: h = 8 - z, back on the left
    s = Canvas(VIEW_W * SUB, VIEW_H * SUB, bg)
    blit(s, tex, BODY["right"], 6, 0)
    blit(s, tex, JACKET["right"], 6, 0)
    blit(s, tex, LEG["right"], 6.5, 12)
    blit(s, tex, SHOE["right"], 5, 21)
    blit(s, tex, ARM["right"], 6, 0)
    blit(s, tex, SLEEVE["right"], 6, 0)
    blit(s, tex, HEAD["right"], 4, -10)
    blit(s, tex, HAT["right"], 4, -10)
    blit(s, tex, NOSE["right"], 12, -5)
    views.append(s)

    # back: h = 8 - x, entity's left (+X) on the left
    b = Canvas(VIEW_W * SUB, VIEW_H * SUB, bg)
    blit(b, tex, LEG["back"], 8.5, 12)
    blit(b, tex, LEG["back"], 4.5, 12, flip=True)
    blit(b, tex, SHOE["back"], 8, 21)
    blit(b, tex, SHOE["back"], 4, 21, flip=True)
    blit(b, tex, BODY["back"], 4, 0)
    blit(b, tex, JACKET["back"], 4, 0)
    for rect in (ARM["back"], SLEEVE["back"]):
        blit(b, tex, rect, 12, 0)
        blit(b, tex, rect, 0, 0, flip=True)
    blit(b, tex, HEAD["back"], 4, -10)
    blit(b, tex, HAT["back"], 4, -10)
    views.append(b)
    return views


def make_preview(textures, scale=4, gap=8):
    cell_w, cell_h = VIEW_W * SUB * scale, VIEW_H * SUB * scale
    cols, rows = 3, len(textures)
    out = Canvas(cols * (cell_w + gap) + gap, rows * (cell_h + gap) + gap, (205, 225, 240, 255))
    for r, tex in enumerate(textures):
        for ci, view in enumerate(render_views(tex)):
            ox, oy = gap + ci * (cell_w + gap), gap + r * (cell_h + gap)
            for y in range(view.h):
                for x in range(view.w):
                    color = view.get(x, y)
                    if color[3] == 0:
                        continue
                    for sy in range(scale):
                        for sx in range(scale):
                            out.set(ox + x * scale + sx, oy + y * scale + sy, color)
    return out


def main():
    textures = []
    for i, variant in enumerate(VARIANTS):
        tex = make_entity_texture(variant, seed=1000 + i)
        tex.save(os.path.join(ASSETS, "entity", "opalikhovets", "opalikhovets_%d.png" % i))
        textures.append(tex)
    make_phone_texture().save(os.path.join(ASSETS, "item", "phone.png"))
    make_preview(textures).save(os.path.join(ROOT, "docs", "preview.png"))
    print("Generated %d outfit textures, phone.png and docs/preview.png" % len(textures))


if __name__ == "__main__":
    main()
