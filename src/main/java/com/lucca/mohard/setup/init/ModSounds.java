package com.lucca.mohard.setup.init;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static void register(){}

    public static final SoundEvent MOHARD_MUSIC = register("music_disc.mohard_music",
            new ResourceLocation(ExampleMod.MOD_ID, "music_disc.mohard_music"));

    public static final Music MOD = Musics.createGameMusic(MOHARD_MUSIC);

    public static final SoundEvent STONE_WISDOM_SOUND = register("hability.stone_wisdom_sound",
            new ResourceLocation(ExampleMod.MOD_ID, "hability.stone_wisdom_sound"));

    public static final SoundEvent LOW_DAMAGE_SOUND = register("damage.low_damage_sound",
            new ResourceLocation(ExampleMod.MOD_ID, "damage.low_damage_sound"));

    public static final SoundEvent NO_DAMAGE_SOUND = register("damage.no_damage_sound",
            new ResourceLocation(ExampleMod.MOD_ID, "damage.no_damage_sound"));

    private static SoundEvent register(String name, ResourceLocation resource){
        SoundEvent soundEvent = new SoundEvent(resource);
        Registration.SOUND_EVENTS.register(name, () ->
                soundEvent
                );
        return soundEvent;
    }


}
