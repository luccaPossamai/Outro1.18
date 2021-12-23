package com.lucca.mohard.groups;

import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class EquipmentsTab extends CreativeModeTab {

    public EquipmentsTab() {
        super("tools");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItens.AXE.get());
    }
}
