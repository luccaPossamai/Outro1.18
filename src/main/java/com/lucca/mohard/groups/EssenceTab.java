package com.lucca.mohard.groups;

import com.lucca.mohard.setup.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class EssenceTab extends CreativeModeTab {

    public EssenceTab() {
        super("essences");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.ALTAR_BLOCK.asItem());
    }
}
