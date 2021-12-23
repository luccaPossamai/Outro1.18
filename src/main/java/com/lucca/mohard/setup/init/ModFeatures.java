package com.lucca.mohard.setup.init;


import com.lucca.mohard.features.DenseDesertBlockBlobFeature;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static void register(){}


    public static final RegistryObject<Feature<BlockStateConfiguration>> DENSE_DESERT_ROCK = Registration.FEATURES.register("forest_rock", () ->
            new DenseDesertBlockBlobFeature(BlockStateConfiguration.CODEC));


}
