package com.lucca.mohard.setup.init;

import com.lucca.mohard.setup.Registration;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {

    public static void register(){}

    public static final RegistryObject<Attribute> PHYSICAL_DAMAGE = Registration.ATTRIBUTES.register("additional.physical_damage", ()->
            new RangedAttribute(
                    "attribute.name.additional.physical_damage",
                    0.0,
                    0.0,
                    2000.0
            ));

    public static final RegistryObject<Attribute> PROJECTILE_DAMAGE = Registration.ATTRIBUTES.register("additional.projectile_damage", ()->
            new RangedAttribute(
                    "attribute.name.additional.projectile_damage",
                    0.0,
                    0.0,
                    2000.0
                    ));

    public static final RegistryObject<Attribute> MAGIC_DAMAGE = Registration.ATTRIBUTES.register("additional.magic_damage", ()->
            new RangedAttribute(
                    "attribute.name.additional.magic_damage",
                    0.0,
                    0.0,
                    2000.0
            ).setSyncable(true));

    public static final RegistryObject<Attribute> RAW_ARMOR = Registration.ATTRIBUTES.register("additional.raw_armor", ()->
            new RangedAttribute(
                    "attribute.name.additional.raw_armor",
                    0.0,
                    0.0,
                    2048.0
            ));

    public static final RegistryObject<Attribute> ARMOR_PENETRATION = Registration.ATTRIBUTES.register("additional.armor_penetration", ()->
            new RangedAttribute(
                    "attribute.name.additional.armor_penetration",
                    0.0,
                    0.0,
                    100.0
            ));

    public static final RegistryObject<Attribute> AGILITY = Registration.ATTRIBUTES.register("additional.agility", ()->
            new RangedAttribute(
                    "attribute.name.additional.agility",
                    0.0,
                    -100.0,
                    100.0
            ));

    public static final Attribute INTELLECT = register("additional.intellect",
            new RangedAttribute(
                    "attribute.name.additional.intellect",
                    0.0,
                    -100.0,
                    100.0
            ));

    public static final Attribute MAGIC_RESISTANCE = register("additional.magic_resistance",
            new RangedAttribute(
                    "attribute.name.additional.magic_resistance",
                    0.0,
                    -10.0,
                    10.0
            ).setSyncable(true));

    private static Attribute register(String name, Attribute att){
        Registration.ATTRIBUTES.register(name, () -> att);
        return att;
    }
}
