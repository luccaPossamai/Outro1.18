package com.lucca.mohard.block;

import com.lucca.mohard.setup.init.ModBlocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DenseCactus extends Block implements IPlantable {

    public static PlantType DENSE_PLANTS = PlantType.get("dense");
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    protected static final VoxelShape OUTLINE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public DenseCactus(Properties p_i48435_1_) {
        super(p_i48435_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));

    }

    @Override
    public void tick(BlockState p_225534_1_, ServerLevel p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        if (!p_225534_2_.isAreaLoaded(p_225534_3_, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
        if (!p_225534_1_.canSurvive(p_225534_2_, p_225534_3_)) {
            p_225534_2_.destroyBlock(p_225534_3_, true);
        }

    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        BlockPos blockpos = p_225542_3_.above();
        if (p_225542_2_.isEmptyBlock(blockpos)) {
            int i;
            for(i = 1; p_225542_2_.getBlockState(p_225542_3_.below(i)).is(this); ++i) {
            }

            if (i < 3) {
                int j = p_225542_1_.getValue(AGE);
                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225542_2_, blockpos, p_225542_1_, true)) {
                    if (j == 15) {
                        p_225542_2_.setBlockAndUpdate(blockpos, this.defaultBlockState());
                        BlockState blockstate = p_225542_1_.setValue(AGE, Integer.valueOf(0));
                        p_225542_2_.setBlock(p_225542_3_, blockstate, 4);
                        blockstate.neighborChanged(p_225542_2_, blockpos, this, p_225542_3_, false);
                    } else {
                        p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(AGE, Integer.valueOf(j + 1)), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
                }
            }
        }
    }
    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos1) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, dir, state1, level, pos, pos1);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return OUTLINE_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate = p_196260_2_.getBlockState(p_196260_3_.relative(direction));
            Material material = blockstate.getMaterial();
            if (material.isSolid() || p_196260_2_.getFluidState(p_196260_3_.relative(direction)).is(FluidTags.LAVA)) {
                return false;
            }
        }
        BlockState blockstate1 = p_196260_2_.getBlockState(p_196260_3_.below());
        return this.canSustainPlant(blockstate1, p_196260_2_, p_196260_3_, Direction.UP, this) && !p_196260_2_.getBlockState(p_196260_3_.above()).getMaterial().isLiquid();
    }

    @Override
    public void entityInside(BlockState p_196262_1_, Level p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        p_196262_4_.hurt(DamageSource.CACTUS, 1.0F);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(AGE);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world,pos);

        if(type.equals(DENSE_PLANTS)){
            return state.getBlock() instanceof SandBlock || state.getBlock().equals(ModBlocks.DENSE_CACTUS);
        }
        return false;
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return DENSE_PLANTS;
    }
}
