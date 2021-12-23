package com.lucca.mohard.enchantments;

import com.lucca.mohard.effects.IncinerationEffect;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import javax.annotation.Nullable;


import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentIncinerationStrike extends Enchantment {
    public EnchantmentIncinerationStrike(Rarity raridade,
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
            MobEffect incinerationEffect = getEffect();
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.addEffect(new MobEffectInstance(incinerationEffect,
                    500,
                    nivel,
                    false,
                    false,
                    true));
        }
    }
    @Nullable
    private static MobEffect getEffect(){
        for(RegistryObject<MobEffect> en : Registration.EFFECTS.getEntries()){
            if(en.get() instanceof IncinerationEffect){
                return en.get();
            }
            continue;
        }
        return null;
    }
}
