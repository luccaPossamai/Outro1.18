package com.lucca.mohard.entities.illagers.goldenVindicator;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoldenArmouredVindicatorSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.GOLDEN_ARMORED_VINDICATOR.get(), GoldenArmoredVindicator.createAttributes().build());
    }



}
