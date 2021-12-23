package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModDamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ContemplationScream extends GenericEssenceHability {

    public ContemplationScream(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
    }

    @Override
    public void tick() {
        super.tick();
        Level level = player.level;
        TargetingConditions pred = TargetingConditions.forCombat().range(10);
        for(LivingEntity mob : level.getNearbyEntities(LivingEntity.class, pred, player, player.getBoundingBox().inflate(10))){
            if(mob instanceof  Mob) {
                Mob mob1 = (Mob) mob;
                if (mob1.getTarget() != player) {
                    mob1.setTarget(player);
                }
                if (mob.getLastHurtByMob() instanceof Player) {
                    mob.setLastHurtByMob(player);
                    mob.setLastHurtByPlayer(player);
                }
            }
            mob.hurt(ModDamageSources.playerMagicAttack(this.player,this.player, DirectEntityDamageSource.Type.CONTEMPLATION_SCREAM), 2F + (float)this.player.getAttributeBaseValue(ModAttributes.MAGIC_DAMAGE.get()) / 25);
            if(!mob.hasEffect(MobEffects.WEAKNESS)) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, 0));
            }
        }
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }
}
