package com.lucca.mohard.effects.corruption;

import com.lucca.mohard.setup.init.ModEffects;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import static com.lucca.mohard.effects.corruption.CorruptionMechanics.*;

public class CorruptionEffect extends MobEffect {


    public CorruptionEffect() {
        super(MobEffectCategory.HARMFUL, 5060690);
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amp) {
        return duration < 10 ;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amp) {
        super.applyEffectTick(entity, amp);
        if(entity instanceof Player){
            if((Math.random() * 100) < 1){
                entity.level.addFreshEntity(CorruptionMechanics.generateCorruptedPlayer((Player) entity));
                entity.hurt(DamageSource.MAGIC.bypassMagic().bypassArmor(), entity.getMaxHealth() * 99999);
            }
            } else if (convertible(entity.getType()) && entity instanceof Mob) {
            Mob mob = (Mob) entity;
            ItemStack itemInHand = mob.getMainHandItem();
            ItemStack itemInOffHand = mob.getOffhandItem();
            Entity newEntity = mob.convertTo(getCorruptedVariant(entity.getType()), false);
            newEntity.setItemSlot(EquipmentSlot.MAINHAND, itemInHand);
            newEntity.setItemSlot(EquipmentSlot.OFFHAND, itemInOffHand);
            entity.removeEffect(this);
            ItemEntity itementity = new ItemEntity(entity.level, entity.getX() + entity.getLookAngle().x, entity.getY() + 1.0D, entity.getZ() + entity.getLookAngle().z, new ItemStack(ModItens.TRUE_VOID_REMNANTS.get(), 1));
            entity.level.addFreshEntity(itementity);
        }
        entity.removeEffect(ModEffects.CORRUPTION.get());
    }

}
