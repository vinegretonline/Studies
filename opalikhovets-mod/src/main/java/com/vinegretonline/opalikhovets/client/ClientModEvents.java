package com.vinegretonline.opalikhovets.client;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import com.vinegretonline.opalikhovets.client.model.OpalikhovetsModel;
import com.vinegretonline.opalikhovets.client.renderer.OpalikhovetsRenderer;
import com.vinegretonline.opalikhovets.registry.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@EventBusSubscriber(modid = OpalikhovetsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OpalikhovetsModel.LAYER_LOCATION, OpalikhovetsModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.OPALIKHOVETS.get(), OpalikhovetsRenderer::new);
    }

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(OpalikhovetsMod.id("photo_flash"), PhotoFlashOverlay::render);
    }
}
