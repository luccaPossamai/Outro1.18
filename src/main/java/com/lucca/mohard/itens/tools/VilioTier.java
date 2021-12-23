package com.lucca.mohard.itens.tools;

import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class VilioTier implements Tier{

    @Override
    public int getUses() {
        return 3062;
    }

    @Override
    public float getSpeed() {
        return 10F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5F;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 5;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItens.TRUE_VOID_REMNANTS.get());
    }
}
