package com.lucca.mohard.setup.init;

import com.google.common.collect.ImmutableSet;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.RegistryObject;

public class ModProfessions {

    public static void register(){}

    public static final RegistryObject<VillagerProfession> MASTER = Registration.PROFESSIONS.register("master", ()->
            new VillagerProfession("master", ModPOI.MASTER.get(), ImmutableSet.of(), ImmutableSet.of(), null));
}
