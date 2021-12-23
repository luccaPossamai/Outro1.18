package com.lucca.mohard.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class ArmorUnbreaking extends Enchantment {

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public ArmorUnbreaking() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, ARMOR_SLOTS);
    }

    @Override
    public boolean canEnchant(ItemStack p_44689_) {
        return super.canEnchant(p_44689_) || p_44689_.getItem() instanceof ShieldItem;
    }

    @Override
    protected boolean checkCompatibility(Enchantment p_44690_) {
        return super.checkCompatibility(p_44690_) && p_44690_ != Enchantments.MENDING;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void doPostAttack(LivingEntity entity, Entity p_44687_, int p_44688_) {
        super.doPostAttack(entity, p_44687_, p_44688_);
        makeUnbreakable(entity);
    }

    @Override
    public void doPostHurt(LivingEntity p_44692_, Entity p_44693_, int p_44694_) {
        super.doPostHurt(p_44692_, p_44693_, p_44694_);
        makeUnbreakable(p_44692_);
    }

    private void makeUnbreakable(LivingEntity entity){
        entity.getArmorSlots().forEach(armor -> {
            Map<Enchantment, Integer> enchantmentIntegerMap = EnchantmentHelper.getEnchantments(armor);
            if(enchantmentIntegerMap.containsKey(this) && !armor.getOrCreateTag().getBoolean("Unbreaking")){
                armor.getOrCreateTag().putBoolean("Unbreakable", true);
            }
        });

    }
}
