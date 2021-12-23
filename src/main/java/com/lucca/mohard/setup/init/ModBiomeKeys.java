package com.lucca.mohard.setup.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomeKeys {

    public static void register(){}

    public static final ResourceKey<Biome> DENSE_DESERT = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.DENSE_DESERT.get())));
    public static final ResourceKey<Biome> TAIGA = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.DENSE_TAIGA.get())));
    public static final ResourceKey<Biome> OLD_GROWTH_DENSE_PINE_TAIGA = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.OLD_GROWTH_DENSE_PINE_TAIGA.get())));
    public static final ResourceKey<Biome> OLD_GROWTH_DENSE_SPRUCE_TAIGA = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.OLD_GROWTH_DENSE_SPRUCE_TAIGA.get())));
    public static final ResourceKey<Biome> TAIGA_SNOWY = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.SNOWY_DENSE_TAIGA.get())));

}
