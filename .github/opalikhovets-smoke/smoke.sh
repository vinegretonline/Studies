#!/usr/bin/env bash
# End-to-end smoke test for opalikhovets-mod, run from the mod directory:
# dedicated server + real client under Xvfb, right-click the mob, check state over RCON,
# print a contact sheet of screenshots (idle / aiming / flash) as base64 into the log.
set -uo pipefail

HERE="$(cd "$(dirname "$0")" && pwd)"
OUT="$(pwd)/build/smoke"
mkdir -p "$OUT/shots" run
cp "$HERE/server.properties" run/server.properties
cp "$HERE/options.txt" run/options.txt
echo 'eula=true' > run/eula.txt

FAILS=()
fail() { echo "::error::SMOKE: $*"; FAILS+=("$*"); }
rcon() { python3 "$HERE/rcon.py" "$@" 2>&1 | tee -a "$OUT/rcon.log"; }
wait_for() { # file, fixed string, timeout seconds
    for ((i = 0; i < $3; i++)); do grep -qF -- "$2" "$1" 2>/dev/null && return 0; sleep 1; done
    return 1
}
shot() { import -window root "$OUT/shots/$1.png" 2>/dev/null; }
brightness() { convert "$1" -colorspace Gray -format "%[fx:mean]" info: 2>/dev/null; }

finish() {
    echo "::group::server output (tail)"; tail -n 60 "$OUT/server.out" 2>/dev/null; echo "::endgroup::"
    echo "::group::client output (tail)"; tail -n 80 "$OUT/client.out" 2>/dev/null; echo "::endgroup::"
    echo "::group::mod-related errors"
    grep -hiE 'exception|error' "$OUT/server.out" "$OUT/client.out" 2>/dev/null | grep -i opalikhovets | head -n 40
    echo "::endgroup::"
    python3 "$HERE/rcon.py" stop > /dev/null 2>&1
    kill ${CLIENT_PID:-} ${SERVER_PID:-} ${XVFB_PID:-} 2>/dev/null
    if [ -f "$OUT/sheet.jpg" ]; then
        echo "SCREENSHOT_SHEET_BEGIN"
        base64 -w 120 "$OUT/sheet.jpg"
        echo "SCREENSHOT_SHEET_END"
    fi
    if [ ${#FAILS[@]} -gt 0 ]; then
        printf 'SMOKE FAILED: %s\n' "${FAILS[@]}"
        exit 1
    fi
    echo "SMOKE PASSED"
    exit 0
}

# --- dedicated server -------------------------------------------------------
echo "[smoke] starting dedicated server"
./gradlew runServer --console=plain > "$OUT/server.out" 2>&1 &
SERVER_PID=$!
if ! wait_for "$OUT/server.out" 'Done (' 420; then
    fail "dedicated server did not start"
    finish
fi
rcon "gamerule spawnRadius 0" "gamerule doDaylightCycle false" "gamerule doMobSpawning false" \
     "gamerule doWeatherCycle false" "time set noon" "weather clear" \
     "summon opalikhovets:opalikhovets 8 -60 8" > "$OUT/setup.txt"
grep -q "Summoned new" "$OUT/setup.txt" || fail "summon on dedicated server failed"

# --- client -----------------------------------------------------------------
echo "[smoke] starting client"
Xvfb :99 -screen 0 854x480x24 > "$OUT/xvfb.log" 2>&1 &
XVFB_PID=$!
export DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1
sleep 2
./gradlew runClient --console=plain --init-script "$HERE/quickplay.init.gradle" > "$OUT/client.out" 2>&1 &
CLIENT_PID=$!
if ! wait_for "$OUT/server.out" 'joined the game' 600; then
    fail "client did not join the server"
    shot client_state
    convert "$OUT/shots/client_state.png" -quality 70 "$OUT/sheet.jpg" 2>/dev/null
    finish
fi
echo "[smoke] client joined"
sleep 5

# One mob right in front of the player (AI on, cannot walk away), all 4 outfits further back (no AI).
rcon "execute at @p run summon opalikhovets:opalikhovets ~ ~ ~2.5" \
     "execute at @p run tag @e[type=opalikhovets:opalikhovets,sort=nearest,limit=1] add cam" \
     "attribute @e[tag=cam,limit=1] minecraft:generic.movement_speed base set 0" \
     "execute at @p run summon opalikhovets:opalikhovets ~-3.3 ~ ~6 {NoAI:1b,Variant:0,Rotation:[180f,0f]}" \
     "execute at @p run summon opalikhovets:opalikhovets ~-1.4 ~ ~6 {NoAI:1b,Variant:1,Rotation:[180f,0f]}" \
     "execute at @p run summon opalikhovets:opalikhovets ~1.4 ~ ~6 {NoAI:1b,Variant:2,Rotation:[180f,0f]}" \
     "execute at @p run summon opalikhovets:opalikhovets ~3.3 ~ ~6 {NoAI:1b,Variant:3,Rotation:[180f,0f]}" \
     "execute at @p run summon opalikhovets:opalikhovets ~-2 ~ ~3.5 {NoAI:1b,Variant:2,Rotation:[90f,0f]}" \
     > "$OUT/summon.txt"
sleep 15
shot idle

# The first click may only grab the mouse; wait out a possible photo + cooldown, then click for real.
xdotool mousemove 427 240
sleep 0.5
xdotool click 3
sleep 14
echo "[smoke] right-click"
xdotool click 3
( sleep 0.5; rcon "data get entity @e[tag=cam,limit=1] HandItems" > "$OUT/hand_aiming.txt" ) &
HAND_PID=$!
for i in $(seq -w 0 39); do shot "burst_$i"; sleep 0.05; done
# Only wait for the RCON probe: a bare `wait` would also wait for the game processes.
wait "$HAND_PID"
sleep 2
rcon "data get entity @e[tag=cam,limit=1] HandItems" "data get entity @e[tag=cam,limit=1] PhotoCooldown" > "$OUT/after.txt"
# Clicking during the cooldown must not start another photo.
xdotool click 3
sleep 1
rcon "data get entity @e[tag=cam,limit=1] HandItems" > "$OUT/cooldown.txt"

grep -q 'opalikhovets:phone' "$OUT/hand_aiming.txt" || fail "no phone in hand while aiming"
grep -q 'opalikhovets:phone' "$OUT/after.txt" && fail "phone still in hand after the photo"
grep -qE 'PhotoCooldown.*: (1[0-9][0-9]|[1-9][0-9]|[1-9])$' "$OUT/after.txt" || fail "cooldown not running after the photo"
grep -q 'opalikhovets:phone' "$OUT/cooldown.txt" && fail "photo started again during cooldown"
cat "$OUT/hand_aiming.txt" "$OUT/after.txt" "$OUT/cooldown.txt"

# Pick the brightest burst frame (flash) and an early frame (phone raised).
best=""; best_b=0
for f in "$OUT"/shots/burst_*.png; do
    b=$(brightness "$f"); echo "$(basename "$f") $b"
    if python3 -c "import sys; sys.exit(0 if float('$b') > float('$best_b') else 1)"; then best=$f; best_b=$b; fi
done
idle_b=$(brightness "$OUT/shots/idle.png")
echo "idle brightness $idle_b, brightest burst frame $best ($best_b)"
python3 -c "import sys; sys.exit(0 if float('$best_b') > float('$idle_b') + 0.25 else 1)" || fail "no white flash captured"
aim="$OUT/shots/burst_05.png"
montage "$OUT/shots/idle.png" "$aim" "$best" -tile 3x1 -geometry 427x240+2+2 -quality 70 "$OUT/sheet.jpg"
finish
