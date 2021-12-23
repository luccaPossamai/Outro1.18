package com.lucca.mohard.setup.init;


import com.lucca.mohard.ExampleMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class ModTags {
    public static final class Blocks{

        private static Tag.Named<Block> forge(String path){
            return BlockTags.bind(new ResourceLocation("forge", path).toString());

        }

        private static Tag.Named<Block> mod(String path){
            return BlockTags.bind(new ResourceLocation(ExampleMod.MOD_ID ,path).toString());

        }

    }

    public static final class Items{
        public static final Tag.Named<Item> STORAGE_BLOCKS_VILIO = forge("storage_blocks/vilio");

        private static Tag.Named<Item> forge(String path){
            return ItemTags.bind(new ResourceLocation("forge" ,path).toString());

        }

        private static Tag.Named<Item> mod(String path){
            return ItemTags.bind(new ResourceLocation(ExampleMod.MOD_ID ,path).toString());


        }
    }
}
