package com.lucca.mohard.effects;

import com.lucca.mohard.setup.init.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class StunningEffect extends MobEffect {


    public StunningEffect() {
        super(MobEffectCategory.HARMFUL, 4411786);
    }


    @SubscribeEvent
    public static void naoPula(LivingEvent.LivingJumpEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity.hasEffect(ModEffects.STUN.get())) {
            livingEntity.setDeltaMovement(0, -10, 0);

        }
    }

    /*


    @SubscribeEvent
    public static void dontMove(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity.hasEffect(ModEffects.STUN.get())) {
            if (!velocidade.containsKey(livingEntity)) {
                velocidade.put(livingEntity, livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
            }
        } else {
            if (velocidade.containsKey(livingEntity)) {
                livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(velocidade.get(livingEntity));
                velocidade.remove(livingEntity);
            }
        }
    }

     */


}
