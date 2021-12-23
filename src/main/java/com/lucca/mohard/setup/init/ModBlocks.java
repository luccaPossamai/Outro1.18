package com.lucca.mohard.setup.init;

import com.lucca.mohard.block.*;
import com.lucca.mohard.block.altar.AltarBlock;
import com.lucca.mohard.block.essenceExchanger.EssenceExchangerBlock;
import com.lucca.mohard.block.protector.Protector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModBlocks {

    public static void register(){}

    public static final AltarBlock ALTAR_BLOCK = new AltarBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
            .strength(22.5F,600)
            .sound(SoundType.NETHER_BRICKS)
            .requiresCorrectToolForDrops()
            );


    public static final EssenceExchangerBlock ESSENCE_EXHANGER_BLOCK = new EssenceExchangerBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
            .strength(22.5F,600)
            .sound(SoundType.NETHER_BRICKS));

    public static final Block HEAVY_SANDSTONE = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(0.8F));

    public static final Block CUT_HEAVY_SANDSTONE = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(0.8F));

    public static final Block SMOOTH_HEAVY_SANDSTONE =  new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    public static final Block CHISELED_HEAVY_SANDSTONE = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(0.8F));

    public static final SlabBlock HEAVY_SANDSTONE_SLAB = new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    public static final SlabBlock CUT_HEAVY_SANDSTONE_SLAB = new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    public static final SlabBlock SMOOTH_HEAVY_SANDSTONE_SLAB = new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    public static final StairBlock HEAVY_SANDSTONE_STAIRS = new StairBlock(HEAVY_SANDSTONE.defaultBlockState(), BlockBehaviour.Properties.copy(HEAVY_SANDSTONE));

    public static final StairBlock SMOOTH_HEAVY_SANDSTONE_STAIRS = new StairBlock(SMOOTH_HEAVY_SANDSTONE.defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_HEAVY_SANDSTONE));

    public static final SandBlock DENSE_SAND = new DenseSand(15714446, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND));

    public static final SandBlock BLOODY_SAND = new BloodySand(15714446, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND).speedFactor(0.2F).jumpFactor(0.7F));
    public static final WallBlock HEAVY_SANDSTONE_WALL = new WallBlock(BlockBehaviour.Properties.copy(HEAVY_SANDSTONE));

    public static final DenseCactus DENSE_CACTUS = new DenseCactus(BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).sound(SoundType.WOOL));

    public static final BushBlock CORRUPTED_BUSH = new CorruptedBerryBush(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH));

    public static final Block COPPERED_DEEPSLATE_BLOCK = new Protector(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER), 5);

    public static final Block DENSE_SPRUCE_LEAVES = leaves(SoundType.GRASS);

    public static final RotatedPillarBlock DENSE_SPRUCE_LOG = log(MaterialColor.PODZOL, MaterialColor.COLOR_RED);

    public static final Block DENSE_SPRUCE_PLANKS = new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final TrapDoorBlock DENSE_SPRUCE_TRAPDOOR = new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never));

    public static final StairBlock DENSE_SPRUCE_STAIRS = new StairBlock(DENSE_SPRUCE_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(DENSE_SPRUCE_PLANKS));

    public static final SlabBlock DENSE_SPRUCE_SLAB = new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final FenceGateBlock DENSE_SPRUCE_FENCE_GATE = new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, DENSE_SPRUCE_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final FenceBlock DENSE_SPRUCE_FENCE = new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, DENSE_SPRUCE_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final DoorBlock DENSE_SPRUCE_DOOR = new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, DENSE_SPRUCE_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());

    public static final SaplingBlock DENSE_SPRUCE_SAPLING = new SaplingBlock(new DenseSpruceTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));

    public static final Block POTTED_SPRUCE_SAPLING = new FlowerPotBlock(DENSE_SPRUCE_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion());


    private static LeavesBlock leaves(SoundType p_152615_) {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(p_152615_).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never));
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return (boolean)false;
    }
    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    private static RotatedPillarBlock log(MaterialColor p_50789_, MaterialColor p_50790_) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_50789_ : p_50790_;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

}
