package com.lucca.mohard.block;

import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DenseSand extends SandBlock {

    public DenseSand(int p_55967_, Properties p_55968_) {
        super(p_55967_, p_55968_);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        ItemStack itemStack = p_60506_.getItemInHand(p_60507_);
        if(itemStack.getItem().equals(ModItens.TRUE_VOID_REMNANTS.get())){
            p_60504_.setBlock(p_60505_, ModBlocks.BLOODY_SAND.defaultBlockState(), 1);
            itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }
}
