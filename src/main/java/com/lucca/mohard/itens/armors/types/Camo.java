package com.lucca.mohard.itens.armors.types;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class Camo {
                                                //B, C, P, CA
    private final int[] DURABILITY    = new int[]{13, 15, 16, 11};
    private final int[] DEFENSE       = new int[]{2, 5, 5, 2};
    private final int DURABILITY_MULTIPLIER = 8;


    public final ArmorMaterial CAMO = new ArmorMaterial() {

        private int getIndex(EquipmentSlot equipmentSlotType){
            return equipmentSlotType.getType().equals(EquipmentSlot.Type.ARMOR) ? equipmentSlotType.getIndex() : 0;

        }

        @Override
        public int getDurabilityForSlot(EquipmentSlot equipmentSlotType) {
            return DURABILITY[getIndex(equipmentSlotType)] * DURABILITY_MULTIPLIER;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot equipmentSlotType) {
            return DEFENSE[getIndex(equipmentSlotType)];
        }

        @Override
        public int getEnchantmentValue() {
            return 12;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_CHAIN;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.SPRUCE_LEAVES);
        }

        @Override
        public String getName() {
            return "camo";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

    };




}
