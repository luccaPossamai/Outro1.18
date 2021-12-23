package com.lucca.mohard.biome.denseTaiga;

import com.google.common.collect.ImmutableList;
import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.biome.ModBiomeMethods;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

@Mod.EventBusSubscriber
public class DenseTaigaBiomeMaker {

    public static ConfiguredFeature<?, ?> PATCH_BERRY_BUSH;
    public static PlacedFeature PATCH_CORRUPTED_BERRY_COMMON;
    public static PlacedFeature PATCH_CORRUPTED_BERRY_RARE;

    public static ConfiguredFeature<TreeConfiguration, ?> DENSE_SPRUCE;
    public static PlacedFeature TREES_DENSE_TAIGA;
    public static PlacedFeature DENSE_PINE_CHECKED;
    public static ConfiguredFeature<TreeConfiguration, ?> DENSE_PINE;
    public static ConfiguredFeature<RandomFeatureConfiguration, ?> TREES_DENSE_TAIGA_FEATURE;
    public static PlacedFeature DENSE_SPRUCE_CHECKED;
    public static PlacedFeature TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA ;
    public static PlacedFeature TREES_OLD_GROWTH_DENSE_PINE_TAIGA;
    public static ConfiguredFeature<RandomFeatureConfiguration, ?> TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA_FEATURE;
    public static ConfiguredFeature<RandomFeatureConfiguration, ?> TREES_OLD_GROWTH_DENSE_PINE_TAIGA_FEATURE;
    public static PlacedFeature MEGA_DENSE_SPRUCE_CHECKED;
    public static PlacedFeature MEGA_DENSE_PINE_CHECKED;
    public static ConfiguredFeature<TreeConfiguration, ?> MEGA_DENSE_SPRUCE;
    public static ConfiguredFeature<TreeConfiguration, ?> MEGA_DENSE_PINE;

    private static int getSkyColor() {
        return 2701393;
    }

    private static int getSkyFogColor() {
        return 2707793;
    }

    private static int getWaterColor() {
        return 2441793;
    }

    private static int getWaterFogColor() {
        return 1127732;
    }

    private static int getGrassOverride(){
        return 5993311;
    }
    private static int getFoliageColorOverride(){
        return 4882277;
    }


    public static Biome denseTaigaBiome(boolean rareBerries) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.CORRUPTER_ENTITY_TYPE, 40, 1, 2));
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

        float f = rareBerries ? -0.5F : 0.25F;
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        ModBiomeMethods.globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addFerns(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        addDenseTaigaTrees(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addTaigaGrass(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        if (rareBerries) {
            addRareCorruptedBerryBushes(biomegenerationsettings$builder);
        } else {
            addRareCorruptedBerryBushes(biomegenerationsettings$builder);
        }

        return ModBiomeMethods.biome(rareBerries ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, f, rareBerries ? 0.4F : 0.8F, getWaterColor(), getWaterFogColor(), getSkyColor(), getSkyFogColor(),getGrassOverride(), getFoliageColorOverride(), mobspawnsettings$builder, biomegenerationsettings$builder, ModBiomeMethods.NORMAL_MUSIC);
    }

    public static Biome giantTreeDenseTaiga(boolean p_194877_) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.CORRUPTER_ENTITY_TYPE, 40, 1, 2));

        if (p_194877_) {
            BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        } else {
            BiomeDefaultFeatures.caveSpawns(mobspawnsettings$builder);
            BiomeDefaultFeatures.monsters(mobspawnsettings$builder, 100, 25, 100, false);
        }
        float f = 0.25F;
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        ModBiomeMethods.globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addFerns(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        addGiantDenseTaigaTrees(biomegenerationsettings$builder, p_194877_);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addGiantTaigaVegetation(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        addCommonCorruptedBerryBushes(biomegenerationsettings$builder);
        return ModBiomeMethods.biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, p_194877_ ? 0.25F : 0.3F, 0.8F, getWaterColor(), getWaterFogColor(), getSkyColor(), getSkyFogColor(),getGrassOverride(), getFoliageColorOverride(), mobspawnsettings$builder, biomegenerationsettings$builder, ModBiomeMethods.NORMAL_MUSIC);
    }




    public static void setupFeatures(){
        PATCH_BERRY_BUSH = FeatureUtils.register("patch_corrupted_berry_bush", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CORRUPTED_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3))))), List.of(Blocks.GRASS_BLOCK))));
        PATCH_CORRUPTED_BERRY_COMMON = PlacementUtils.register("patch_corrupted_berry_common", PATCH_BERRY_BUSH.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        PATCH_CORRUPTED_BERRY_RARE = PlacementUtils.register("patch_corrupted_berry_rare", PATCH_BERRY_BUSH.placed(RarityFilter.onAverageOnceEvery(384), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        DENSE_PINE = FeatureUtils.register("dense_pine", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LOG), new StraightTrunkPlacer(6, 4, 0), BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LEAVES), new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
        DENSE_PINE_CHECKED = PlacementUtils.register("dense_pine_checked", DENSE_PINE.filteredByBlockSurvival(ModBlocks.DENSE_SPRUCE_SAPLING));
        DENSE_SPRUCE = FeatureUtils.register("dense_spruce", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LEAVES), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build()));
        DENSE_SPRUCE_CHECKED = PlacementUtils.register("dense_spruce_checked", DENSE_SPRUCE.filteredByBlockSurvival(ModBlocks.DENSE_SPRUCE_SAPLING));
        TREES_DENSE_TAIGA_FEATURE = FeatureUtils.register("trees_dense_taiga", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(DENSE_PINE_CHECKED, 0.33333334F)), DENSE_SPRUCE_CHECKED)));
        TREES_DENSE_TAIGA = PlacementUtils.register("trees_dense_taiga", TREES_DENSE_TAIGA_FEATURE.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
        MEGA_DENSE_PINE = FeatureUtils.register("mega_dense_pine", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LEAVES), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 7)), new TwoLayersFeatureSize(1, 1, 2))).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build()));
        MEGA_DENSE_SPRUCE = FeatureUtils.register("mega_dense_spruce", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(ModBlocks.DENSE_SPRUCE_LEAVES), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2))).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build()));
        MEGA_DENSE_SPRUCE_CHECKED  = PlacementUtils.register("mega_dense_spruce_checked", MEGA_DENSE_SPRUCE.filteredByBlockSurvival(ModBlocks.DENSE_SPRUCE_SAPLING));
        MEGA_DENSE_PINE_CHECKED = PlacementUtils.register("mega_dense_pine_checked", MEGA_DENSE_PINE.filteredByBlockSurvival(ModBlocks.DENSE_SPRUCE_SAPLING));
        TREES_OLD_GROWTH_DENSE_PINE_TAIGA_FEATURE = FeatureUtils.register("trees_old_growth_dense_pine_taiga", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(MEGA_DENSE_SPRUCE_CHECKED, 0.025641026F), new WeightedPlacedFeature(MEGA_DENSE_PINE_CHECKED, 0.30769232F), new WeightedPlacedFeature(DENSE_PINE_CHECKED, 0.33333334F)), DENSE_SPRUCE_CHECKED)));
        TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA_FEATURE = FeatureUtils.register("trees_old_growth_dense_spruce_taiga", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(MEGA_DENSE_SPRUCE_CHECKED, 0.33333334F), new WeightedPlacedFeature(DENSE_PINE_CHECKED, 0.33333334F)), DENSE_SPRUCE_CHECKED)));
        TREES_OLD_GROWTH_DENSE_PINE_TAIGA = PlacementUtils.register("trees_old_growth_dense_pine_taiga", VegetationFeatures.TREES_OLD_GROWTH_PINE_TAIGA.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
        TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA = PlacementUtils.register("trees_old_growth_dense_spruce_taiga", VegetationFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));



    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {

    }

    public static void addGiantDenseTaigaTrees(BiomeGenerationSettings.Builder builder, boolean p_194877_){
        if(TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA != null && TREES_OLD_GROWTH_DENSE_PINE_TAIGA != null) {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, p_194877_ ? TREES_OLD_GROWTH_DENSE_SPRUCE_TAIGA : TREES_OLD_GROWTH_DENSE_PINE_TAIGA);
        }
    }
    public static void addDenseTaigaTrees(BiomeGenerationSettings.Builder p_126839_) {
        if (TREES_DENSE_TAIGA != null) {
            p_126839_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TREES_DENSE_TAIGA);
        }
    }
    public static void addRareCorruptedBerryBushes(BiomeGenerationSettings.Builder p_194736_) {
        if (PATCH_CORRUPTED_BERRY_COMMON != null) {
            p_194736_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_CORRUPTED_BERRY_COMMON);
        }
    }

    public static void addCommonCorruptedBerryBushes(BiomeGenerationSettings.Builder p_194738_) {
        if(PATCH_CORRUPTED_BERRY_RARE != null) {
            p_194738_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_CORRUPTED_BERRY_RARE);
        }
    }
    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MOD_ID, name), configuredFeature);
    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
    }


}
