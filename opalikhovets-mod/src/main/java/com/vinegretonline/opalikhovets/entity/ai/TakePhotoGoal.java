package com.vinegretonline.opalikhovets.entity.ai;

import com.vinegretonline.opalikhovets.entity.OpalikhovetsEntity;
import com.vinegretonline.opalikhovets.network.PhotoFlashPayload;
import com.vinegretonline.opalikhovets.registry.ModItems;
import com.vinegretonline.opalikhovets.registry.ModSounds;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Takes out the phone, aims at the player for 1-2 seconds, then "takes a photo":
 * camera click for everyone nearby and a white screen flash for the photographed player.
 */
public class TakePhotoGoal extends Goal {
    private static final double MAX_DISTANCE_SQR = 16.0D * 16.0D;
    private static final int MIN_AIM_TICKS = 20;
    private static final int MAX_AIM_TICKS = 40;
    /** How long the phone stays up after the shot. */
    private static final int HOLD_AFTER_SHOT_TICKS = 12;

    private final OpalikhovetsEntity mob;
    private int aimTicks;
    private int holdTicks;
    private boolean photoTaken;

    public TakePhotoGoal(OpalikhovetsEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        Player target = this.mob.getPhotoTarget();
        if (target == null) {
            return false;
        }
        if (!this.isValidTarget(target)) {
            this.mob.clearPhotoTarget();
            return false;
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.isValidTarget(this.mob.getPhotoTarget()) && (!this.photoTaken || this.holdTicks > 0);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        this.mob.getNavigation().stop();
        this.aimTicks = MIN_AIM_TICKS + this.mob.getRandom().nextInt(MAX_AIM_TICKS - MIN_AIM_TICKS + 1);
        this.holdTicks = HOLD_AFTER_SHOT_TICKS;
        this.photoTaken = false;
        this.mob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.PHONE.get()));
        this.mob.setPhotoPhase(OpalikhovetsEntity.PHASE_AIMING);
        this.mob.playSound(ModSounds.PHONE_OUT.get(), 1.0F, 1.0F);
    }

    @Override
    public void tick() {
        Player target = this.mob.getPhotoTarget();
        if (target == null) {
            return;
        }
        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        if (!this.photoTaken) {
            if (--this.aimTicks <= 0) {
                this.takePhoto(target);
            }
        } else {
            this.holdTicks--;
        }
    }

    @Override
    public void stop() {
        if (this.photoTaken) {
            this.mob.startPhotoCooldown();
        }
        this.photoTaken = false;
        this.mob.clearPhotoTarget();
        this.mob.setPhotoPhase(OpalikhovetsEntity.PHASE_NONE);
        if (this.mob.getMainHandItem().is(ModItems.PHONE.get())) {
            this.mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    private void takePhoto(Player target) {
        this.photoTaken = true;
        this.mob.setPhotoPhase(OpalikhovetsEntity.PHASE_SHOT);
        this.mob.level().playSound(null, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(),
                ModSounds.PHOTO.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);

        if (this.mob.level() instanceof ServerLevel serverLevel) {
            Vec3 phonePos = this.mob.getEyePosition().add(this.mob.getViewVector(1.0F).scale(0.5D));
            serverLevel.sendParticles(ParticleTypes.END_ROD, phonePos.x, phonePos.y, phonePos.z,
                    4, 0.05D, 0.05D, 0.05D, 0.01D);
        }
        if (target instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, PhotoFlashPayload.INSTANCE);
        }
    }

    private boolean isValidTarget(@Nullable Player target) {
        return target != null
                && target.isAlive()
                && !target.isRemoved()
                && !target.isSpectator()
                && target.level() == this.mob.level()
                && this.mob.distanceToSqr(target) <= MAX_DISTANCE_SQR;
    }
}
