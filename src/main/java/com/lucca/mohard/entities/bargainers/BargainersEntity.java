package com.lucca.mohard.entities.bargainers;

import com.lucca.mohard.entities.mooers.MooerEntity;
import com.lucca.mohard.help.Methods;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BargainersEntity extends PathfinderMob implements Npc, Merchant {

    private final BargainersType bargainersType;

    public BargainersEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_, BargainersType type) {
        super(p_i48575_1_, p_i48575_2_);
        this.bargainersType = type;
    }


    @Override
    public void setTradingPlayer(@Nullable Player p_70932_1_) {
    }

    public static AttributeSupplier.Builder createAttributes(BargainersType type) {
        AttributeSupplier.Builder attributes = Monster.createMonsterAttributes();
        Map<Attribute, Double> attributesList = BargainersType.BargainersTypeHelper.getAttributes(type);
        for(Attribute att : attributesList.keySet()){
            attributes.add(att, attributesList.get(att));
        }
        return attributes;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return null;
    }

    @Override
    public MerchantOffers getOffers() {
        return null;
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers p_213703_1_) {

    }

    @Override
    public void notifyTrade(MerchantOffer p_213704_1_) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack p_110297_1_) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int p_213702_1_) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return this.level.isClientSide;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public BargainersType getBargainersType() {
        return bargainersType;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, @Nullable SpawnGroupData p_213386_4_, @Nullable CompoundTag p_213386_5_) {
        p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
        BargainersType type = this.getBargainersType();
        this.setLeftHanded(type.leftHanded);

        this.setItemInHand(InteractionHand.MAIN_HAND, type.getMainHandItem());

        this.setItemInHand(InteractionHand.OFF_HAND, type.getOffHandItem());

        if(type.equals(BargainersType.AAN)) {
            this.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(DyeItem.byColor(DyeColor.byId((int) Math.round(Math.random() * 15))), 1));
        }

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);

    }


}
