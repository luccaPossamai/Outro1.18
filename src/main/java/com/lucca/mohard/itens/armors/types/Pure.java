package com.lucca.mohard.itens.armors.types;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class Pure {

        //B, C, P, CA
        private final int[] DURABILITY    = new int[]{13, 15, 16, 11};
        private final int[] DEFENSE       = new int[]{5, 9, 11, 5};
        private final int DURABILITY_MULTIPLIER = 42;


        public final ArmorMaterial PURE = new ArmorMaterial() {

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
                return 40;
            }

            @Override
            public SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_IRON;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Items.WHITE_CONCRETE);
            }

            @Override
            public String getName() {
                return "pure";
            }

            @Override
            public float getToughness() {
                return 4;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.2F;
            }

        };
}
