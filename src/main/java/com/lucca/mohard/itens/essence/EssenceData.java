package com.lucca.mohard.itens.essence;

import com.lucca.mohard.setup.init.ModEssences;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EssenceData {

    AXOLOTL(ModEssences.AXOLOTL_ESSENCE.get(),false,0,2,0,0,
            2, 0,0,0
    ),
    BAT(ModEssences.BAT_ESSENCE.get(),false,0,0,0,1,
                    0, 0,0,0
    ),
    BEE(ModEssences.BEE_ESSENCE.get(),false,0, 3,0,1,
                        0, 1,0,0
    ),
    BLAZE(ModEssences.BLAZE_ESSENCE.get(),false,0,0,0,0,
                        0, 0,0,2
    ),
    CAT(ModEssences.CAT_ESSENCE.get(),false,0, 0, 0, 1,
                        0,0,2,0
    ),
    CAVE_SPIDER(ModEssences.CAVE_SPIDER_ESSENCE.get(),false,0,2,0,1,
                        0,0,0,0
    ),
    CHICKEN(ModEssences.CHICKEN_ESSENCE.get(),false,-1,0,0,1,
                        0,0,1,0
    ),
    COD(ModEssences.COD_ESSENCE.get(),false,3,0,0,-1,
                        0,0,0,0
    ),
    COW(ModEssences.COW_ESSENCE.get(),false,2,0,1,0,
                        0, 0,0,0
    ),
    CREEPER(ModEssences.CREEPER_ESSENCE.get(),false,0,3,-1,0,
                        0, 0,0,0
    ),
    DOLPHIN(ModEssences.DOLPHIN_ESSENCE.get(),false,0,0,0,3,
                        0, -1,1,0
    ),
    DONKEY(ModEssences.DONKEY_ESSENCE.get(),false,0,0,2,1,
                        0, 0,-2,0
    ),
    DROWNED(ModEssences.DROWNED_ESSENCE.get(),false,2,1,1,0,
                        0, 0,0,0
    ),
    ELDER_GUARDIAN(ModEssences.ELDER_GUARDIAN_ESSENCE.get(),false,15,0,4,0,
                        0, 0,0,4
    ),
    ENDERMAN(ModEssences.ENDERMAN_ESSENCE.get(),false,0,4,0,1,
                        0, 1,0,0
    ),
    ENDERMITE(ModEssences.ENDERMITE_ESSENCE.get(),false,-4,3,-2,0,
                        0, 2,0,0
    ),
    EVOKER(ModEssences.EVOKER_ESSENCE.get(),false,0,0,0,0,
                        0, 0,2,12
    ),
    ENCHANTER_ILLAGER_ENTITY_TYPE(ModEssences.ENCHANTER_ESSENCE.get(),false,0,0,-2,0,
                        0, 0,4,6),

    FOX(ModEssences.FOX_ESSENCE.get(),false,-1,0,-1,4,
                        0, 0,0,0
    ),

    GHAST(ModEssences.GHAST_ESSENCE.get(),false,2,0,0,0,
                        2, 0,0,0
    ),
    GOAT(ModEssences.GOAT_ESSENCE.get(),false,0,3,1,1,
            0, 0,0,0
    ),
    GUARDIAN(ModEssences.GUARDIAN_ESSENCE.get(),false,0,0,3,0,
                        0, 1,0,2
    ),
    HOGLIN(ModEssences.HOGLIN_ESSENCE.get(),false,7,4,0,0,
                        0, 0,0,0
    ),
    HORSE(ModEssences.HORSE_ESSENCE.get(),false,3,0,0,3,
                        0, 0,0,0
    ),
    HUSK(ModEssences.HUSK_ESSENCE.get(),false,2,2,0,-1,
                        0, 0,0,0
    ),
    LLAMA(ModEssences.LLAMA_ESSENCE.get(),false,3,-2,2,-1,
                        4, 0,1,0
    ),
    MAGMA_CUBE(ModEssences.MAGMA_CUBE_ESSENCE.get(),false,2,2,0,0,
                        0, 1,0,0
    ),
    MOOSHROOM(ModEssences.MOOSHROOM_ESSENCE.get(),false,3,0,3,0,
                        0, 0,0,5
    ),
    MULE(ModEssences.MULE_ESSENCE.get(),false,1,0,1,2,
                        0, 0,-1,0
    ),
    OCELOT(ModEssences.OCELOT_ESSENCE.get(),false,0,2,0,1,
                        0, 2,0,0
    ),
    PANDA(ModEssences.PANDA_ESSENCE.get(),false,6,6,0,-1,
                        0, 0,0,0
    ),
    PARROT(ModEssences.PARROT_ESSENCE.get(),false,2,0,0,1,
                        0, 0,1,0
    ),
    PHANTOM(ModEssences.PHANTOM_ESSENCE.get(),false,0,2,2,1,
                        0, 0,0,2
    ),
    PIG(ModEssences.PIG_ESSENCE.get(),false,0,0,3,0,
                        0, 0,0,0
    ),
    PIGLIN(ModEssences.PIGLIN_ESSENCE.get(),false,4,4,0,0,
                        0, 0,2,0
    ),
    PIGLIN_BRUTE(ModEssences.PIGLIN_BRUTE_ESSENCE.get(),false,0,10,0,0,
                        0, 0,-1,0
    ),
    PILLAGER(ModEssences.PILLAGER_ESSENCE.get(),false,0,0,0,1,
                        2, 1,0,0
    ),
    GOLDEN_ARMORED_PILLAGER_ENTITY_TYPE(ModEssences.GOLDEN_ARMORED_PILLAGER_ESSENCE.get(),false,
            0,0,1,3,
            4, 1,0,0
    ),
    DIAMOND_ARMORED_PILLAGER_ENTITY_TYPE(ModEssences.DIAMOND_ARMORED_PILLAGER_ESSENCE.get(),false,
            0,0, 2,2,
            5, 2,0,0
    ),
    POLAR_BEAR(ModEssences.POLAR_BEAR_ESSENCE.get(),false,3,6,0,-1,
                        0, 0,0,0
    ),
    PUFFERFISH(ModEssences.PUFFERFISH_ESSENCE.get(),false,-1,0,-1,0,
                        0, 5,0,0
    ),
    RABBIT(ModEssences.RABBIT_ESSENCE.get(),false,-1,0,0,3,
                        0, 0,0,0
    ),
    RAVAGER(ModEssences.RAVAGER_ESSENCE.get(),false,10,2,5,-1,
                        0, 0,-1,-2
    ),
    SALMON(ModEssences.SALMON_ESSENCE.get(),false,0,0,2,0,
                        0, 0,0,1
    ),
    SHEEP(ModEssences.SHEEP_ESSENCE.get(),false,1,0,2,0,
                        0, 0,0,0
    ),
    SHULKER(ModEssences.SHULKER_ESSENCE.get(),false,0,0,3,-4,
                        10, 2,0,0
    ),
    SILVERFISH(ModEssences.SILVERFISH_ESSENCE.get(),false,-1,3,-1,1,
                        0, 1,0,0
    ),
    SKELETON(ModEssences.SKELETON_ESSENCE.get(),false,-1,0,0,1,
                        2, 1,0,0
    ),
    SKELETON_HORSE(ModEssences.SKELETON_HORSE_ESSENCE.get(),false,1,0,5,3,
                        0, 0,1,0
    ),
    SLIME(ModEssences.SLIME_ESSENCE.get(),false,4,1,0,-1,
                        0, 0,0,0
    ),
    SPIDER(ModEssences.SPIDER_ESSENCE.get(),false,0,2,0,2,
                        0, 0,0,0
    ),
    SQUID(ModEssences.SQUID_ESSENCE.get(),false,0,0,0,0,
                        0, 0,-1,0
    ),
    GLOW_SQUID(ModEssences.GLOW_SQUID_ESSENCE.get(),false,0,0,0,0,
            0, 0,3,2
    ),
    STRAY(ModEssences.STRAY_ESSENCE.get(),false,0,0,0,1,
                        2, 0,0,2
    ),
    STRIDER(ModEssences.STRIDER_ESSENCE.get(),false,2,0,0,2,
                        0, 0,0,2
    ),
    TRADER_LLAMA(ModEssences.TRADER_LLAMA_ESSENCE.get(),false,1,0,2,0,
                        0, 0,0,6
    ),
    TROPICAL_FISH(ModEssences.TROPICAL_FISH_ESSENCE.get(),false,0,0,0,2,
                        0, 0,0,0
    ),
    TURTLE(ModEssences.TURTLE_ESSENCE.get(),false,0,0,4,-2,
                        0, 0,0,0
    ),
    VEX(ModEssences.VEX_ESSENCE.get(),false,-2,6,0,0,
                        0, 5,0,0
    ),
    VILLAGER(ModEssences.VILLAGER_ESSENCE.get(),false,4,0,0,0,
                        0, 0,2,1
    ),
    VINDICATOR(ModEssences.VINDICATOR_ESSENCE.get(),false,0,3,0,1,
                        0, 6,0,0
    ),
    GOLDEN_ARMORED_VINDICATOR_ENTITY_TYPE(ModEssences.GOLDEN_ARMORED_VINDICATOR_ESSENCE.get(),false,
            0,4,1,1,
            0, 7,0,0
    ),
    DIAMOND_ARMORED_VINDICATOR_ENTITY_TYPE(ModEssences.DIAMOND_ARMORED_VINDICATOR_ESSENCE.get(),false,
            0,5,2,1,
            0, 8,0,0
    ),
    WANDERING_TRADER(ModEssences.WANDERING_TRADER_ESSENCE.get(),false,3,0,0,0,
                        0, 0,3,5
    ),
    WITCH(ModEssences.WITCH_ESSENCE.get(),false,2,0,0,0,
                        0, 0,4,5
    ),
    WITHER_SKELETON(ModEssences.WITHER_SKELETON_ESSENCE.get(),false,-1,4,-2,0,
                        0, 3,0,0
    ),
    WOLF(ModEssences.WOLF_ESSENCE.get(),false,0,2,2,0,
                        0, 2,0,0
    ),
    ZOGLIN(ModEssences.ZOGLIN_ESSENCE.get(),false,0,5,7,0,
                        0, 0,0,0
    ),
    ZOMBIE(ModEssences.ZOMBIE_ESSENCE.get(),false,2,2,0,0,
                        0, 0,-1,0
    ),
    ZOMBIE_HORSE(ModEssences.ZOMBIE_HORSE_ESSENCE.get(),false,5,0,1,3,
                        0, 1,-1,0
    ),
    ZOMBIE_VILLAGER(ModEssences.ZOMBIE_VILLAGER_ESSENCE.get(),false,5,1,1,0,
                        0, 0,-1,0
    ),
    ZOMBIFIED_PIGLIN(ModEssences.ZOMBIFIED_PIGLIN_ESSENCE.get(),false,0,6,4,0,
                        0, 0,-2,0
    ),

    IRON_GOLEM(ModEssences.IRON_GOLEM_ESSENCE.get(),false,4,0,6,-3,
                        0, 2,0,0
    ),
    ILLUSIONER(ModEssences.ILLUSIONER_ESSENCE.get(),false,0,0,-4,0,
                        5, 0,5,5
    ),
    SNOW_GOLEM(ModEssences.SNOW_GOLEM_ESSENCE.get(),false,2,0,-4,0,
                        10, 0,0,0
    ),
    MOOER_ENTITY_TYPE(ModEssences.MOOER_ESSENCE.get(),true,0,20,0,0,
                        0, 0,0,0
    ),
    GOLDEN_MOOER_ENTITY_TYPE(ModEssences.GILDED_MOOER_ESSENCE.get(),true,0,0,0,0,
                        20, 0,0,0
    ),
    MOOSHROOM_MOOER_ENTITY_TYPE(ModEssences.MOOSHROOM_MOOER_ESSENCE.get(),true,0,0,0,0,
                        0, 0,0,20
    ),
    ICE_ISOLATOR(ModEssences.ICE_ISOLATOR_ESSENCE.get(),ModEssences.BRUNO_ESSENCE.get(), false,-4,2,1,0,
            2, 0,0,3
    ),
    BRUNO(ModEssences.BRUNO_ESSENCE.get(),false,-6,4,2,0,
            4, 0,0,6
    ),
    ;

    private final EssenceItem essence;
    private final EssenceItem alternativeEssence;
    private final boolean essenceExchangerOnly;
    private final double vida;
    private final double danoFisico;
    private final double contactArmor;
    private final double agilidade;
    private final double danoProjectile;
    private final double penetration;
    private final double intellect;
    private final double magicDamage;
    private final boolean alternative;

    EssenceData(EssenceItem essence, boolean essenceExchangerOnly, double vida, double danoFisico, double contactArmor, double agilidade, double danoProjectile, double penetration, double intellect, double magicDamage) {
        this(essence, essence, essenceExchangerOnly, vida, danoFisico, contactArmor, agilidade, danoProjectile, penetration, intellect, magicDamage);
    }

        EssenceData(EssenceItem essence, EssenceItem alternativeEssece, boolean essenceExchangerOnly, double vida, double danoFisico, double contactArmor, double agilidade, double danoProjectile, double penetration, double intellect, double magicDamage){
        this.essence = essence;
        this.alternativeEssence = alternativeEssece;
        this.alternative = essence != alternativeEssence;
        this.essenceExchangerOnly = essenceExchangerOnly;
        this.vida = vida;
        this.danoFisico = danoFisico;
        this.contactArmor = contactArmor;
        this.agilidade = agilidade;
        this.danoProjectile = danoProjectile;
        this.penetration = penetration;
        this.intellect = intellect;
        this.magicDamage = magicDamage;
    }

    public static List<Item> getItems(){
        return Arrays.stream(values()).map(EssenceData::getItem).toList();
    }

    public EssenceItem getAlternativeEssence() {
        return alternativeEssence;
    }

    public static List<EssenceItem> getEssences(){
        return Arrays.stream(values()).map(EssenceData::getEssence).toList();
    }

    public boolean hasAlternative() {
        return alternative;
    }

    public List<Double> getStats(int upgrade, int negativeUpgrade){
        List<Double> stats = List.of(vida, danoFisico, contactArmor, agilidade, danoProjectile, penetration, intellect, magicDamage);
        List<Double> leveledStats = new ArrayList<>();
        for(Double stat : stats){
            if(upgrade > 0) {
                if (stat > 0) {
                    leveledStats.add(stat + ((double)(upgrade * upgrade + upgrade)/2));
                    continue;
                }
            }
            if(negativeUpgrade > 0) {
                if (stat < 0) {
                    double newStat = (stat + ((double)(negativeUpgrade * negativeUpgrade + negativeUpgrade)/2));
                    if (newStat > 0) newStat = 0;
                    leveledStats.add(newStat);
                    continue;
                }
            }
            leveledStats.add(stat);
        }
        return leveledStats;
    }

    public boolean isEssenceExchangerOnly() {
        return essenceExchangerOnly;
    }

    public EssenceItem getEssence() {
        return essence;
    }

    public Item getItem() {
        return essence;
    }

    public List<String> getFormatedStats(int upgrade, int negativeUpgrade){
        return formatStats(getStats(upgrade, negativeUpgrade));
    }

    private List<String> formatStats(List<Double> stats){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < stats.size() ; i++){
            double stat = stats.get(i);
            String statString = "";
            if(stat > 0){
                statString = "+";
            }
            statString = statString+""+stat+" " ;
            statString.replace(".0", "");
            list.add(statString);
        }
        list.set(0, list.get(0)+"Health");
        list.set(1, list.get(1)+"Melee Damage");
        list.set(2, list.get(2)+"Raw Armor");
        list.set(3, list.get(3)+"Agility");
        list.set(4, list.get(4)+"Ranged Damage");
        list.set(5, list.get(5)+"Armor Penetration");
        list.set(6, list.get(6)+"Intellect");
        list.set(7, list.get(7)+"Magic Damage");
        return list;
    }
}
