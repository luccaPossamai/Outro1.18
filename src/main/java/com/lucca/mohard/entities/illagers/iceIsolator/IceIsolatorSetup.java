package com.lucca.mohard.entities.illagers.iceIsolator;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IceIsolatorSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.ICE_ISOLATOR.get(), IceIsolator.createAttributes().build());
    }



}
