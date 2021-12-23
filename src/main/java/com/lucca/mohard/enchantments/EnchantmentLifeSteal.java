package com.lucca.mohard.enchantments;

import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;


public class EnchantmentLifeSteal extends Enchantment {
    public EnchantmentLifeSteal(Rarity raridade,
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
    protected boolean checkCompatibility(Enchantment enchantment) {
        return (enchantment != this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void doPostAttack(LivingEntity usuario, Entity entity, int nivel) {
        double vida = usuario.getHealth();
        ItemStack item = usuario.getMainHandItem();
        double dano = usuario.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()).getValue();
        usuario.heal((float)(dano * (0.05 * nivel)));
    }
}
