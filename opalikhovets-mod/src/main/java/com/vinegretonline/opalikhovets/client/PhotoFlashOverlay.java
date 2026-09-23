package com.vinegretonline.opalikhovets.client;

import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

/** Short full-screen white flash shown to a photographed player. */
public final class PhotoFlashOverlay {
    private static final long HOLD_MS = 80L;
    private static final long FADE_MS = 520L;
    private static final float MAX_ALPHA = 0.95F;
    /** Used when the "Hide Lightning Flashes" accessibility option is on. */
    private static final float REDUCED_MAX_ALPHA = 0.25F;

    private static long flashStartMs = -1L;

    private PhotoFlashOverlay() {
    }

    public static void trigger() {
        flashStartMs = Util.getMillis();
    }

    public static void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        if (flashStartMs < 0L) {
            return;
        }
        long elapsed = Util.getMillis() - flashStartMs;
        if (elapsed >= HOLD_MS + FADE_MS) {
            flashStartMs = -1L;
            return;
        }
        float strength = elapsed < HOLD_MS ? 1.0F : 1.0F - (float) (elapsed - HOLD_MS) / FADE_MS;
        float maxAlpha = Minecraft.getInstance().options.hideLightningFlash().get() ? REDUCED_MAX_ALPHA : MAX_ALPHA;
        int alpha = Mth.clamp((int) (strength * maxAlpha * 255.0F), 0, 255);
        if (alpha > 0) {
            graphics.fill(0, 0, graphics.guiWidth(), graphics.guiHeight(), alpha << 24 | 0xFFFFFF);
        }
    }
}
