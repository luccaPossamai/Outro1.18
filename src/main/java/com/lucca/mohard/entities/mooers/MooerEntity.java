package com.lucca.mohard.entities.mooers;

import com.google.common.collect.*;
import com.lucca.mohard.entities.bargainers.BargainersEntity;
import com.lucca.mohard.entities.mooers.AI.MooerAction;
import com.lucca.mohard.entities.mooers.AI.tasks.MultipleTemptGoal;
import com.lucca.mohard.entities.mooers.AI.tasks.TradeWithPlayerMooerGoal;
import com.lucca.mohard.itens.essence.EssenceData;
import com.lucca.mohard.itens.essence.EssenceDataHelper;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.*;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;

public class MooerEntity extends AbstractMooerEntity {

    private static final EntityDataAccessor<Integer> DATA_UNHAPPY_COUNTER = SynchedEntityData.defineId(MooerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(MooerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_DANCING = SynchedEntityData.defineId(MooerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_READING = SynchedEntityData.defineId(MooerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.2F, AttributeModifier.Operation.MULTIPLY_BASE);
    private final SimpleContainer inventory = new SimpleContainer(8);
    private BlockPos jukebox;
    @Nullable
    private Player tradingPlayer;
    @Nullable
    protected MerchantOffers offers;

    private static final Map<EntityType<?>, MooerType> types = ImmutableMap.of(ModEntityTypes.MOOER_ENTITY_TYPE, MooerType.DEFAULT, ModEntityTypes.GOLDEN_MOOER_ENTITY_TYPE, MooerType.GILDED, ModEntityTypes.MOOSHROOM_MOOER_ENTITY_TYPE, MooerType.MOOSHROOM);

    public MooerEntity(EntityType<? extends AbstractMooerEntity> p_i48553_1_, Level p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
        this.xpReward = 5;

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerMooerGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, BargainersEntity.class, true));
        this.goalSelector.addGoal(3, new MultipleTemptGoal(this, (10 + Math.PI)/10, EssenceData.getItems(), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }


    public MooerType getMooerType() {
        return types.getOrDefault(this.getType(), MooerType.DEFAULT);
    }



    @Override
    public MooerAction getArmPose() {
        if(this.isDancing()){
            return MooerAction.DANCING;
        } else if(this.isReading()){
            return MooerAction.READING;
        } else {
            return MooerAction.DEFAULT;
        }
    }

    public boolean isReading(){
        return this.entityData.get(DATA_IS_READING);
    }

    public boolean isDancing() {
        return this.entityData.get(DATA_IS_DANCING);
    }

    public void setDancing(boolean p_234442_1_){
        this.entityData.set(DATA_IS_DANCING, p_234442_1_);
    }

    public void setReading(boolean read){
        this.entityData.set(DATA_IS_READING, read);
    }

    @Override
    public boolean startRiding(Entity entity, boolean p_184205_2_) {
        if (this.isBaby() && entity.getType() == EntityType.COW) {
            Cow cow = (Cow) entity;
            if(cow.isBaby()) {
                return super.startRiding(entity, p_184205_2_);
            }
        }
        return false;


    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        boolean flag = super.hurt(p_70097_1_, p_70097_2_);
        if (this.level.isClientSide) {
            return false;
        } else {
            if (flag && p_70097_1_.getEntity() instanceof LivingEntity) {
            }

            return flag;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("IsBaby", this.isBaby());
        p_213281_1_.put("Inventory", this.inventory.createTag());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBaby(p_70037_1_.getBoolean("IsBaby"));
        this.inventory.fromTag(p_70037_1_.getList("Inventory", 10));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)Math.PI/10)
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(ModAttributes.RAW_ARMOR.get(), 4)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 1)
                .add(ModAttributes.AGILITY.get(), 30)
                .add(ModAttributes.ARMOR_PENETRATION.get(), 2)
                .add(ModAttributes.MAGIC_DAMAGE.get(), 40)
                .add(ModAttributes.INTELLECT, 50);
    }

    @Override
    public boolean isBaby() {
        return this.getEntityData().get(DATA_BABY_ID);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return this.isBaby() ? 0.93F : 1.74F;

    }

    @Override
    public void setBaby(boolean p_82227_1_) {
        this.getEntityData().set(DATA_BABY_ID, p_82227_1_);
        if (this.level != null && !this.level.isClientSide) {
            AttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(SPEED_MODIFIER_BABY);
            if (p_82227_1_) {
                modifiableattributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
            }
        }

    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> p_184206_1_) {
        super.onSyncedDataUpdated(p_184206_1_);
        if (DATA_BABY_ID.equals(p_184206_1_)) {
            this.refreshDimensions();
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_BABY_ID, false);
        this.getEntityData().define(DATA_IS_DANCING, false);
        this.getEntityData().define(DATA_IS_READING, false);
        this.getEntityData().define(DATA_UNHAPPY_COUNTER, 0);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, @Nullable SpawnGroupData p_213386_4_, @Nullable CompoundTag p_213386_5_) {
        p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
        float f = p_213386_2_.getSpecialMultiplier();
        if (p_213386_4_ == null) {
            p_213386_4_ = new MooerEntity.GroupData(getSpawnAsBabyOdds(p_213386_1_.getRandom()), true);
        }
        if (p_213386_4_ instanceof MooerEntity.GroupData) {
            MooerEntity.GroupData mooerEntityData = (MooerEntity.GroupData)p_213386_4_;
            if (mooerEntityData.isBaby) {
                this.setBaby(true);
                if (mooerEntityData.canSpawnJockey) {
                    if ((double)p_213386_1_.getRandom().nextFloat() < 0.05D) {
                        List<Cow> list = p_213386_1_.getEntitiesOfClass(Cow.class, this.getBoundingBox().inflate(5.0D, 3.0D, 5.0D), EntitySelector.ENTITY_NOT_BEING_RIDDEN);
                        if (!list.isEmpty()) {
                            Cow cowEntity = list.get(0);
                            this.startRiding(cowEntity);
                        }
                    } else if ((double)p_213386_1_.getRandom().nextFloat() < 0.05D) {
                        Cow cowEntity1 = EntityType.COW.create(this.level);
                        cowEntity1.moveTo(this.getX(), this.getY(), this.getZ(), this.yBodyRot, 0.0F);
                        cowEntity1.finalizeSpawn(p_213386_1_, p_213386_2_, MobSpawnType.JOCKEY, (SpawnGroupData)null, (CompoundTag)null);
                        this.startRiding(cowEntity1);
                        p_213386_1_.addFreshEntity(cowEntity1);
                    }
                }
            }

            this.populateDefaultEquipmentSlots(p_213386_2_);
            this.populateDefaultEquipmentEnchantments(p_213386_2_);
        }


        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    private static boolean getSpawnAsBabyOdds(Random p_241399_0_) {
        return p_241399_0_.nextFloat() < net.minecraftforge.common.ForgeConfig.SERVER.zombieBabyChance.get();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.isAlive() && !this.isTrading() && !player.isSecondaryUseActive()) {
            if (this.isBaby()) {
                this.setUnhappy();
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            } else {
                updateTrades(player);
                boolean flag = this.getOffers().isEmpty();
                if (hand == InteractionHand.MAIN_HAND) {
                    if (flag && !this.level.isClientSide) {
                        this.setUnhappy();
                    }
                }

                if (flag) {
                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                } else {
                    if (!this.level.isClientSide && !this.offers.isEmpty()) {
                        this.startTrading(player);
                    }

                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                }
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void setUnhappy() {
        this.setUnhappyCounter(40);
        if (!this.level.isClientSide()) {
            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
        }
    }
    public void setUnhappyCounter(int p_35320_) {
        this.entityData.set(DATA_UNHAPPY_COUNTER, p_35320_);
    }
    public int getUnhappyCounter() {
        return this.entityData.get(DATA_UNHAPPY_COUNTER);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getUnhappyCounter() > 0) {
            this.setUnhappyCounter(this.getUnhappyCounter() - 1);
        }
    }

    @Override
    protected void pickUpItem(ItemEntity p_175445_1_) {
        this.onItemPickup(p_175445_1_);
    }

    public SimpleContainer getInventory(){
        return this.inventory;
    }
    public boolean isTrading() {
        return this.tradingPlayer != null;
    }
    private void startTrading(Player p_35537_) {
        updateTrades(p_35537_);
        this.setTradingPlayer(p_35537_);
        this.openTradingScreen(p_35537_, this.getDisplayName(), 1);
    }

    @Override
    public void setTradingPlayer(@Nullable Player p_45307_) {
        this.tradingPlayer = p_45307_;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }

        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers p_45306_) {

    }

    @Override
    public void notifyTrade(MerchantOffer p_45305_) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack p_45308_) {

    }


    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int p_45309_) {

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

    protected void updateTrades(Player player) {
        if(player != null){
            List<ItemStack> essences = new ArrayList<>();
            Inventory inv = player.getInventory();
            for(int i = 0; i < inv.getContainerSize(); i++){
                ItemStack itemStack = inv.getItem(i);
                if(itemStack.getItem() instanceof EssenceItem){
                    if(EssenceDataHelper.canUpgradeEssence(itemStack)) {
                        essences.add(itemStack);
                    }
                }
            }
            this.offers = new MerchantOffers();
            for(ItemStack essence : essences) {
                MerchantOffer offer = createOffer(essence);
                if(offer != null)this.offers.add(offer);

            }
        }
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        this.stopTrading();
    }
    protected void stopTrading() {
        this.setTradingPlayer((Player)null);
    }

    @Nullable
    protected MerchantOffer createOffer(ItemStack item){
        ItemStack itemStack = item.copy();
        itemStack.setCount(9);

        if(this.getMooerType().equals(MooerType.GILDED) && EssenceDataHelper.canUpgradeNegativeEssence(item)) {
            return new MerchantOffer(itemStack, new ItemStack(ModItens.TRUE_VOID_REMNANTS.get(), 64), getUpgradedNegativeItem(item), 0, 1, 0, 1, 0);
        }
        if(this.getMooerType().equals(MooerType.MOOSHROOM)){

        }
        if(this.getMooerType().equals(MooerType.DEFAULT) && EssenceDataHelper.canUpgradeEssence(item)) {
            return new MerchantOffer(itemStack, new ItemStack(ModItens.TRUE_VOID_REMNANTS.get(), 64), getUpgradedItem(item), 0, 1, 0, 1, 0);
        }

        return null;
    }


    protected ItemStack getUpgradedItem(ItemStack item){
        ItemStack itemStack =  EssenceDataHelper.upgradeLevel(item);
        itemStack.setCount(1);
        return itemStack;
    }
    protected ItemStack getUpgradedNegativeItem(ItemStack item){
        ItemStack itemStack = EssenceDataHelper.upgradeNegativeLevel(item);
        itemStack.setCount(1);
        return itemStack;
    }


    public static class GroupData implements SpawnGroupData {
        public final boolean isBaby;
        public final boolean canSpawnJockey;

        public GroupData(boolean p_i231567_1_, boolean p_i231567_2_) {
            this.isBaby = p_i231567_1_;
            this.canSpawnJockey = p_i231567_2_;
        }
    }

    @Override
    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerThan(this.position(), 3.46D) || !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.setDancing(false);
            this.jukebox = null;
        }

        super.aiStep();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void setRecordPlayingNearby(BlockPos p_191987_1_, boolean p_191987_2_) {
        this.jukebox = p_191987_1_;
        this.setDancing(p_191987_2_);
    }

    public void playSound(SoundEvent p_241417_1_) {
        this.playSound(p_241417_1_, this.getSoundVolume(), this.getVoicePitch());

    }

}