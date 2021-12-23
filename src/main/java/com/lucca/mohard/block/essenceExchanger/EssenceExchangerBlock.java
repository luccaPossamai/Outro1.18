package com.lucca.mohard.block.essenceExchanger;

import com.lucca.mohard.setup.init.ModBlockStateProperties;
import com.lucca.mohard.setup.init.ModItens;
import com.lucca.mohard.setup.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;


public class EssenceExchangerBlock extends BaseEntityBlock {


    public static final IntegerProperty CHARGE = ModBlockStateProperties.ESSENCE_EXCHANGER_CHARGES;
    private static EssenceExchangerTileEntity tileEntity;

    public EssenceExchangerBlock(Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(CHARGE, Integer.valueOf(0)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        tileEntity = new EssenceExchangerTileEntity(p_153215_, p_153216_);
        return tileEntity;
    }

    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152694_, BlockState p_152695_, BlockEntityType<T> p_152696_) {
        return p_152694_.isClientSide ? null : createTickerHelper(p_152696_, ModTileEntityTypes.ESSENCE_EXCHANGER.get(), EssenceExchangerTileEntity::serverTick);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level world, BlockPos pos, Player player, InteractionHand p_60507_, BlockHitResult p_60508_) {

        if(world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof EssenceExchangerTileEntity && player instanceof ServerPlayer) {
                EssenceExchangerTileEntity essenceExchangerTileEntity = (EssenceExchangerTileEntity) tileentity;
                if(player.getItemInHand(p_60507_).is(ModItens.TRUE_VOID_REMNANTS.get()) &&
                canBeCharged(p_60503_)) {
                    charge(world, pos, p_60503_);
                    player.getItemInHand(p_60507_).shrink(1);

                } else {
                    player.openMenu(essenceExchangerTileEntity);
                }
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        super.createBlockStateDefinition(p_49915_);
        p_49915_.add(ModBlockStateProperties.ESSENCE_EXCHANGER_CHARGES);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_54296_) {
        return RenderShape.MODEL;
    }

    public static void charge(Level p_55856_, BlockPos p_55857_, BlockState p_55858_) {
        p_55856_.setBlock(p_55857_, p_55858_.setValue(CHARGE, Integer.valueOf(p_55858_.getValue(CHARGE) + 1)), 3);
        p_55856_.playSound((Player)null, (double)p_55857_.getX() + 0.5D, (double)p_55857_.getY() + 0.5D, (double)p_55857_.getZ() + 0.5D, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void uncharge(Level p_55856_, BlockPos p_55857_, BlockState p_55858_) {
        p_55856_.setBlock(p_55857_, p_55858_.setValue(CHARGE, Integer.valueOf(p_55858_.getValue(CHARGE) - 1)), 3);
        p_55856_.playSound((Player)null, (double)p_55857_.getX() + 0.5D, (double)p_55857_.getY() + 0.5D, (double)p_55857_.getZ() + 0.5D, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static boolean canBeCharged(BlockState state){
        return state.getValue(CHARGE) < 3;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean bool) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = world.getBlockEntity(pos);
            if (blockentity instanceof EssenceExchangerTileEntity) {
                Containers.dropContents(world, pos, (EssenceExchangerTileEntity)blockentity);
            }
        }
        super.onRemove(state, world, pos, newState, bool);

    }
}