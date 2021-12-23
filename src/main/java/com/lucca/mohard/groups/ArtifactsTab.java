package com.lucca.mohard.groups;

import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ArtifactsTab extends CreativeModeTab {

    public ArtifactsTab() {
        super("artifacts");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItens.BOOK_ARTIFACT.get());
    }
}
