package com.vinegretonline.opalikhovets;

import com.vinegretonline.opalikhovets.network.ModNetwork;
import com.vinegretonline.opalikhovets.registry.ModEntities;
import com.vinegretonline.opalikhovets.registry.ModItems;
import com.vinegretonline.opalikhovets.registry.ModSounds;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(OpalikhovetsMod.MOD_ID)
public final class OpalikhovetsMod {
    public static final String MOD_ID = "opalikhovets";

    public OpalikhovetsMod(IEventBus modEventBus) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(ModEntities::registerAttributes);
        modEventBus.addListener(ModEntities::registerSpawnPlacements);
        modEventBus.addListener(ModItems::addToCreativeTabs);
        modEventBus.addListener(ModNetwork::registerPayloads);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
