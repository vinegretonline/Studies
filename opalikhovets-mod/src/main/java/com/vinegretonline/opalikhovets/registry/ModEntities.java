package com.vinegretonline.opalikhovets.registry;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import com.vinegretonline.opalikhovets.entity.OpalikhovetsEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, OpalikhovetsMod.MOD_ID);

    // Same footprint as a vanilla villager.
    public static final DeferredHolder<EntityType<?>, EntityType<OpalikhovetsEntity>> OPALIKHOVETS =
            ENTITY_TYPES.register("opalikhovets", () -> EntityType.Builder
                    .of(OpalikhovetsEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F)
                    .eyeHeight(1.62F)
                    .clientTrackingRange(10)
                    .build(OpalikhovetsMod.MOD_ID + ":opalikhovets"));

    private ModEntities() {
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(OPALIKHOVETS.get(), OpalikhovetsEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(OPALIKHOVETS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                OpalikhovetsEntity::checkOpalikhovetsSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
