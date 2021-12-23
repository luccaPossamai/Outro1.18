package com.lucca.mohard.setup.init;

import com.lucca.mohard.help.Methods;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.itens.essence.EssencePredicate;
import com.lucca.mohard.setup.Registration;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEssences {

    public static void register(){}

    public static final RegistryObject<EssenceItem> AXOLOTL_ESSENCE = register("axolotl", EntityType.AXOLOTL,16499171, 10890612);
    public static final RegistryObject<EssenceItem> BAT_ESSENCE = register("bat", EntityType.BAT, 4996656, 986895);
    public static final RegistryObject<EssenceItem> BEE_ESSENCE = register("bee", EntityType.BEE, 15582019, 4400155);
    public static final RegistryObject<EssenceItem> BLAZE_ESSENCE = register("blaze", EntityType.BLAZE, 16167425, 16775294);
    public static final RegistryObject<EssenceItem> CAT_ESSENCE = register("cat", EntityType.CAT, 15714446, 9794134);
    public static final RegistryObject<EssenceItem> CAVE_SPIDER_ESSENCE = register("cave_spider", EntityType.CAVE_SPIDER, ModEntityTypes.SPIDEY_ENTITY_TYPE, 803406, 11013646);
    public static final RegistryObject<EssenceItem> CHICKEN_ESSENCE = register("chicken", EntityType.CHICKEN, 10592673, 16711680);
    public static final RegistryObject<EssenceItem> COD_ESSENCE = register("cod", EntityType.COD, 12691306, 15058059);
    public static final RegistryObject<EssenceItem> COW_ESSENCE = register("cow", EntityType.COW, 4470310, 10592673);
    public static final RegistryObject<EssenceItem> CREEPER_ESSENCE = register("creeper", EntityType.CREEPER, ModEntityTypes.CREEPY_ENTITY_TYPE, 894731, 0);
    public static final RegistryObject<EssenceItem> DOLPHIN_ESSENCE = register("dolphin", EntityType.DOLPHIN, 2243405, 16382457);
    public static final RegistryObject<EssenceItem> DONKEY_ESSENCE = register("donkey", EntityType.DONKEY, 5457209, 8811878);
    public static final RegistryObject<EssenceItem> DROWNED_ESSENCE = register("drowned", EntityType.DROWNED, ModEntityTypes.ZOMBY_ENTITY_TYPE, 9433559, 7969893);
    public static final RegistryObject<EssenceItem> ELDER_GUARDIAN_ESSENCE = register("elder_guardian", EntityType.ELDER_GUARDIAN, 13552826, 7632531);
    public static final RegistryObject<EssenceItem> ENDERMAN_ESSENCE = register("enderman", EntityType.ENDERMAN, 1447446, 0);
    public static final RegistryObject<EssenceItem> ENDERMITE_ESSENCE = register("endermite", EntityType.ENDERMITE, 1447446, 7237230);
    public static final RegistryObject<EssenceItem> EVOKER_ESSENCE = register("evoker", EntityType.EVOKER, 9804699, 1973274);
    public static final RegistryObject<EssenceItem> FOX_ESSENCE = register("fox", EntityType.FOX, 14005919, 13396256);
    public static final RegistryObject<EssenceItem> GHAST_ESSENCE = register("ghast", EntityType.GHAST, 16382457, 12369084);
    public static final RegistryObject<EssenceItem> GOAT_ESSENCE = register("goat", EntityType.GOAT, 10851452, 5589310);
    public static final RegistryObject<EssenceItem> GUARDIAN_ESSENCE = register("guardian", EntityType.GUARDIAN, 5931634, 15826224);
    public static final RegistryObject<EssenceItem> HOGLIN_ESSENCE = register("hoglin", EntityType.HOGLIN, 13004373, 6251620);
    public static final RegistryObject<EssenceItem> HORSE_ESSENCE = register("horse", EntityType.HORSE, 12623485, 15656192);
    public static final RegistryObject<EssenceItem> HUSK_ESSENCE = register("husk", EntityType.HUSK, ModEntityTypes.ZOMBY_ENTITY_TYPE, 7958625, 15125652);
    public static final RegistryObject<EssenceItem> LLAMA_ESSENCE = register("llama", EntityType.LLAMA, 12623485, 10051392);
    public static final RegistryObject<EssenceItem> MAGMA_CUBE_ESSENCE = register("magma_cube", EntityType.MAGMA_CUBE, 3407872, 16579584);
    public static final RegistryObject<EssenceItem> MOOSHROOM_ESSENCE = register("mooshroom", EntityType.MOOSHROOM, 10489616, 12040119);
    public static final RegistryObject<EssenceItem> MULE_ESSENCE = register("mule", EntityType.MULE, 1769984, 5321501);
    public static final RegistryObject<EssenceItem> OCELOT_ESSENCE = register("ocelot", EntityType.OCELOT, 15720061, 5653556);
    public static final RegistryObject<EssenceItem> PANDA_ESSENCE = register("panda", EntityType.PANDA, 15198183, 1776418);
    public static final RegistryObject<EssenceItem> PARROT_ESSENCE = register("parrot", EntityType.PARROT, 894731, 16711680);
    public static final RegistryObject<EssenceItem> PHANTOM_ESSENCE = register("phantom", EntityType.PHANTOM, 4411786, 8978176);
    public static final RegistryObject<EssenceItem> PIG_ESSENCE = register("pig", EntityType.PIG, 15771042, 14377823);
    public static final RegistryObject<EssenceItem> PIGLIN_ESSENCE = register("piglin", EntityType.PIGLIN, 10051392, 16380836);
    public static final RegistryObject<EssenceItem> PIGLIN_BRUTE_ESSENCE = register("piglin_brute", EntityType.PIGLIN_BRUTE, 5843472, 16380836);
    public static final RegistryObject<EssenceItem> PILLAGER_ESSENCE = register("pillager", EntityType.PILLAGER, 5451574, 9804699);
    public static final RegistryObject<EssenceItem> POLAR_BEAR_ESSENCE = register("polar_bear", EntityType.POLAR_BEAR, 15921906, 9803152);
    public static final RegistryObject<EssenceItem> PUFFERFISH_ESSENCE = register("pufferfish", EntityType.PUFFERFISH, 16167425, 3654642);
    public static final RegistryObject<EssenceItem> RABBIT_ESSENCE = register("rabbit", EntityType.RABBIT, 10051392, 7555121);
    public static final RegistryObject<EssenceItem> RAVAGER_ESSENCE = register("ravager", EntityType.RAVAGER, 7697520, 5984329);
    public static final RegistryObject<EssenceItem> SALMON_ESSENCE = register("salmon", EntityType.SALMON, 10489616, 951412);
    public static final RegistryObject<EssenceItem> SHEEP_ESSENCE = register("sheep", EntityType.SHEEP, 15198183, 16758197);
    public static final RegistryObject<EssenceItem> SHULKER_ESSENCE = register("shulker", EntityType.SHULKER, 9725844, 5060690);
    public static final RegistryObject<EssenceItem> SILVERFISH_ESSENCE = register("silverfish", EntityType.SILVERFISH, 7237230, 3158064);
    public static final RegistryObject<EssenceItem> SKELETON_ESSENCE = register("skeleton", EntityType.SKELETON, ModEntityTypes.SKELLY_ENTITY_TYPE, 12698049, 4802889);
    public static final RegistryObject<EssenceItem> SKELETON_HORSE_ESSENCE = register("skeleton_horse", EntityType.SKELETON_HORSE, 6842447, 15066584);
    public static final RegistryObject<EssenceItem> SLIME_ESSENCE = register("slime", EntityType.SLIME, 5349438, 8306542);
    public static final RegistryObject<EssenceItem> SPIDER_ESSENCE = register("spider", EntityType.SPIDER, ModEntityTypes.SPIDEY_ENTITY_TYPE, 3419431, 11013646);
    public static final RegistryObject<EssenceItem> SQUID_ESSENCE = register("squid", EntityType.SQUID, 2243405, 7375001);
    public static final RegistryObject<EssenceItem> GLOW_SQUID_ESSENCE = register("glow_squid", EntityType.GLOW_SQUID, 611926, 8778172);
    public static final RegistryObject<EssenceItem> STRAY_ESSENCE = register("stray", EntityType.STRAY, ModEntityTypes.SKELLY_ENTITY_TYPE, 6387319, 14543594);
    public static final RegistryObject<EssenceItem> STRIDER_ESSENCE = register("strider", EntityType.STRIDER, 10236982, 5065037);
    public static final RegistryObject<EssenceItem> TRADER_LLAMA_ESSENCE = register("trader_llama", EntityType.TRADER_LLAMA, 15377456, 4547222);
    public static final RegistryObject<EssenceItem> TROPICAL_FISH_ESSENCE = register("tropical_fish", EntityType.TROPICAL_FISH, 15690005, 16775663);
    public static final RegistryObject<EssenceItem> TURTLE_ESSENCE = register("turtle", EntityType.TURTLE, 15198183, 44975);
    public static final RegistryObject<EssenceItem> VEX_ESSENCE = register("vex", EntityType.VEX, 8032420, 15265265);
    public static final RegistryObject<EssenceItem> VILLAGER_ESSENCE = register("villager", EntityType.VILLAGER, 5651507, 12422002);
    public static final RegistryObject<EssenceItem> VINDICATOR_ESSENCE = register("vindicator", EntityType.VINDICATOR, 9804699, 2580065);
    public static final RegistryObject<EssenceItem> WANDERING_TRADER_ESSENCE = register("wandering_trader", EntityType.WANDERING_TRADER, 4547222, 15377456);
    public static final RegistryObject<EssenceItem> WITCH_ESSENCE = register("witch", EntityType.WITCH, 3407872, 5349438);
    public static final RegistryObject<EssenceItem> WITHER_SKELETON_ESSENCE = register("wither_skeleton", EntityType.WITHER_SKELETON, 1315860, 4672845);
    public static final RegistryObject<EssenceItem> WOLF_ESSENCE = register("wolf", EntityType.WOLF, 14144467, 13545366);
    public static final RegistryObject<EssenceItem> ZOGLIN_ESSENCE = register("zoglin", EntityType.ZOGLIN, 13004373, 15132390);
    public static final RegistryObject<EssenceItem> ZOMBIE_ESSENCE = register("zombie", EntityType.ZOMBIE, ModEntityTypes.ZOMBY_ENTITY_TYPE, 44975, 7969893);
    public static final RegistryObject<EssenceItem> ZOMBIE_HORSE_ESSENCE = register("zombie_horse", EntityType.ZOMBIE_HORSE, 3232308, 9945732);
    public static final RegistryObject<EssenceItem> ZOMBIE_VILLAGER_ESSENCE = register("zombie_villager", EntityType.ZOMBIE_VILLAGER, 5651507, 7969893);
    public static final RegistryObject<EssenceItem> ZOMBIFIED_PIGLIN_ESSENCE = register("zombified_piglin", EntityType.ZOMBIFIED_PIGLIN, 15373203, 5009705);

    //OUT OF SPAWNEGGS
    public static final RegistryObject<EssenceItem> IRON_GOLEM_ESSENCE = register("iron_golem", EntityType.IRON_GOLEM, 15198183, 894731 );
    public static final RegistryObject<EssenceItem> ILLUSIONER_ESSENCE = register("illusioner", EntityType.ILLUSIONER, 2243405, 16579584);
    public static final RegistryObject<EssenceItem> SNOW_GOLEM_ESSENCE = register("snow_golem", EntityType.SNOW_GOLEM, 15198183, 15690005);
    public static final RegistryObject<EssenceItem> GOLDEN_ARMORED_PILLAGER_ESSENCE = register("golden_armored_pillager", ModEntityTypes.GOLDEN_ARMORED_PILLAGER_ENTITY_TYPE,5451574, 16167425);
    public static final RegistryObject<EssenceItem> DIAMOND_ARMORED_PILLAGER_ESSENCE = register("diamond_armored_pillager", ModEntityTypes.DIAMOND_ARMORED_PILLAGER_ENTITY_TYPE, 5451574, 9433559);
    public static final RegistryObject<EssenceItem> GOLDEN_ARMORED_VINDICATOR_ESSENCE = register("golden_armored_vindicator", ModEntityTypes.GOLDEN_ARMORED_VINDICATOR_ENTITY_TYPE,9804699, 16167425);
    public static final RegistryObject<EssenceItem> DIAMOND_ARMORED_VINDICATOR_ESSENCE = register("diamond_armored_vindicator", ModEntityTypes.DIAMOND_ARMORED_VINDICATOR_ENTITY_TYPE, 9804699, 9433559);
    public static final RegistryObject<EssenceItem> ENCHANTER_ESSENCE = register("enchanter", ModEntityTypes.ENCHANTER_ILLAGER_ENTITY_TYPE, 10489616, 9433559);
    public static final RegistryObject<EssenceItem> MOOER_ESSENCE = register("mooer", ModEntityTypes.MOOER_ENTITY_TYPE, 5457209, 4470310);
    public static final RegistryObject<EssenceItem> GILDED_MOOER_ESSENCE = register("gilded_mooer", ModEntityTypes.GOLDEN_MOOER_ENTITY_TYPE, 4672845, 16167425);
    public static final RegistryObject<EssenceItem> MOOSHROOM_MOOER_ESSENCE = register("mooshroom_mooer", ModEntityTypes.MOOSHROOM_MOOER_ENTITY_TYPE, 5420416, 10489616);
    public static final RegistryObject<EssenceItem> ICE_ISOLATOR_ESSENCE = register("ice_isolator", ModEntityTypes.ICE_ISOLATOR_ENTITY_TYPE, 9156025, 9804699, (entity) -> {
        String name = ChatFormatting.stripFormatting(entity.getName().getString());
        return name.equals("Bruno");
    }, false);
    public static final RegistryObject<EssenceItem> BRUNO_ESSENCE = register("bruno", ModEntityTypes.ICE_ISOLATOR_ENTITY_TYPE, 9156025, 10489616, true);


    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, int color1, int color2){
        return register(string, type, type, color1, color2, false);
    }

    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, int color1, int color2, boolean alternative){
        return register(string, type, type, color1, color2, alternative);
    }

    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, EntityType<?> corruptedType, int color1, int color2){
        return register(string, type, corruptedType, color1, color2, (a) -> false, false);
    }

    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, EntityType<?> corruptedType, int color1, int color2, boolean alternative){
        return register(string, type, corruptedType, color1, color2, (a) -> false, alternative);
    }

    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, int color1, int color2, EssencePredicate<LivingEntity> sup, boolean alternative){
        return register(string, type, type, color1, color2, sup, alternative);
    }

    private static RegistryObject<EssenceItem> register(String string, EntityType<?> type, EntityType<?> corruptedType, int color1, int color2, EssencePredicate<LivingEntity> sup, boolean alternative){
        return Registration.ITENS.register("essence_"+string, () ->
                new EssenceItem(getEssenceProperties(), type,corruptedType, color1, color2, sup, alternative));
    }


    public static EssenceItem.Properties getEssenceProperties(){
        return new EssenceItem.Properties()
                .tab(ModItemGroups.ESSENCE_TAB)
                .stacksTo(9);
    }
}
