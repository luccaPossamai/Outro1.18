package com.lucca.mohard.entities.illagers.goldenPillager;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoldenArmouredPillagerSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.GOLDEN_ARMORED_PILLAGER.get(), GoldenArmoredPillager.createAttributes().build());
    }

}
