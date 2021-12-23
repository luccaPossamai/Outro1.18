package com.lucca.mohard.setup.init;

import com.lucca.mohard.enchantments.*;
import com.lucca.mohard.enchantments.essences.EssenceEnchantment;
import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilities;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.registries.RegistryObject;


public class ModEnchantments {

    public static void register(){}

    public static final RegistryObject<Enchantment> STUN_STRIKE = Registration.ENCHANTMENTS.register("heaviness", ()->
    new EnchantmentStunStrike(
            Enchantment.Rarity.VERY_RARE,
            EnchantmentCategory.BREAKABLE,
            EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> POISON_STRIKE = Registration.ENCHANTMENTS.register("spider_fangs", ()->
            new EnchantmentPoisonStrike(
                    Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.BREAKABLE,
                    EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> INCINERATION = Registration.ENCHANTMENTS.register("incineration", ()->
            new EnchantmentIncinerationStrike(
                    Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.BREAKABLE,
                    EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> VAMPIRISM = Registration.ENCHANTMENTS.register("vampirism", ()->
            new EnchantmentLifeSteal(
                    Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.BREAKABLE,
                    EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> ARMOR_UNBREAKING = Registration.ENCHANTMENTS.register("armor_unbreaking", ()->
            new ArmorUnbreaking());


    //ESSENCES ENCHANTMENTS

    //VIDA
    public static final RegistryObject<EssenceEnchantment> STONE_WISDOM_ENCHANTMENT = Registration.ENCHANTMENTS.register("stone_wisdom", ()->
            new EssenceEnchantment(EssenceHabilities.STONE_WISDOM));

    //DANO
    public static final RegistryObject<EssenceEnchantment> GLASS_BEAST_ENCHANTMENT = Registration.ENCHANTMENTS.register("glass_beast", ()->
            new EssenceEnchantment(EssenceHabilities.GLASS_BEAST));

    //VIDA
    public static final RegistryObject<EssenceEnchantment> CONTEMPLATION_SCREAM_ENCHANTMENT = Registration.ENCHANTMENTS.register("contemplation_scream", ()->
            new EssenceEnchantment(EssenceHabilities.CONTEMPLATION_SCREAM));

    //FUNCAO
    public static final RegistryObject<EssenceEnchantment> CHICK_EVASION_ENCHANTMENT = Registration.ENCHANTMENTS.register("chick_evasion", ()->
            new EssenceEnchantment(EssenceHabilities.CHICK_EVASION));

    //DANO
    public static final RegistryObject<EssenceEnchantment> AUTOCIDE_ENCHANTMENT = Registration.ENCHANTMENTS.register("autocide" , ()->
            new EssenceEnchantment(EssenceHabilities.AUTOCIDE));

    //FUNCAO
    public static final RegistryObject<EssenceEnchantment> DUMMY_SUPPORT_ENCHANTMENT = Registration.ENCHANTMENTS.register("dummy_support", ()->
            new EssenceEnchantment(EssenceHabilities.DUMMY_SUPPORT));

    //VIDA
    public static final RegistryObject<EssenceEnchantment> CURSED_FRIENDSHIP_ENCHANTMENT = Registration.ENCHANTMENTS.register("cursed_friendship", ()->
            new EssenceEnchantment(EssenceHabilities.DUMMY_SUPPORT));

    //DANO
    public static final RegistryObject<EssenceEnchantment> NAKAS_MASTERY_ENCHANTMENT = Registration.ENCHANTMENTS.register("nakas_mastery", ()->
            new EssenceEnchantment(EssenceHabilities.NAKAS_MASTERY));


}
