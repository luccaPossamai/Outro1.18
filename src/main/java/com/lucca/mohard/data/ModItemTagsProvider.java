package com.lucca.mohard.data;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
        super(gen, blockTags, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        //copy(ModTags.Blocks.STORAGE_BLOCKS_VILIO, ModTags.Items.STORAGE_BLOCKS_VILIO);
        //copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

        tag(ItemTags.MUSIC_DISCS).add(ModItens.MUSIC_DISC_BREATHE.get());
        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(ModItens.MUSIC_DISC_BREATHE.get());

    }
}
