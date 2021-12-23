package com.lucca.mohard.enchantments.essences;

import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilities;
import com.lucca.mohard.itens.essence.EssenceItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;


import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public class EssenceEnchantment extends Enchantment {

    private final EssenceHabilities essenceHability;

    public EssenceEnchantment(EssenceHabilities essenceHability) {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEARABLE, EquipmentSlot.values());
        this.essenceHability = essenceHability;
    }

    public EssenceHabilities getEssenceHability() {
        return essenceHability;
    }

    @Override
    public boolean canEnchant(ItemStack p_92089_1_) {
        return p_92089_1_.getItem() instanceof EssenceItem;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    protected boolean checkCompatibility(Enchantment p_77326_1_) {
        return false;
    }
}
