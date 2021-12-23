package com.lucca.mohard.setup.init;

import com.lucca.mohard.block.DenseCactus;
import com.lucca.mohard.block.altar.AltarBlock;
import com.lucca.mohard.block.essenceExchanger.EssenceExchangerBlock;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksRegistration {

    public static final RegistryObject<AltarBlock> ALTAR_BLOCK = register("altar", () ->
            ModBlocks.ALTAR_BLOCK
            , ModItemGroups.ESSENCE_TAB);

    public static final RegistryObject<EssenceExchangerBlock> ESSENCE_EXCHANGER = register("essence_exchanger", () ->
                    ModBlocks.ESSENCE_EXHANGER_BLOCK
            , ModItemGroups.ESSENCE_TAB);


    public static final RegistryObject<SandBlock> BLOODY_SAND = register("bloody_sand", () ->
            ModBlocks.BLOODY_SAND
            , ModItemGroups.DENSE_BLOCKS
    );


    public static final RegistryObject<SandBlock> DENSE_SAND = register("dense_sand", () ->
            ModBlocks.DENSE_SAND
            , ModItemGroups.DENSE_BLOCKS
    );

    public static final RegistryObject<Block> HEAVY_SANDSTONE = register("heavy_sandstone", () ->
           ModBlocks.HEAVY_SANDSTONE
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> CUT_HEAVY_SANDSTONE = register("cut_heavy_sandstone", () ->
            ModBlocks.CUT_HEAVY_SANDSTONE
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> SMOOTH_HEAVY_SANDSTONE = register("smooth_heavy_sandstone", () ->
           ModBlocks.SMOOTH_HEAVY_SANDSTONE
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> CHISELED_HEAVY_SANDSTONE = register("chiseled_heavy_sandstone", () ->
            ModBlocks.CHISELED_HEAVY_SANDSTONE
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<SlabBlock> HEAVY_SANDSTONE_SLAB = register("heavy_sandstone_slab", () ->
            ModBlocks.HEAVY_SANDSTONE_SLAB
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<SlabBlock> CUT_HEAVY_SANDSTONE_SLAB = register("cut_heavy_sandstone_slab", () ->
            ModBlocks.CUT_HEAVY_SANDSTONE_SLAB
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<SlabBlock> SMOOTH_HEAVY_SANDSTONE_SLAB = register("smooth_heavy_sandstone_slab", () ->
            ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<StairBlock> HEAVY_SANDSTONE_STAIRS = register("heavy_sandstone_stairs", () ->
            ModBlocks.HEAVY_SANDSTONE_STAIRS
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<StairBlock> SMOOTH_HEAVY_SANDSTONE_STAIRS = register("smooth_heavy_sandstone_stairs", () ->
            ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<WallBlock> HEAVY_SANDSTONE_WALL = register("heavy_sandstone_wall", () ->
            ModBlocks.HEAVY_SANDSTONE_WALL
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<DenseCactus> DENSE_CACTUS = register("dense_cactus", () ->
            ModBlocks.DENSE_CACTUS
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> COPPERED_DEEPSLATE_BLOCK = register("coppered_deepslate", () ->
                    ModBlocks.COPPERED_DEEPSLATE_BLOCK
            , ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_LEAVES = register("dense_spruce_leaves", () ->
            ModBlocks.DENSE_SPRUCE_LEAVES
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_LOG = register("dense_spruce_log", () ->
                    ModBlocks.DENSE_SPRUCE_LOG
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_PLANKS = register("dense_spruce_planks", () ->
                    ModBlocks.DENSE_SPRUCE_PLANKS
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_TRAPDOOR = register("dense_spruce_trapdoor", () ->
                    ModBlocks.DENSE_SPRUCE_TRAPDOOR
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_STAIRS = register("dense_spruce_stairs", () ->
                    ModBlocks.DENSE_SPRUCE_STAIRS
            ,ModItemGroups.DENSE_BLOCKS);


    public static final RegistryObject<Block> DENSE_SPRUCE_SLAB = register("dense_spruce_slab", () ->
                    ModBlocks.DENSE_SPRUCE_SLAB
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_FENCE = register("dense_spruce_fence", () ->
                    ModBlocks.DENSE_SPRUCE_FENCE
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_FENCE_GATE = register("dense_spruce_fence_gate", () ->
                    ModBlocks.DENSE_SPRUCE_FENCE_GATE
            ,ModItemGroups.DENSE_BLOCKS);

    public static final RegistryObject<Block> DENSE_SPRUCE_DOOR = register("dense_spruce_door", () ->
                    ModBlocks.DENSE_SPRUCE_DOOR
            ,ModItemGroups.DENSE_BLOCKS);







    public static void register(){
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String nome, Supplier<T> bloco){

        return Registration.BLOCOS.register(nome, bloco);
    }

    private static <T extends Block> RegistryObject<T> register(String nome, Supplier<T> bloco, @Nullable CreativeModeTab group){
        RegistryObject<T> ret = registerNoItem(nome, bloco);
        Item.Properties prop = new Item.Properties();
        if(group != null) {
            prop.tab(group);
        }
        Registration.ITENS.register(nome, ()->
                new BlockItem(ret.get(), prop));

        return ret;
    }

}
