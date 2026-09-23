package com.vinegretonline.opalikhovets.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import com.vinegretonline.opalikhovets.client.model.OpalikhovetsModel;
import com.vinegretonline.opalikhovets.entity.OpalikhovetsEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class OpalikhovetsRenderer extends MobRenderer<OpalikhovetsEntity, OpalikhovetsModel> {
    /** Outfit variants: grey hoodie, green cardigan, navy puffer vest, beige fleece vest. */
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[OpalikhovetsEntity.VARIANT_COUNT];

    static {
        for (int i = 0; i < TEXTURES.length; i++) {
            TEXTURES[i] = OpalikhovetsMod.id("textures/entity/opalikhovets/opalikhovets_" + i + ".png");
        }
    }

    public OpalikhovetsRenderer(EntityRendererProvider.Context context) {
        super(context, new OpalikhovetsModel(context.bakeLayer(OpalikhovetsModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(OpalikhovetsEntity entity) {
        return TEXTURES[Math.floorMod(entity.getVariant(), TEXTURES.length)];
    }

    @Override
    protected void scale(OpalikhovetsEntity entity, PoseStack poseStack, float partialTick) {
        // Same scale as the vanilla villager renderer.
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
