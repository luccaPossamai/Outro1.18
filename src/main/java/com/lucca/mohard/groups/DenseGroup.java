package com.lucca.mohard.groups;

import com.lucca.mohard.setup.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DenseGroup extends CreativeModeTab {

    public DenseGroup() {
        super("dense_blocks");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.BLOODY_SAND);
    }
}
