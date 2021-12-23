package com.lucca.mohard.data;


import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.init.ModBlocks;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlocksTagProvider extends BlockTagsProvider {


    public ModBlocksTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        //tag(ModTags.Blocks.STORAGE_BLOCKS_VILIO).add(ModBlocks.ALTAR_BLOCK);
        tag(BlockTags.VALID_SPAWN).add(ModBlocks.BLOODY_SAND,
                ModBlocks.DENSE_SAND,
                ModBlocks.CHISELED_HEAVY_SANDSTONE,
                ModBlocks.CUT_HEAVY_SANDSTONE,
                ModBlocks.SMOOTH_HEAVY_SANDSTONE,
                ModBlocks.HEAVY_SANDSTONE,
                ModBlocks.COPPERED_DEEPSLATE_BLOCK
        );
        tag(BlockTags.SAND).add(ModBlocks.BLOODY_SAND, ModBlocks.DENSE_SAND);
        tag(BlockTags.SLABS).add(ModBlocks.HEAVY_SANDSTONE_SLAB,
                ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB,
                ModBlocks.CUT_HEAVY_SANDSTONE_SLAB,
                ModBlocks.DENSE_SPRUCE_SLAB);
        tag(BlockTags.STAIRS).add(ModBlocks.HEAVY_SANDSTONE_STAIRS,
                ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS,
                ModBlocks.DENSE_SPRUCE_STAIRS);
        tag(BlockTags.WALLS).add(ModBlocks.HEAVY_SANDSTONE_WALL);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALTAR_BLOCK)
                .add(ModBlocks.ESSENCE_EXHANGER_BLOCK)
                .add(ModBlocks.HEAVY_SANDSTONE)
                .add(ModBlocks.SMOOTH_HEAVY_SANDSTONE)
                .add(ModBlocks.CUT_HEAVY_SANDSTONE)
                .add(ModBlocks.CHISELED_HEAVY_SANDSTONE)
                .add(ModBlocks.HEAVY_SANDSTONE_SLAB)
                .add(ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB)
                .add(ModBlocks.CUT_HEAVY_SANDSTONE_SLAB)
                .add(ModBlocks.HEAVY_SANDSTONE_STAIRS)
                .add(ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS)
                .add(ModBlocks.HEAVY_SANDSTONE_WALL)
                .add(ModBlocks.COPPERED_DEEPSLATE_BLOCK);

        tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DENSE_SPRUCE_FENCE).add(ModBlocks.DENSE_SPRUCE_FENCE_GATE);

        tag(BlockTags.LOGS).add(ModBlocks.DENSE_SPRUCE_LOG);
        tag(BlockTags.LOGS_THAT_BURN).add(ModBlocks.DENSE_SPRUCE_LOG);
        tag(BlockTags.SAPLINGS).add(ModBlocks.DENSE_SPRUCE_SAPLING);
        tag(BlockTags.LEAVES).add(ModBlocks.DENSE_SPRUCE_LEAVES);
        tag(BlockTags.PLANKS).add(ModBlocks.DENSE_SPRUCE_PLANKS);
        tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.DENSE_SPRUCE_STAIRS);
        tag(BlockTags.WOODEN_SLABS).add(ModBlocks.DENSE_SPRUCE_SLAB);
        tag(BlockTags.WOODEN_FENCES).add(ModBlocks.DENSE_SPRUCE_STAIRS);
        tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.DENSE_SPRUCE_TRAPDOOR);
        tag(BlockTags.WOODEN_DOORS).add(ModBlocks.DENSE_SPRUCE_DOOR);
        tag(BlockTags.DOORS).add(ModBlocks.DENSE_SPRUCE_DOOR);
        tag(BlockTags.FENCES).add(ModBlocks.DENSE_SPRUCE_FENCE);
        tag(BlockTags.FENCE_GATES).add(ModBlocks.DENSE_SPRUCE_FENCE_GATE);


        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.ALTAR_BLOCK)
                .add(ModBlocks.ESSENCE_EXHANGER_BLOCK)
                .add(ModBlocks.COPPERED_DEEPSLATE_BLOCK);

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.BLOODY_SAND).add(ModBlocks.DENSE_SAND);


    }



}
