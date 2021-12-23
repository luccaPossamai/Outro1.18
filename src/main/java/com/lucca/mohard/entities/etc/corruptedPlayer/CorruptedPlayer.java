package com.lucca.mohard.entities.etc.corruptedPlayer;

import com.lucca.mohard.entities.etc.creepy.Creepy;
import com.lucca.mohard.entities.etc.thox.Thox;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CorruptedPlayer extends Monster implements RangedAttackMob {

    private final CorruptedPlayerTridentAttackGoal tridentGoal = new CorruptedPlayerTridentAttackGoal(this, 1.0D, 40, 10.0F);
    private final RangedCrossbowAttackGoal crossbowGoal = new RangedCrossbowAttackGoal(this, 1.0D, 8.0F);
    private final RangedBowAttackGoal<CorruptedPlayer> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            CorruptedPlayer.this.setAggressive(false);
        }

        public void start() {
            super.start();
            CorruptedPlayer.this.setAggressive(true);
        }
    };
    private static final EntityDataAccessor<Optional<UUID>> PLAYER = SynchedEntityData.defineId(CorruptedPlayer.class, EntityDataSerializers.OPTIONAL_UUID);

    public CorruptedPlayer(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.reassessWeaponGoal();
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Thox.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLAYER, Optional.ofNullable(null));
    }

    public void setPlayer(@Nullable UUID uuid){
        this.entityData.set(PLAYER, Optional.ofNullable(uuid));
    }

    public UUID getPlayer(){
        return this.entityData.get(PLAYER).orElse(null);
    }
    public @Nullable Player getRealPlayer(){
        return this.entityData.get(PLAYER).orElse(null) == null ? null :
        this.level.getPlayerByUUID(Objects.requireNonNull(this.entityData.get(PLAYER).orElse(null)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(ModAttributes.RAW_ARMOR.get(), 10)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 10);
    }

    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem));
            if (itemstack.is(Items.BOW)) {
                int i = 20;
                if (this.level.getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.bowGoal.setMinAttackInterval(i);
                this.goalSelector.addGoal(4, this.bowGoal);
            } else if(itemstack.is(Items.CROSSBOW)) {
                this.goalSelector.addGoal(4, this.crossbowGoal);
            } else if(itemstack.is(Items.TRIDENT)){
                this.goalSelector.addGoal(4, this.tridentGoal);
            } else {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }

        }
    }

    @Override
    public void performRangedAttack(LivingEntity p_32141_, float p_32142_) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
        AbstractArrow abstractarrow = this.getArrow(itemstack, p_32142_);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem) {
            abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrow);
            double d0 = p_32141_.getX() - this.getX();
            double d1 = p_32141_.getY(0.3333333333333333D) - abstractarrow.getY();
            double d2 = p_32141_.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
            this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level.addFreshEntity(abstractarrow);
        } else if(this.getMainHandItem().getItem() instanceof TridentItem){
            ThrownTrident throwntrident = new ThrownTrident(this.level, this, new ItemStack(Items.TRIDENT));
            double d0 = p_32141_.getX() - this.getX();
            double d1 = p_32141_.getY(0.3333333333333333D) - throwntrident.getY();
            double d2 = p_32141_.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            throwntrident.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
            this.playSound(SoundEvents.DROWNED_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level.addFreshEntity(throwntrident);
        } else if(this.getMainHandItem().getItem() instanceof CrossbowItem){
            this.performCrossbowAttack(this, 1.6F);
        }
    }
    private void performCrossbowAttack(LivingEntity p_32337_, float p_32338_) {
        InteractionHand interactionhand = ProjectileUtil.getWeaponHoldingHand(p_32337_, item -> item instanceof CrossbowItem);
        ItemStack itemstack = p_32337_.getItemInHand(interactionhand);
        if (p_32337_.isHolding(is -> is.getItem() instanceof CrossbowItem)) {
            CrossbowItem.performShooting(p_32337_.level, p_32337_, interactionhand, itemstack, p_32338_, (float)(14 - p_32337_.level.getDifficulty().getId() * 4));
        }
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21416_, ItemStack p_21417_) {
        super.setItemSlot(p_21416_, p_21417_);
        this.reassessWeaponGoal();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        this.reassessWeaponGoal();
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        this.reassessWeaponGoal();
    }

    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        return ProjectileUtil.getMobArrow(this, p_32156_, p_32157_);
    }

    static class CorruptedPlayerTridentAttackGoal extends RangedAttackGoal {
        private final CorruptedPlayer drowned;

        public CorruptedPlayerTridentAttackGoal(RangedAttackMob p_32450_, double p_32451_, int p_32452_, float p_32453_) {
            super(p_32450_, p_32451_, p_32452_, p_32453_);
            this.drowned = (CorruptedPlayer)p_32450_;
        }

        public boolean canUse() {
            return super.canUse() && this.drowned.getMainHandItem().is(Items.TRIDENT);
        }

        public void start() {
            super.start();
            this.drowned.setAggressive(true);
            this.drowned.startUsingItem(InteractionHand.MAIN_HAND);
        }

        public void stop() {
            super.stop();
            this.drowned.stopUsingItem();
            this.drowned.setAggressive(false);
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
        Entity entity = p_33574_.getEntity();
        for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            ItemStack itemstack = this.getItemBySlot(equipmentslot);
            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                this.spawnAtLocation(itemstack);
                this.setItemSlot(equipmentslot, ItemStack.EMPTY);
            }
        }
        if (entity instanceof Creepy) {
            Creepy creeper = (Creepy)entity;
            if (creeper.canDropMobsSkull()) {
                creeper.increaseDroppedSkulls();
                ItemStack item = new ItemStack(Items.PLAYER_HEAD, 1);
                if(!item.getOrCreateTag().contains("SkullOwner") && this.getRealPlayer() != null){
                    item.getOrCreateTag().putString("SkullOwner", this.getRealPlayer().getName().getString());
                }
                this.spawnAtLocation(item);
            }
        }

    }

}
