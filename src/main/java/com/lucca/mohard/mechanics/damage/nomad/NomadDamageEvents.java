package com.lucca.mohard.mechanics.damage.nomad;

import com.lucca.mohard.entities.villagers.nomad.NomadEntity;
import com.lucca.mohard.evolution.GameLevel;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class NomadDamageEvents {


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void damageTaken(LivingHurtEvent event){
        if(event.getEntity() instanceof NomadEntity){
            NomadEntity nomadEntity = (NomadEntity) event.getEntity();
            double level = GameLevel.getLevel(nomadEntity.getX(),nomadEntity.getZ(), nomadEntity.level);
            event.setAmount(event.getAmount() / (600 - (float) level));
        }

    }


}
