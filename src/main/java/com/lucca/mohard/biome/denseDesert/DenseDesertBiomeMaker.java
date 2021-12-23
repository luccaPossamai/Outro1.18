package com.lucca.mohard.biome.denseDesert;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.biome.ModBiomeMethods;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DenseDesertBiomeMaker {


    public static final BlockState[] HEAVY_SANDSTONE_VARIANTS = {
            ModBlocks.HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.CUT_HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.CUT_HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.SMOOTH_HEAVY_SANDSTONE.defaultBlockState(),
            ModBlocks.SMOOTH_HEAVY_SANDSTONE.defaultBlockState(),
    };

    public static ConfiguredFeature<?, ?> PATCH_DENSE_CACTUS;
    public static PlacedFeature PATCH_DENSE_CACTUS_DENSE_DESERT;
    public static ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> VILLAGE_DESERT;




    private static int getSkyColor() {
        return 13348758;
    }

    private static int getSkyFogColor() {
        return 11243640;
    }

    private static int getWaterColor() {
        return 13151140;
    }

    private static int getWaterFogColor() {
        return 13151140;
    }
    private static int getGrassOverride(){
        return 5993311;
    }
    private static int getFoliageColorOverride(){
        return 4882277;
    }



    public static Biome denseDesertBiome() {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.CORRUPTER_ENTITY_TYPE, 40, 1, 2));
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addFossilDecoration(biomegenerationsettings$builder);
        ModBiomeMethods.globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomegenerationsettings$builder);
        return ModBiomeMethods.biome(Biome.Precipitation.NONE, Biome.BiomeCategory.DESERT, 2.0F, 0.0F,getWaterColor(), getWaterFogColor(), getSkyColor(), getSkyFogColor(),getGrassOverride(), getFoliageColorOverride(), mobspawnsettings$builder, biomegenerationsettings$builder, ModBiomeMethods.NORMAL_MUSIC);
    }



    public static void setupFeatures(){
        PATCH_DENSE_CACTUS  = FeatureUtils.register("patch_dense_cactus", Feature.RANDOM_PATCH.configured(FeatureUtils.simpleRandomPatchConfiguration(10, Feature.BLOCK_COLUMN.configured(BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(ModBlocks.DENSE_CACTUS))).placed(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(ModBlocks.DENSE_CACTUS.defaultBlockState(), BlockPos.ZERO)))))));
        PATCH_DENSE_CACTUS_DENSE_DESERT = PlacementUtils.register("patch_dense_cactus_dense_desert", VegetationFeatures.PATCH_CACTUS.placed(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    }


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MOD_ID, name), configuredFeature);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        if(event.getName().toString().contains("dense_desert")) {
            if(VILLAGE_DESERT != null) {
            }
        }
    }


    public static void setupStructureFeatures(){

        VILLAGE_DESERT = register("village_dense_desert", StructureFeature.VILLAGE.configured(new JigsawConfiguration(() -> DenseDesertVillagePools.START, 6)));

    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
    }



}
