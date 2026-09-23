package com.vinegretonline.opalikhovets.registry;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Own sound events; sounds.json maps them onto vanilla sounds with a different pitch.
 */
public final class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, OpalikhovetsMod.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT = register("entity.opalikhovets.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> HURT = register("entity.opalikhovets.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> DEATH = register("entity.opalikhovets.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> NO = register("entity.opalikhovets.no");
    public static final DeferredHolder<SoundEvent, SoundEvent> PHONE_OUT = register("entity.opalikhovets.phone_out");
    public static final DeferredHolder<SoundEvent, SoundEvent> PHOTO = register("entity.opalikhovets.photo");

    private ModSounds() {
    }

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(OpalikhovetsMod.id(name)));
    }
}
