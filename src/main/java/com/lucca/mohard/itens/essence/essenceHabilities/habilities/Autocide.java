package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Autocide extends GenericEssenceHability {

    public Autocide(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        this.player.hurt(DamageSource.MAGIC, this.player.getMaxHealth());
        Level level = this.player.level;
        TargetingConditions pred = TargetingConditions.forCombat().range(5);
        float dano = (float)(this.player.getAttributeBaseValue(ModAttributes.MAGIC_DAMAGE.get()) / 2) +
                (float)(this.player.getAttributeBaseValue(ModAttributes.PHYSICAL_DAMAGE.get()) / 2) +
                (float)(this.player.getAttributeBaseValue(ModAttributes.PROJECTILE_DAMAGE.get()) / 2);
        for(LivingEntity mob : level.getNearbyEntities(LivingEntity.class, pred, player, player.getBoundingBox().inflate(5))){
            mob.hurt(DamageSource.explosion(this.player), 20F + dano);
        }
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }

}
