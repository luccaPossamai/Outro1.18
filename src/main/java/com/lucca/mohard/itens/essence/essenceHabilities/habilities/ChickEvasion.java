package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ChickEvasion extends GenericEssenceHability {

    private double originalSpeed;

    public ChickEvasion(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        originalSpeed = this.player.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
        this.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(originalSpeed * 1.5);
        dashTowards();

        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
    }

    @Override
    public void stopEffects() {
        super.stopEffects();
        this.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(originalSpeed);
    }

    @Override
    public void tick() {
        super.tick();
        Level level = player.level;
        TargetingConditions pred = TargetingConditions.forCombat().range(10);
        for(Mob mob : level.getNearbyEntities(Mob.class, pred, player, player.getBoundingBox().inflate(10))){
            if (mob.getTarget() == player) {
                mob.setTarget(null);
            }
            if(mob.getLastHurtByMob() instanceof Player){
                mob.setLastHurtByMob(null);
                mob.setLastHurtByPlayer(null);
            }
        }
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }

    private void dashTowards(){
        float yRot = player.getYRot();
        float xRot = player.getXRot();
        float f1 = -Mth.sin(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
        float f2 = 0.5F;
        float f3 = Mth.cos(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 3.0F * ((2.0F) / 4.0F);
        f1 = f1 * (f5 / f4);
        f2 = f2 * (f5 / f4);
        f3 = f3 * (f5 / f4);
        player.push(-f1, f2, -f3);
    }

    @Override
    public void onHit(LivingEntity livingEntity) {
        super.onHit(livingEntity);
        this.stopEffects();
    }
}
