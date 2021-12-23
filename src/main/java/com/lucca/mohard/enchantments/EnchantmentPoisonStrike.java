package com.lucca.mohard.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;


import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;


public class EnchantmentPoisonStrike extends Enchantment {
    public EnchantmentPoisonStrike(Rarity raridade,
                                   EnchantmentCategory tipo,
                                   EquipmentSlot[] slot) {
        super(raridade, tipo, slot);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        return (item.getItem() instanceof DiggerItem ||
                item.getItem() instanceof SwordItem ||
                item.getItem() instanceof CrossbowItem ||
                item.getItem() instanceof TridentItem);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return (enchantment != this);
    }

    @Override
    public void doPostAttack(LivingEntity usuario, Entity entity, int nivel) {
        if(entity instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.addEffect(new MobEffectInstance(MobEffect.byId(19),
                    100 * nivel,
                    1,
                    false,
                    false,
                    true));
        }
    }
}
