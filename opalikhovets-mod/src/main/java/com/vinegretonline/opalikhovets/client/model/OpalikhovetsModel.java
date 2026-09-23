package com.vinegretonline.opalikhovets.client.model;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import com.vinegretonline.opalikhovets.entity.OpalikhovetsEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

/**
 * Villager-proportioned humanoid (tall 8x10x8 head with a small nose) with free arms,
 * overlay layers for the crop haircut, jacket/vest and sleeves, skinny legs and sneakers.
 *
 * Texture layout (64x64):
 * head 0,0 | nose 24,0 | hair 32,0 | body 0,18 | jacket 24,18 | arm 48,18 | sleeve 48,34 | leg 0,34 | shoe 12,34.
 * Left limbs mirror the right ones.
 */
public class OpalikhovetsModel extends HumanoidModel<OpalikhovetsEntity> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(OpalikhovetsMod.id("opalikhovets"), "main");

    private static final CubeDeformation OVERLAY = new CubeDeformation(0.25F);
    private static final CubeDeformation HAIR = new CubeDeformation(0.5F);
    // Slightly narrower than 4 px so the two shoes never overlap, slightly taller/longer to cover the leg.
    private static final CubeDeformation SHOE = new CubeDeformation(-0.05F, 0.1F, 0.1F);

    public OpalikhovetsModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
        head.addOrReplaceChild("nose", CubeListBuilder.create()
                .texOffs(24, 0).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 3.0F, 1.0F), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create()
                .texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, HAIR), PartPose.ZERO);

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 18).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F), PartPose.ZERO);
        body.addOrReplaceChild("jacket", CubeListBuilder.create()
                .texOffs(24, 18).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, OVERLAY), PartPose.ZERO);

        PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(48, 18).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        rightArm.addOrReplaceChild("right_sleeve", CubeListBuilder.create()
                .texOffs(48, 34).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, OVERLAY), PartPose.ZERO);
        PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(48, 18).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        leftArm.addOrReplaceChild("left_sleeve", CubeListBuilder.create()
                .texOffs(48, 34).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, OVERLAY), PartPose.ZERO);

        // Skinny jeans: legs are 3 px wide instead of 4.
        PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(0, 34).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        rightLeg.addOrReplaceChild("right_shoe", CubeListBuilder.create()
                .texOffs(12, 34).addBox(-2.0F, 9.0F, -4.0F, 4.0F, 3.0F, 7.0F, SHOE), PartPose.ZERO);
        PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(0, 34).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        leftLeg.addOrReplaceChild("left_shoe", CubeListBuilder.create()
                .texOffs(12, 34).mirror().addBox(-2.0F, 9.0F, -4.0F, 4.0F, 3.0F, 7.0F, SHOE), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(OpalikhovetsEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (entity.isPhotographing()) {
            this.poseHoldingPhone(entity, ageInTicks);
        }
    }

    /** Phone arm raised to eye level in front of the face, the other hand supporting it. */
    private void poseHoldingPhone(OpalikhovetsEntity entity, float ageInTicks) {
        boolean rightHanded = entity.getMainArm() == HumanoidArm.RIGHT;
        ModelPart phoneArm = rightHanded ? this.rightArm : this.leftArm;
        ModelPart supportArm = rightHanded ? this.leftArm : this.rightArm;
        // Positive yRot turns an arm towards -X; the right arm sits at -X, so it needs a negative angle to reach the middle.
        float inward = rightHanded ? -1.0F : 1.0F;

        phoneArm.xRot = -Mth.HALF_PI - 0.25F + this.head.xRot;
        phoneArm.yRot = 0.35F * inward + this.head.yRot;
        phoneArm.zRot = 0.0F;

        supportArm.xRot = -Mth.HALF_PI - 0.15F + this.head.xRot;
        supportArm.yRot = -0.55F * inward + this.head.yRot;
        supportArm.zRot = 0.0F;

        if (entity.getPhotoPhase() == OpalikhovetsEntity.PHASE_SHOT) {
            // Tiny "tap" of the shutter button right after the shot.
            float tap = Mth.sin(ageInTicks * 1.3F) * 0.03F;
            phoneArm.xRot += tap;
            supportArm.xRot += tap;
        }
    }
}
