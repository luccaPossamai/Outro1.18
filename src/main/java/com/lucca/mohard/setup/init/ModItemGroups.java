package com.lucca.mohard.setup.init;

import com.lucca.mohard.groups.*;
import net.minecraft.world.item.CreativeModeTab;

public class ModItemGroups {

    public static void register(){}

    public static final CreativeModeTab ESSENCE_TAB = new EssenceTab();

    public static final CreativeModeTab ARTIFACTS = new ArtifactsTab();

    public static final CreativeModeTab TOOLS = new EquipmentsTab();

    public static final CreativeModeTab ARMORS = new ArmorTab();

    public static final CreativeModeTab DENSE_BLOCKS = new DenseGroup();

}
