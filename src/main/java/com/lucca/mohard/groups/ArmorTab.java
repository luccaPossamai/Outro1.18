package com.lucca.mohard.groups;

import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ArmorTab extends CreativeModeTab {

    public ArmorTab() {
        super("armors");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItens.CAMO_CHESTPLATE.get());
    }
}
