package com.lucca.mohard.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.*;

import javax.annotation.Nullable;

public class ModBiomeMethods {

    public static Music NORMAL_MUSIC = null;

    public static Biome biome(Biome.Precipitation p_194852_, Biome.BiomeCategory p_194853_, float p_194854_, float p_194855_, int waterColor, int waterFogColor, int skyColor, int fogColor, int grassColor, int foliageColor, MobSpawnSettings.Builder p_194858_, BiomeGenerationSettings.Builder p_194859_, @Nullable Music p_194860_) {
        return (new Biome.BiomeBuilder()).precipitation(p_194852_).biomeCategory(p_194853_).temperature(p_194854_).downfall(p_194855_).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(skyColor).fogColor(fogColor).grassColorOverride(grassColor).foliageColorOverride(foliageColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(p_194860_).build()).mobSpawnSettings(p_194858_.build()).generationSettings(p_194859_.build()).build();
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder p_194870_) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(p_194870_);
        BiomeDefaultFeatures.addDefaultCrystalFormations(p_194870_);
        BiomeDefaultFeatures.addDefaultMonsterRoom(p_194870_);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(p_194870_);
        BiomeDefaultFeatures.addDefaultSprings(p_194870_);
        BiomeDefaultFeatures.addSurfaceFreezing(p_194870_);
    }
}
