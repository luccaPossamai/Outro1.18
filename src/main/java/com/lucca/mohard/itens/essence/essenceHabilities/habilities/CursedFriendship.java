package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModDamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CursedFriendship extends GenericEssenceHability {

    double maxHealthBaseValue;

    public CursedFriendship(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        this.maxHealthBaseValue = this.player.getMaxHealth();
        this.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealthBaseValue * 1.5F);
    }

    @Override
    public void stopEffects() {
        super.stopEffects();
        this.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.maxHealthBaseValue);
    }

    @Override
    public void tick() {
        super.tick();
        Level level = player.level;
        TargetingConditions pred = TargetingConditions.forCombat().range(5);
        double value = this.player.getMaxHealth() * 0.0005;
        for(LivingEntity mob : level.getNearbyEntities(LivingEntity.class, pred, player, player.getBoundingBox().inflate(5))){
            mob.hurt(ModDamageSources.playerMagicAttack(this.player, this.player, DirectEntityDamageSource.Type.CURSED_FRIENDSHIP), (float)value);
            this.player.heal((float)value);
        }
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }
}
