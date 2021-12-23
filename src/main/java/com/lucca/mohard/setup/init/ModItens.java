package com.lucca.mohard.setup.init;

import com.lucca.mohard.block.CorruptedBerryBush;
import com.lucca.mohard.itens.Chimarrao;
import com.lucca.mohard.itens.armors.*;
import com.lucca.mohard.itens.artifacts.*;
import com.lucca.mohard.itens.artifacts.algidAxe.AlgidAxe;
import com.lucca.mohard.itens.essence.essenceSyntonizer.EssenceSyntonizer;
import com.lucca.mohard.itens.tools.*;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;


import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;

public class ModItens {


    private static List<Item> listaItem = new ArrayList<>();
    private static List<EntityType> listaTipo = new ArrayList<>();


    public static void register(){
    }


    /*

    public static final RegistryObject<Item> ESSENCE = Registration.ITENS.register("essence", () ->
            new EssenceItem(
                    getEssenceProperties(),
                    null,
                    1,
                    1));

     */

    public static final RegistryObject<Item> GENERIC_ESSENCE = Registration.ITENS.register("empty_essence", () ->
            new Item(new Item.Properties().tab(ModItemGroups.ESSENCE_TAB)));

    public static final RegistryObject<Item> ENCHANTED_GOLD_INGOT = Registration.ITENS.register("enchanted_gold_ingot", () ->
            new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> AXE = Registration.ITENS.register("vilio_axe", () ->
            new ModAxe(new VilioTier(),
                    5.0F,
                    -3F,
                    new Item.Properties().tab(ModItemGroups.TOOLS)
            .stacksTo(1)
            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PICKAXE = Registration.ITENS.register("vilio_pickaxe", () ->
            new ModPickaxe(new VilioTier(),
                    1,
                    -2.8F,
                    new Item.Properties().tab(ModItemGroups.TOOLS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> HOE = Registration.ITENS.register("vilio_hoe", () ->
            new ModHoe(new VilioTier(),
                    -3,
                    0,
                    new Item.Properties().tab(ModItemGroups.TOOLS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));


    public static final RegistryObject<Item> SHOVEL = Registration.ITENS.register("vilio_shovel", () ->
            new ModShovel(new VilioTier(),
                    1.5F,
                    -3F,
                    new Item.Properties().tab(ModItemGroups.TOOLS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BOOK_ARTIFACT = Registration.ITENS.register("book_artifact", () ->
            new Book(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> DAGGER_ARTIFACT = Registration.ITENS.register("dagger_artifact", () ->
            new Dagger(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> DASH_ARTIFACT = Registration.ITENS.register("dash_artifact", () ->
            new Dash(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SCROLL_ARTIFACT = Registration.ITENS.register("scroll_artifact", () ->
            new Scroll(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ALGID_AXE_ARTIFACT = Registration.ITENS.register("algid_axe_artifact", () ->
            new AlgidAxe(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> CLOCK_BREAKER_ARTIFACT = Registration.ITENS.register("clock_breaker_artifact", () ->
            new ClockBreaker(new Item.Properties().tab(ModItemGroups.ARTIFACTS)
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)));




    //ARMORS
    public static final RegistryObject<Item> CAMO_BOOTS = Registration.ITENS.register("camo_boots", () ->
            new CamoArmor(EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CAMO_LEGGINGS = Registration.ITENS.register("camo_leggings", () ->
            new CamoArmor(EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CAMO_CHESTPLATE = Registration.ITENS.register("camo_chestplate", () ->
            new CamoArmor(EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CAMO_HELMET = Registration.ITENS.register("camo_helmet", () ->
            new CamoArmor(EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAGIC_BOOTS = Registration.ITENS.register("magic_boots", () ->
            new MagicArmor(EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAGIC_LEGGINGS = Registration.ITENS.register("magic_leggings", () ->
            new MagicArmor(EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAGIC_CHESTPLATE = Registration.ITENS.register("magic_chestplate", () ->
            new MagicArmor(EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAGIC_HELMET = Registration.ITENS.register("magic_helmet", () ->
            new MagicArmor(EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DEMON_BOOTS = Registration.ITENS.register("demon_boots", () ->
            new DemonArmor(EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DEMON_LEGGINGS = Registration.ITENS.register("demon_leggings", () ->
            new DemonArmor(EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DEMON_CHESTPLATE = Registration.ITENS.register("demon_chestplate", () ->
            new DemonArmor(EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DEMON_HELMET = Registration.ITENS.register("demon_helmet", () ->
            new DemonArmor(EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BIOHAZARD_BOOTS = Registration.ITENS.register("biohazard_boots", () ->
            new BiohazardArmor(EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BIOHAZARD_LEGGINGS = Registration.ITENS.register("biohazard_leggings", () ->
            new BiohazardArmor(EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BIOHAZARD_CHESTPLATE = Registration.ITENS.register("biohazard_chestplate", () ->
            new BiohazardArmor(EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BIOHAZARD_HELMET = Registration.ITENS.register("biohazard_helmet", () ->
            new BiohazardArmor(EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PURE_BOOTS = Registration.ITENS.register("pure_boots", () ->
            new PureArmor(EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PURE_LEGGINGS = Registration.ITENS.register("pure_leggings", () ->
            new PureArmor(EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PURE_CHESTPLATE = Registration.ITENS.register("pure_chestplate", () ->
            new PureArmor(EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PURE_HELMET = Registration.ITENS.register("pure_helmet", () ->
            new PureArmor(EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroups.ARMORS)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)));


    public static final ItemNameBlockItem CORRUPTED_BERRY = (ItemNameBlockItem) registerItem("corrupted_berry", new ItemNameBlockItem(ModBlocks.CORRUPTED_BUSH, (new Item.Properties()).tab(ModItemGroups.DENSE_BLOCKS).food(CorruptedBerryBush.CORRUPTED_BERRY)));

    public static final RegistryObject<Item> CHIMARRAO = Registration.ITENS.register("chimarrao", () ->
            new Chimarrao((new Item.Properties()).tab(ModItemGroups.DENSE_BLOCKS).food(Chimarrao.CHIMARRAO).stacksTo(1)));


    public static final ItemNameBlockItem DENSE_SPRUCE_SAPLING = (ItemNameBlockItem) registerItem("dense_spruce_sapling", new ItemNameBlockItem(ModBlocks.DENSE_SPRUCE_SAPLING, (new Item.Properties()).tab(ModItemGroups.DENSE_BLOCKS)));

    public static final RegistryObject<Item> MUSIC_DISC_BREATHE = Registration.ITENS.register("music_disc_breathe", () ->
            new RecordItem(1, () ->ModSounds.MOHARD_MUSIC,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                            .stacksTo(1)
                            .rarity(Rarity.EPIC))
            );

    public static final RegistryObject<Item> TRUE_VOID_REMNANTS = Registration.ITENS.register("true_void_remnants", () ->
            new Item((new Item.Properties()).tab(ModItemGroups.DENSE_BLOCKS)));

    public static final RegistryObject<Item> ESSENCE_SYNTONIZER = Registration.ITENS.register("essence_syntonizer", () ->
            new EssenceSyntonizer((new Item.Properties()).tab(ModItemGroups.TOOLS).stacksTo(1)));

    private static Item registerItem(String p_221547_0_, Item p_221547_1_) {
        if (p_221547_1_ instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) p_221547_1_;
            Registration.BLOCOS.register(p_221547_0_, () -> blockItem.getBlock());
        }
        Registration.ITENS.register(p_221547_0_, () -> p_221547_1_);
        return p_221547_1_;
    }







}
