package com.lucca.mohard.entities.corrupter;

import com.lucca.mohard.effects.corruption.CorruptionMechanics;
import com.lucca.mohard.setup.init.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;


public class Corrupter extends Monster implements RangedAttackMob, CorruptedEntity {

    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Corrupter.class, EntityDataSerializers.INT);

    public Corrupter(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1D, 1D));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 60, 10.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.35D));
            NearestAttackableTargetGoal attackGoal = new NearestAttackableTargetGoal(this, AbstractSkeleton.class, 10, true, false, (Predicate<LivingEntity>)null);
            this.goalSelector.addGoal(4, attackGoal);
            attackGoal = new NearestAttackableTargetGoal(this, Creeper.class, 10, true, false, (Predicate<LivingEntity>)null);
            this.goalSelector.addGoal(4, attackGoal);
            attackGoal = new NearestAttackableTargetGoal(this, Zombie.class, 10, true, false, (Predicate<LivingEntity>)null);
            this.goalSelector.addGoal(4, attackGoal);
            attackGoal = new NearestAttackableTargetGoal(this, Spider.class, 10, true, false, (Predicate<LivingEntity>)null);
            this.goalSelector.addGoal(4, attackGoal);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100).add(Attributes.MOVEMENT_SPEED, 0.35D).add(ModAttributes.MAGIC_DAMAGE.get(), 10);
    }

    @Override
    public void performRangedAttack(LivingEntity p_34143_, float p_34144_) {
        if(this.getTarget() != null) {
            if (!(this.getTarget().getActiveEffectsMap().containsKey(ModEffects.CORRUPTION.get()))) {
                Vec3 vec3 = p_34143_.getDeltaMovement();
                double d0 = p_34143_.getX() + vec3.x - this.getX();
                double d1 = p_34143_.getEyeY() - (double) 1.1F - this.getY();
                double d2 = p_34143_.getZ() + vec3.z - this.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);

                Potion potion = getRandomPotion();

                ThrownPotion thrownpotion = new ThrownPotion(this.level, this);
                thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
                thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
                thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
                if (!this.isSilent()) {
                    this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
                }

                this.level.addFreshEntity(thrownpotion);
                this.setTarget(null);
                this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putInt("Type", this.getCorrupterType().getId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        this.setCorrupterType(Corrupter.Type.byId(p_21450_.getInt("Type")));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    protected void setCorrupterType(Corrupter.Type type){
        this.entityData.set(DATA_TYPE_ID, type.getId());
    }

    protected Corrupter.Type getCorrupterType(){
        return Corrupter.Type.byId(this.entityData.get(DATA_TYPE_ID));
    }

    @Override
    public void setTarget(@Nullable LivingEntity p_21544_) {
        super.setTarget(p_21544_);
    }

    @Nullable
    @Override
    public LivingEntity getTarget() {
        LivingEntity mob = super.getTarget();
        if(mob != null) {
            if (mob.hasEffect(ModEffects.CORRUPTION.get()) || CorruptionMechanics.converted(mob)) {
                setTarget(null);
            }
        }
        return super.getTarget();
    }

    private Potion getRandomPotion(){
        double chance = Math.random() * 3;
        if(chance < 1){
            return  ModPotions.CORRUPTION_POTION.get();
        } else if(chance < 2){
            return ModPotions.STRONG_CORRUPTION_POTION.get();
        }
        return ModPotions.VERY_STRONG_CORRUPTION_POTION.get();

    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
        super.dropCustomDeathLoot(p_21385_, p_21386_, p_21387_);
        for(Item items : getDrops().keySet()){
            if(Math.random() * 100 < getDrops().get(items)){
                this.spawnAtLocation(items);
            }
        }

    }

    @Override
    public Map<Item, Double> getDrops() {
        return new HashMap<>();
    }

    @Override
    public int getEssenceXp() {
        return 1;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {

        Optional<ResourceKey<Biome>> optional = p_21434_.getBiomeName(this.blockPosition());
        Corrupter.Type cType = Corrupter.Type.byBiome(optional);
        this.setCorrupterType(cType);

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    protected enum Type{


        DENSE_DESERT(0, "dense_desert", ModBiomeKeys.DENSE_DESERT),
        DENSE_TAIGA(1, "dense_taiga", ModBiomeKeys.TAIGA);


        private static final Corrupter.Type[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(Corrupter.Type::getId)).toArray((p_28822_) -> {
            return new Corrupter.Type[p_28822_];
        });
        private int id;
        private String suffix;
        List<ResourceKey<Biome>> biomes;

        Type(int id, String suffix, ResourceKey<Biome>... biomes){
            this.id = id;
            this.suffix = suffix;
            this.biomes = Arrays.asList(biomes);
        }

        protected int getId() {
            return id;
        }

        public String getSuffix() {
            return suffix;
        }

        public static Corrupter.Type byId(int p_28813_) {
            if (p_28813_ < 0 || p_28813_ > BY_ID.length) {
                p_28813_ = 0;
            }

            return BY_ID[p_28813_];
        }

        public static Corrupter.Type byBiome(Optional<ResourceKey<Biome>> p_28819_) {
            if(p_28819_.isPresent()){
                for(Corrupter.Type type : Type.BY_ID){
                    if(type.biomes.contains(p_28819_.get())) return type;
                }
            }
            return DENSE_DESERT;
        }


    }
}
