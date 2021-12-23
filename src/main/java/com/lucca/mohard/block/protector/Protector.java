package com.lucca.mohard.block.protector;

import com.lucca.mohard.mechanics.blocks.BlockBreak;
import com.lucca.mohard.setup.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class Protector extends BaseEntityBlock {

    private ProtectorTileEntity tileEntity;
    protected final int range;

    public Protector(Properties p_49795_, int range) {
        super(p_49795_);
        this.range = range;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state0, boolean p_60570_) {
        super.onPlace(state, level, pos, state0, p_60570_);
        tileEntity.protect();
    }


    @Override
    public void onRemove(BlockState p_60515_, Level level, BlockPos pos, BlockState p_60518_, boolean p_60519_) {
        super.onRemove(p_60515_, level, pos, p_60518_, p_60519_);
        List<BlockPos> protectorsPos = new ArrayList<>();
        int rangeAl2 = this.range - 2;
        int range2 = 2 * this.range;
        for (int i = rangeAl2; i <= rangeAl2; i++) {
            for (int j = range2; j <= range2; j++) {
                for (int k = -rangeAl2; k <= rangeAl2; k++) {
                    BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                    if(level.getBlockState(pos1).getBlock() instanceof Protector){
                        Protector protector = (Protector) level.getBlockState(pos1).getBlock();
                        protectorsPos.addAll(protector.tileEntity.privateProtectedBlocks);
                    }
                }
            }
        }
        for(BlockPos state : this.tileEntity.privateProtectedBlocks){
            if(BlockBreak.protectedBlocks.contains(state) && !protectorsPos.contains(state)) BlockBreak.protectedBlocks.remove(state);
        }

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModTileEntityTypes.PROTECTOR.get(), ProtectorTileEntity::serverTick);

    }

    @Override
    public RenderShape getRenderShape(BlockState p_54296_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        tileEntity = new ProtectorTileEntity(p_153215_, p_153216_);
        return tileEntity;
    }
}
