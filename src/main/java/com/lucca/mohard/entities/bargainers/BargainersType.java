package com.lucca.mohard.entities.bargainers;

import com.google.common.collect.ImmutableMap;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModItens;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public enum BargainersType {

    GASPI(1, "gaspi", false, ModModelLayers.BARGAINERS_SMALL, new ItemStack(ModItens.AXE.get(), 1), null),

    CUSPILE(2,"cuspile", true, ModModelLayers.BARGAINERS_TALL, new ItemStack(Items.BOW, 1), null),

    TUSJUS(3, "tusjus", false, ModModelLayers.BARGAINERS_NORMAL, null, null),

    OEL(4, "oel", false, ModModelLayers.BARGAINERS_NORMAL, null, null),

    NIIPPA(5, "niippa", false, ModModelLayers.BARGAINERS_NORMAL, null, null),//

    SAATPON(6, "saatpon", false, ModModelLayers.BARGAINERS_VERY_SMALL, null, null),

    PABITTAS(7, "pabittas", false, ModModelLayers.BARGAINERS_TALL, null, null),//

    NOJAS(8, "nojas", false, ModModelLayers.BARGAINERS_SMALL, null, null),

    AAN(9, "aan", false, ModModelLayers.BARGAINERS_SMALL, new ItemStack(Items.PAINTING, 1), null),

    HO_CINSAL(10, "ho_cinsal", false, ModModelLayers.BARGAINERS_VERY_TALL, null, null),

    CINVET(11, "cinvet", false, ModModelLayers.BARGAINERS_TALL, null, null),

    TASBEGO(12, "tasbego", false, ModModelLayers.BARGAINERS_SMALL, null, null);//

    private int id;
    String name;
    boolean leftHanded;
    ModelLayerLocation layerDefinition;
    private ItemStack mainHandItem;
    private ItemStack offHandItem;

    BargainersType(int id, String name, boolean leftHanded, ModelLayerLocation layer, ItemStack mainHandItem, ItemStack offHandItem){
        this.id = id;
        this.name = name;
        this.leftHanded = leftHanded;
        this.layerDefinition = layer;
        this.mainHandItem = mainHandItem;
        this.offHandItem = offHandItem;
    }

    public int getId() {
        return id;
    }

    public ItemStack getMainHandItem() {
        return mainHandItem != null ? mainHandItem : ItemStack.EMPTY;
    }

    public ItemStack getOffHandItem() {
        return offHandItem != null ? offHandItem : ItemStack.EMPTY;
    }



    public static class BargainersTypeHelper {

        private final static Map<BargainersType, Map<Attribute, Double>> BARGAINERS_TYPE_ATTRIBUTES = attributesSetup();

        public static BargainersType generateRandomBargainersType(){
            BargainersType[] types = BargainersType.values();
            return types[(int) Math.floor(Math.random() * types.length)];
        }

        public static Map<Attribute, Double> getAttributes(BargainersType type){
            return BARGAINERS_TYPE_ATTRIBUTES.get(type);
        }

        public static BargainersType getBargainersType(int id){
            BargainersType[] types = BargainersType.values();
            return types[id - 1];
        }

        public static BargainersType getBargainersType(BargainersEntity entity){
            return entity.getBargainersType();
        }

        private static Map<BargainersType, Map<Attribute, Double>> attributesSetup(){
            Map<BargainersType, Map<Attribute, Double>> attributes = new HashMap<>();

            attributes.put(BargainersType.GASPI,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.MAX_HEALTH, 300.0D)));

            attributes.put(BargainersType.CUSPILE,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.MOVEMENT_SPEED, 0.5D)));

            attributes.put(BargainersType.TUSJUS,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.ATTACK_DAMAGE, 18D)));

            attributes.put(BargainersType.OEL,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.PROJECTILE_DAMAGE.get(), 50D)));

            attributes.put(BargainersType.NIIPPA,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.AGILITY.get(), 70D)));

            attributes.put(BargainersType.SAATPON,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.INTELLECT, 70D)));

            attributes.put(BargainersType.PABITTAS,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.MOVEMENT_SPEED, 0.7D,
                            Attributes.MAX_HEALTH, 50D)));

            attributes.put(BargainersType.NOJAS,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.MOVEMENT_SPEED, 0.15D,
                            Attributes.MAX_HEALTH, 400D)));

            attributes.put(BargainersType.AAN,
                    formatWithBaseAttributes(ImmutableMap.of(Attributes.ATTACK_DAMAGE, 18D,
                            Attributes.MAX_HEALTH, 50D, ModAttributes.PHYSICAL_DAMAGE.get(), 26D)));

            attributes.put(BargainersType.HO_CINSAL,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.MAGIC_DAMAGE.get(), 100D,
                            Attributes.MAX_HEALTH, 120D)));

            attributes.put(BargainersType.CINVET,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.ARMOR_PENETRATION.get(), 100D,
                            Attributes.MAX_HEALTH, 20D)));

            attributes.put(BargainersType.TASBEGO,
                    formatWithBaseAttributes(ImmutableMap.of(ModAttributes.AGILITY.get(), 100D,
                            Attributes.MAX_HEALTH, 20D)));


            return attributes;

        }

        private static Map<Attribute, Double> formatWithBaseAttributes(Map<Attribute, Double> map) {
            Map<Attribute, Double> attributes = new HashMap<>();

            attributes.put(Attributes.MOVEMENT_SPEED, 0.3D);
            attributes.put(Attributes.MAX_HEALTH, 100.0D);
            attributes.put(Attributes.ATTACK_DAMAGE, 1.0D);
            attributes.put(ModAttributes.PHYSICAL_DAMAGE.get(), 1.0D);
            attributes.put(Attributes.FOLLOW_RANGE, 50.0D);
            attributes.put(ModAttributes.RAW_ARMOR.get(), 10D);
            attributes.put(ModAttributes.PROJECTILE_DAMAGE.get(), 1D);
            attributes.put(ModAttributes.AGILITY.get(), 30D);
            attributes.put(ModAttributes.ARMOR_PENETRATION.get(), 30D);
            attributes.put(ModAttributes.MAGIC_DAMAGE.get(), 1D);
            attributes.put(ModAttributes.INTELLECT, 30D);

            for(Attribute att : map.keySet()){
                attributes.remove(att);
                attributes.put(att, map.get(att));
            }

            return attributes;
        }


    }
}



