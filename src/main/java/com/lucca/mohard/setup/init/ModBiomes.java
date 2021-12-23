package com.lucca.mohard.setup.init;

import com.lucca.mohard.biome.denseDesert.DenseDesertBiomeMaker;
import com.lucca.mohard.biome.denseTaiga.DenseTaigaBiomeMaker;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {

    public static void register(){}

    public static final RegistryObject<Biome> DENSE_DESERT = Registration.BIOMES.register("dense_desert", () ->
            DenseDesertBiomeMaker.denseDesertBiome());

    public static final RegistryObject<Biome> DENSE_TAIGA = Registration.BIOMES.register("dense_taiga", () ->
            DenseTaigaBiomeMaker.denseTaigaBiome(false));

    public static final RegistryObject<Biome> SNOWY_DENSE_TAIGA = Registration.BIOMES.register("snowy_dense_taiga", ()->
            DenseTaigaBiomeMaker.denseTaigaBiome(true));

    public static final RegistryObject<Biome> OLD_GROWTH_DENSE_PINE_TAIGA = Registration.BIOMES.register("old_growth_dense_pine_taiga", ()->
            DenseTaigaBiomeMaker.giantTreeDenseTaiga(false));

    public static final RegistryObject<Biome> OLD_GROWTH_DENSE_SPRUCE_TAIGA = Registration.BIOMES.register("old_growth_dense_spruce_taiga", ()->
            DenseTaigaBiomeMaker.giantTreeDenseTaiga(true));
}
