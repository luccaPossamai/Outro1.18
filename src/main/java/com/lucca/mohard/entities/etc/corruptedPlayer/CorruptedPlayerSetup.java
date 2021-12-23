package com.lucca.mohard.entities.etc.corruptedPlayer;

import com.lucca.mohard.entities.etc.skelly.Skelly;
import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CorruptedPlayerSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.CORRUPTED_PLAYER.get(), CorruptedPlayer.createAttributes().build());
    }
}
