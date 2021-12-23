package com.lucca.mohard.setup.init;

import com.lucca.mohard.setup.Registration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    public static void register(){}

    public static final RegistryObject<Potion> CORRUPTION_POTION = Registration.POTIONS.register("corruption", () ->
            new Potion(new MobEffectInstance(ModEffects.CORRUPTION.get(), 200, 0))
    );

    public static final RegistryObject<Potion> STRONG_CORRUPTION_POTION = Registration.POTIONS.register("strong_corruption", () ->
            new Potion(new MobEffectInstance(ModEffects.CORRUPTION.get(), 100, 1))
    );

    public static final RegistryObject<Potion> VERY_STRONG_CORRUPTION_POTION = Registration.POTIONS.register("very_strong_corruption", () ->
            new Potion(new MobEffectInstance(ModEffects.CORRUPTION.get(), 80, 2))
    );
}
