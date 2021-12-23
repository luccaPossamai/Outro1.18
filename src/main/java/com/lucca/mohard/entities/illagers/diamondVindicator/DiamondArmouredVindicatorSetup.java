package com.lucca.mohard.entities.illagers.diamondVindicator;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DiamondArmouredVindicatorSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.DIAMOND_ARMORED_VINDICATOR.get(), DiamondArmoredVindicator.createAttributes().build());
    }



}
