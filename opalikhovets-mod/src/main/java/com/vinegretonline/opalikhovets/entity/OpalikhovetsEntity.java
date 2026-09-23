package com.vinegretonline.opalikhovets.entity;

import com.vinegretonline.opalikhovets.entity.ai.TakePhotoGoal;
import com.vinegretonline.opalikhovets.registry.ModItems;
import com.vinegretonline.opalikhovets.registry.ModSounds;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * A peaceful villager-like mob. Right-click him and he takes out his phone,
 * aims at the player for 1-2 seconds and takes a photo (see {@link TakePhotoGoal}).
 */
public class OpalikhovetsEntity extends PathfinderMob {
    public static final int VARIANT_COUNT = 4;
    public static final int PHOTO_COOLDOWN_TICKS = 10 * 20;

    public static final byte PHASE_NONE = 0;
    public static final byte PHASE_AIMING = 1;
    public static final byte PHASE_SHOT = 2;

    private static final EntityDataAccessor<Integer> DATA_VARIANT =
            SynchedEntityData.defineId(OpalikhovetsEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_PHOTO_PHASE =
            SynchedEntityData.defineId(OpalikhovetsEntity.class, EntityDataSerializers.BYTE);

    /** Server-only: the player who asked for a photo. Not persisted. */
    @Nullable
    private Player photoTarget;
    private int photoCooldown;

    public OpalikhovetsEntity(EntityType<? extends OpalikhovetsEntity> type, Level level) {
        super(type, level);
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
        if (this.getNavigation() instanceof GroundPathNavigation navigation) {
            navigation.setCanOpenDoors(true);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    public static boolean checkOpalikhovetsSpawnRules(EntityType<OpalikhovetsEntity> type, ServerLevelAccessor level,
                                                      MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        boolean validGround = level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
                || level.getBlockState(pos.below()).is(BlockTags.SAND);
        return validGround && level.getRawBrightness(pos, 0) > 8;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(2, new TakePhotoGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.8D, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, AbstractIllager.class, 12.0F, 0.8D, 1.0D));
        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(7, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT, 0);
        builder.define(DATA_PHOTO_PHASE, PHASE_NONE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant());
        tag.putInt("PhotoCooldown", this.photoCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(tag.getInt("Variant"));
        this.photoCooldown = tag.getInt("PhotoCooldown");
        // A photo session is not persisted, so never load with the phone still in hand.
        if (this.getMainHandItem().is(ModItems.PHONE.get())) {
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.setVariant(level.getRandom().nextInt(VARIANT_COUNT));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND || !this.isAlive()) {
            return super.mobInteract(player, hand);
        }
        if (!this.level().isClientSide && this.photoTarget == null && !this.isPhotographing()) {
            if (this.photoCooldown > 0) {
                this.playSound(ModSounds.NO.get(), this.getSoundVolume(), this.getVoicePitch());
            } else {
                this.photoTarget = player;
            }
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.photoCooldown > 0) {
            this.photoCooldown--;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt && !this.level().isClientSide) {
            // Getting hit cancels the photo session; PanicGoal takes over.
            this.photoTarget = null;
        }
        return hurt;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isPhotographing() ? null : ModSounds.AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DEATH.get();
    }

    public int getVariant() {
        return this.entityData.get(DATA_VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT, Math.floorMod(variant, VARIANT_COUNT));
    }

    public byte getPhotoPhase() {
        return this.entityData.get(DATA_PHOTO_PHASE);
    }

    public void setPhotoPhase(byte phase) {
        this.entityData.set(DATA_PHOTO_PHASE, phase);
    }

    /** Synced to the client, drives the raised-phone animation. */
    public boolean isPhotographing() {
        return this.getPhotoPhase() != PHASE_NONE;
    }

    @Nullable
    public Player getPhotoTarget() {
        return this.photoTarget;
    }

    public void clearPhotoTarget() {
        this.photoTarget = null;
    }

    public void startPhotoCooldown() {
        this.photoCooldown = PHOTO_COOLDOWN_TICKS;
    }
}
