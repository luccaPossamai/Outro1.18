package com.lucca.mohard.block.protector;

import com.lucca.mohard.mechanics.blocks.BlockBreak;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ProtectorTileEntity extends BlockEntity {

    protected List<BlockPos> privateProtectedBlocks = new ArrayList<>();
    protected final int range;
    private int countToUpdate = 0;

    public ProtectorTileEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModTileEntityTypes.PROTECTOR.get(), p_155229_, p_155230_);
        this.range = p_155230_.getBlock() instanceof Protector ? ((Protector) p_155230_.getBlock()).range : 0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ProtectorTileEntity essenceExchangerTileEntity) {
        if(essenceExchangerTileEntity.countToUpdate == 0){
            essenceExchangerTileEntity.countToUpdate++;
            essenceExchangerTileEntity.protect();
        }
    }



    protected void protect() {
        BlockPos pos = this.getBlockPos();
        Level level = this.getLevel();
        int rangeAl = this.range - 2;

        for (int i = -rangeAl; i <= rangeAl; i++) {
            for (int j = 0; j <= range; j++) {
                for (int k = -rangeAl; k <= rangeAl; k++) {
                    BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                    if (pos1.getX() != pos.getX() || pos1.getY() != pos.getY() || pos1.getZ() != pos.getZ()) {
                        if (!BlockBreak.protectedBlocks.contains(pos1) && pos1 != pos && !level.getBlockState(pos1).is(ModBlocks.COPPERED_DEEPSLATE_BLOCK)) {
                            BlockBreak.protectedBlocks.add(pos1);
                            privateProtectedBlocks.add(pos1);
                        }
                    }
                }
            }
        }

    }

}
