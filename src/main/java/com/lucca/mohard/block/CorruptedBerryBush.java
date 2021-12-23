package com.lucca.mohard.block;

import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.PlantType;

import java.util.Random;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;


public class CorruptedBerryBush extends BushBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final FoodProperties CORRUPTED_BERRY = new FoodProperties.Builder().nutrition(1).saturationMod(2).build();

    public CorruptedBerryBush(Properties p_i48437_1_) {
        super(p_i48437_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(ModItens.CORRUPTED_BERRY);
    }



    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        if (p_220053_1_.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        } else {
            return p_220053_1_.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(p_220053_1_, p_220053_2_, p_220053_3_, p_220053_4_);
        }
    }

    @Override
    public Item asItem() {
        return super.asItem();
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(ModItens.CORRUPTED_BERRY);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_) {
        return p_149653_1_.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        int i = p_225542_1_.getValue(AGE);
        if (i < 3 && p_225542_2_.getRawBrightness(p_225542_3_.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225542_2_, p_225542_3_, p_225542_1_,p_225542_4_.nextInt(5) == 0)) {
            p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(AGE, Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
        }
    }
    @Override
    public void entityInside(BlockState p_196262_1_, Level p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        if (p_196262_4_ instanceof LivingEntity) {
            int i  = p_196262_1_.getValue(AGE);
            if(i >= 2){
                p_196262_4_.makeStuckInBlock(p_196262_1_, new Vec3((double)0.0F, 0.75D, (double)0.0F));
            } else if(i < 2 && i >= 0){
                p_196262_4_.makeStuckInBlock(p_196262_1_, new Vec3((double)0.8F, 0.75D, (double)0.8F));
            }

        }
    }
    @Override
    public InteractionResult use(BlockState p_225533_1_, Level p_225533_2_, BlockPos p_225533_3_, Player p_225533_4_, InteractionHand p_225533_5_, BlockHitResult p_225533_6_) {
        int i = p_225533_1_.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && p_225533_4_.getItemInHand(p_225533_5_).getItem() == Items.BONE_MEAL) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + p_225533_2_.random.nextInt(2);
            popResource(p_225533_2_, p_225533_3_, new ItemStack(ModItens.CORRUPTED_BERRY, j + (flag ? 1 : 0)));
            p_225533_2_.playSound((Player)null, p_225533_3_, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + p_225533_2_.random.nextFloat() * 0.4F);
            p_225533_2_.setBlock(p_225533_3_, p_225533_1_.setValue(AGE, Integer.valueOf(1)), 2);
            return InteractionResult.sidedSuccess(p_225533_2_.isClientSide);
        } else {
            return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(AGE);
    }


    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return DenseCactus.DENSE_PLANTS;
    }
}
