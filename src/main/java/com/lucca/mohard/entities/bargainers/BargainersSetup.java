package com.lucca.mohard.entities.bargainers;

import com.lucca.mohard.setup.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BargainersSetup {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){

        event.put(ModEntities.GASPI_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.GASPI).build());

        event.put(ModEntities.CUSPILE_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.CUSPILE).build());

        event.put(ModEntities.TUSJUS_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.TUSJUS).build());

        event.put(ModEntities.OEL_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.OEL).build());

        event.put(ModEntities.NIIPPA_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.NIIPPA).build());

        event.put(ModEntities.SAATPON_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.SAATPON).build());

        event.put(ModEntities.PABITTAS_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.PABITTAS).build());

        event.put(ModEntities.NOJAS_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.NOJAS).build());

        event.put(ModEntities.AAN_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.AAN).build());

        event.put(ModEntities.HOCINZAL_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.HO_CINSAL).build());

        event.put(ModEntities.CINVET_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.CINVET).build());

        event.put(ModEntities.TASBEGO_ENTITY_TYPE.get() , BargainersEntity.createAttributes(BargainersType.TASBEGO).build());
    }
}
