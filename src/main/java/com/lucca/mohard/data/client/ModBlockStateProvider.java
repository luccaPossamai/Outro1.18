package com.lucca.mohard.data.client;


import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.init.ModBlockStateProperties;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.stream.IntStream;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExampleMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(ModBlocks.ALTAR_BLOCK, models().cubeBottomTop("altar",
                mcLoc("mohard:block/altar_side"),
                mcLoc("mohard:block/altar_bottom"),
                mcLoc("mohard:block/altar_top")));

        simpleBlock(ModBlocks.CUT_HEAVY_SANDSTONE, models().cubeBottomTop("cut_heavy_sandstone",
                mcLoc("mohard:block/cut_heavy_sandstone"),
                mcLoc("mohard:block/heavy_sandstone_bottom"),
                mcLoc("mohard:block/heavy_sandstone_top")));

        simpleBlock(ModBlocks.HEAVY_SANDSTONE, models().cubeBottomTop("heavy_sandstone",
                mcLoc("mohard:block/heavy_sandstone"),
                mcLoc("mohard:block/heavy_sandstone_bottom"),
                mcLoc("mohard:block/heavy_sandstone_top")));


        simpleBlock(ModBlocks.CHISELED_HEAVY_SANDSTONE, models().cubeBottomTop("chiseled_heavy_sandstone",
                mcLoc("mohard:block/chiseled_heavy_sandstone"),
                mcLoc("mohard:block/heavy_sandstone_bottom"),
                mcLoc("mohard:block/heavy_sandstone_top")));

        simpleBlock(ModBlocks.DENSE_CACTUS, models().cubeBottomTop("dense_cactus",
                mcLoc("mohard:block/dense_cactus_side"),
                mcLoc("mohard:block/dense_cactus_bottom"),
                mcLoc("mohard:block/dense_cactus_top")));




        ConfiguredModel[] corruptedBerryFiles = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> {
                    String varName = "corrupted_berry_bush_" + i;
                    return models().cross(varName, mcLoc("mohard:block/"+ varName));
                })
                .map(ConfiguredModel::new).toArray(ConfiguredModel[]::new);
        int i = 0;
        for(ConfiguredModel model : corruptedBerryFiles) {
            getVariantBuilder(ModItens.CORRUPTED_BERRY.getBlock()).partialState().with(BlockStateProperties.AGE_3, i).setModels(model);
            i++;
        }


        ConfiguredModel[] essenceExchangerFiles = IntStream.rangeClosed(0, 3)
                .mapToObj(j -> {
                    String varName = "essence_exchanger_" + j;
                    if(j == 0) {
                        return models().cubeBottomTop(varName, mcLoc("mohard:block/essence_exchanger_side_0"),
                                mcLoc("mohard:block/essence_exchanger_bottom"),
                                mcLoc("mohard:block/essence_exchanger_top_off"));
                    } else {
                        return models().cubeBottomTop(varName, mcLoc("mohard:block/essence_exchanger_side_"+j),
                                mcLoc("mohard:block/essence_exchanger_bottom"),
                                mcLoc("mohard:block/essence_exchanger_top"));
                    }
                })
                .map(ConfiguredModel::new).toArray(ConfiguredModel[]::new);
        int j = 0;
        for(ConfiguredModel model : essenceExchangerFiles) {
            getVariantBuilder(ModBlocks.ESSENCE_EXHANGER_BLOCK).partialState().with(ModBlockStateProperties.ESSENCE_EXCHANGER_CHARGES, j).setModels(model);
            j++;

        }





        simpleBlock(ModBlocks.SMOOTH_HEAVY_SANDSTONE, models().cubeAll("smooth_heavy_sandstone", mcLoc("mohard:block/heavy_sandstone_top")));
        simpleBlock(ModBlocks.BLOODY_SAND, models().cubeAll("bloody_sand", mcLoc("mohard:block/bloody_sand")));

        simpleBlock(ModBlocks.DENSE_SAND, models().cubeAll("dense_sand", mcLoc("mohard:block/dense_sand")));

        simpleBlock(ModBlocks.DENSE_SPRUCE_LEAVES, models().withExistingParent("mohard:block/dense_spruce_leaves", "minecraft:block/leaves").texture("all","mohard:block/dense_spruce_leaves"));
        simpleBlock(ModBlocks.DENSE_SPRUCE_PLANKS, models().cubeAll("dense_spruce_planks", new ResourceLocation("mohard:block/dense_spruce_planks")));
        stairsBlock(ModBlocks.DENSE_SPRUCE_STAIRS, "dense_spruce", mcLoc("mohard:block/dense_spruce_planks"));
        slabBlock(ModBlocks.DENSE_SPRUCE_SLAB, mcLoc("mohard:block/dense_spruce_planks"), mcLoc("mohard:block/dense_spruce_planks"), mcLoc("mohard:block/dense_spruce_planks"), mcLoc("mohard:block/dense_spruce_planks") );
        fenceBlock(ModBlocks.DENSE_SPRUCE_FENCE, "dense_spruce", mcLoc("mohard:block/dense_spruce_planks"));

        fenceGateBlock(ModBlocks.DENSE_SPRUCE_FENCE_GATE, "dense_spruce", mcLoc("mohard:block/dense_spruce_planks"));
        trapdoorBlock(ModBlocks.DENSE_SPRUCE_TRAPDOOR, "dense_spruce", mcLoc("mohard:block/dense_spruce_trapdoor"), true);

        doorBlock(ModBlocks.DENSE_SPRUCE_DOOR, "dense_spruce", mcLoc("mohard:block/dense_spruce_door_bottom"), mcLoc("mohard:block/dense_spruce_door_top"));
        logBlock(ModBlocks.DENSE_SPRUCE_LOG);

        simpleBlock(ModBlocks.DENSE_SPRUCE_SAPLING, models().cross("dense_spruce_sapling", mcLoc("mohard:block/dense_spruce_sapling")));

        simpleBlock(ModBlocks.COPPERED_DEEPSLATE_BLOCK, models().cubeAll("coppered_deepslate", mcLoc("mohard:block/coppered_deepslate")));


        slabBlock(ModBlocks.HEAVY_SANDSTONE_SLAB,  mcLoc("mohard:block/heavy_sandstone"), mcLoc("mohard:block/heavy_sandstone"), mcLoc("mohard:block/heavy_sandstone_bottom"), mcLoc("mohard:block/heavy_sandstone_top"));
        slabBlock(ModBlocks.CUT_HEAVY_SANDSTONE_SLAB,  mcLoc("mohard:block/cut_heavy_sandstone"), mcLoc("mohard:block/cut_heavy_sandstone"), mcLoc("mohard:block/heavy_sandstone_bottom"), mcLoc("mohard:block/heavy_sandstone_top"));
        slabBlock(ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB,  mcLoc("mohard:block/smooth_heavy_sandstone"), mcLoc("mohard:block/heavy_sandstone_top"));

        stairsBlock(ModBlocks.HEAVY_SANDSTONE_STAIRS,  "heavy_sandstone", mcLoc("mohard:block/heavy_sandstone"), mcLoc("mohard:block/heavy_sandstone_top"), mcLoc("mohard:block/heavy_sandstone_bottom"));
        stairsBlock(ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS,  "smooth_heavy_sandstone", mcLoc("mohard:block/heavy_sandstone_top"));

        wallBlock(ModBlocks.HEAVY_SANDSTONE_WALL, "heavy_sandstone", mcLoc("mohard:block/heavy_sandstone"));

    }


}
