package com.lucca.mohard.setup.init;

import com.lucca.mohard.effects.corruption.CorruptionEffect;
import com.lucca.mohard.effects.IncinerationEffect;
import com.lucca.mohard.effects.StunningEffect;
import com.lucca.mohard.effects.TeleportationEffect;
import com.lucca.mohard.effects.attEffects.*;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.RegistryObject;


public class ModEffects {

    public static void register(){}

    public static final RegistryObject<MobEffect> INCINERATION = Registration.EFFECTS.register("incineration", ()->
            new IncinerationEffect());

    public static final RegistryObject<MobEffect> STUN = Registration.EFFECTS.register("stun", ()->
            (new StunningEffect())
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -100D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );

    public static final RegistryObject<MobEffect> TELEPORTATION = Registration.EFFECTS.register("teleportation", ()->
            (new TeleportationEffect()));

    public static final RegistryObject<MobEffect> CORRUPTION = Registration.EFFECTS.register("corruption", ()->
            (new CorruptionEffect()));

    public static final RegistryObject<MobEffect> MAGIC_DAMAGE_BUFF = Registration.EFFECTS.register("magic_damage_buff", ()->
            (new MagicDamageBuff())
                    .addAttributeModifier(ModAttributes.MAGIC_DAMAGE.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );

    public static final RegistryObject<MobEffect> HEALTH_BUFF = Registration.EFFECTS.register("health_buff", ()->
            (new HealthBuff())
                    .addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> PHYSICAL_DAMAGE_BUFF = Registration.EFFECTS.register("physical_damage_buff", ()->
            (new DamageBuff())
                    .addAttributeModifier(ModAttributes.PHYSICAL_DAMAGE.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> PROJECTILE_DAMAGE_BUFF = Registration.EFFECTS.register("projectile_damage_buff", ()->
            (new ProjectileDamageBuff())
                    .addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> INTELLECT_BUFF = Registration.EFFECTS.register("intellect_buff", ()->
            (new IntellectBuff())
                    .addAttributeModifier(ModAttributes.INTELLECT, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> ARMOR_PENETRATION_BUFF = Registration.EFFECTS.register("armor_penetration_buff", ()->
            (new ArmorPenetrationBuff())
                    .addAttributeModifier(ModAttributes.ARMOR_PENETRATION.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> AGILITY_BUFF = Registration.EFFECTS.register("agility_buff", ()->
            (new AgilityBuff())
                    .addAttributeModifier(ModAttributes.AGILITY.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
    public static final RegistryObject<MobEffect> ARMOR_BUFF = Registration.EFFECTS.register("armor_buff", ()->
            (new ArmorBuff())
                    .addAttributeModifier(ModAttributes.RAW_ARMOR.get(), "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", +0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );

}
