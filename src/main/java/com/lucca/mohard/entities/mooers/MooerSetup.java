package com.lucca.mohard.entities.mooers;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MooerSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.MOOER.get(), MooerEntity.createAttributes().build());
        event.put(ModEntities.GOLDEN_MOOER.get(), MooerEntity.createAttributes().build());
        event.put(ModEntities.MOOSHROOM_MOOER.get(), MooerEntity.createAttributes().build());
    }

}
