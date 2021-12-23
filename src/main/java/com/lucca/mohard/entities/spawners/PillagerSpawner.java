package com.lucca.mohard.entities.spawners;

import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.PillagerOutpostFeature;
import net.minecraft.world.level.levelgen.feature.WoodlandMansionFeature;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PillagerSpawner {

    @SubscribeEvent
    public static void pillagerOutPostSpawn(StructureSpawnListGatherEvent event){

        if(event.getStructure() instanceof PillagerOutpostFeature){
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.GOLDEN_ARMORED_PILLAGER_ENTITY_TYPE, 1, 0, 1));
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 1, 1, 1));
        }

    }

    @SubscribeEvent
    public static void mansionSpawn(StructureSpawnListGatherEvent event){

        if(event.getStructure() instanceof WoodlandMansionFeature){
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.GOLDEN_ARMORED_VINDICATOR_ENTITY_TYPE, 1, 1, 1));
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.DIAMOND_ARMORED_VINDICATOR_ENTITY_TYPE, 1, 1, 1));
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.ENCHANTER_ILLAGER_ENTITY_TYPE, 1, 1, 1));
        }

    }
}
