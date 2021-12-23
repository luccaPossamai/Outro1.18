package com.lucca.mohard.setup.init;

import com.google.common.collect.ImmutableSet;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.RegistryObject;

public class ModPOI {

    public static void register(){}

    public static final RegistryObject<PoiType> MASTER = Registration.POINT_OF_INTEREST_TYPE.register("master", ()->
            new PoiType("master", ImmutableSet.copyOf(ModBlocks.ALTAR_BLOCK.getStateDefinition().getPossibleStates()), 1, 1));
}
