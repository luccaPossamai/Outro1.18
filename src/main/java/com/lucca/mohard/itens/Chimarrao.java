package com.lucca.mohard.itens;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class Chimarrao extends BowlFoodItem {

    public static final FoodProperties CHIMARRAO = stew(1).build();
    public Chimarrao(Properties p_40682_) {
        super(p_40682_);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(!p_41404_.getOrCreateTag().contains("Uses")) {
            p_41404_.getOrCreateTag().putInt("Uses", new Random().nextInt(5) + 1);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_40684_, Level p_40685_, LivingEntity p_40686_) {
        int uses = p_40684_.getOrCreateTag().getInt("Uses") - 1;
        p_40684_.getOrCreateTag().putInt("Uses", uses);
        if (uses < 0) {
            super.finishUsingItem(p_40684_, p_40685_, p_40686_);
            return new ItemStack(Items.BOWL);
        } else {
            ItemStack itemStack = p_40684_.copy();
            p_40686_.eat(p_40685_, p_40684_);
            return  itemStack;
        }
    }

    private static FoodProperties.Builder stew(int p_150384_) {
        return (new FoodProperties.Builder()).nutrition(p_150384_).saturationMod(0.6F);
    }
}
