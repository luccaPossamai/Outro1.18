package com.lucca.mohard.entities.illagers.enchanter;

import com.lucca.mohard.help.Methods;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;


import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.raid.Raider;


public class EnchanterEntity extends SpellcasterIllager {

    protected EnchanterEntity(EntityType<? extends SpellcasterIllager> p_i48551_1_, Level p_i48551_2_) {
        super(p_i48551_1_, p_i48551_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new EnchanterEntity.CastingSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(4, new EnchanterEntity.SummonSpellGoal());
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));

    }

    @Override
    protected SoundEvent getCastingSoundEvent() {
        //TODO sound
        return SoundEvents.EVOKER_CAST_SPELL;
    }

    @Override
    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {}

    @Override
    public SoundEvent getCelebrateSound() {
        //TODO
        return SoundEvents.EVOKER_CELEBRATE;
    }

    @Override
    public IllagerArmPose getArmPose() {
        if (this.isCastingSpell()) {
            return AbstractIllager.IllagerArmPose.SPELLCASTING;
        } else {
            return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
        }
    }


    //TODO 2 CLASS

    class CastingSpellGoal extends SpellcasterIllager.SpellcasterCastingSpellGoal {
        private CastingSpellGoal() {
        }

        public void tick() {
            if (EnchanterEntity.this.getTarget() != null) {
                EnchanterEntity.this.getLookControl().setLookAt(EnchanterEntity.this.getTarget(), (float)EnchanterEntity.this.getMaxHeadYRot(), (float)EnchanterEntity.this.getMaxHeadXRot());
            }

        }
    }

    class SummonSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        private final TargetingConditions buffedEntity = (TargetingConditions.forCombat()).range(10.0D).ignoreInvisibilityTesting();
        private final TargetingConditions player = (TargetingConditions.forCombat().range(10.0D));

        private SummonSpellGoal() {
        }

        public boolean canUse() {
            if (!super.canUse()) {
                return false;
            } else {
                int i = EnchanterEntity.this.level.getNearbyEntities(Mob.class, this.buffedEntity, EnchanterEntity.this, EnchanterEntity.this.getBoundingBox().inflate(10.0D)).size();
                int j = EnchanterEntity.this.level.getNearbyEntities(Player.class, this.player, EnchanterEntity.this, EnchanterEntity.this.getBoundingBox().inflate(10.0D)).size();

                return i > 0 && j > 0;
            }
        }

        protected int getCastingTime() {
            return 60;
        }

        protected int getCastingInterval() {
            return 600;
        }

        protected void performSpellCasting() {
            ServerLevel serverworld = (ServerLevel)EnchanterEntity.this.level;

            for(Mob entity : serverworld.getNearbyEntities(Mob.class, this.buffedEntity, EnchanterEntity.this, EnchanterEntity.this.getBoundingBox().inflate(10.0D))){
                EnchanterEntity.this.setTarget(entity);
                Attribute att = Methods.getRandomAttribute();
                double addValue = EnchanterEntity.this.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get());
                entity.getAttribute(att).setBaseValue(entity.getAttributeValue(att) + addValue);
                break;
            }


        }



        protected SoundEvent getSpellPrepareSound() {
            //TODO
            return SoundEvents.EVOKER_PREPARE_SUMMON;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return IllagerSpell.FANGS;
        }
    }




}
