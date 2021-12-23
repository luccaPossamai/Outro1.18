package com.lucca.mohard.itens.artifacts;

import com.lucca.mohard.capabilities.ModCapabilities;
import com.lucca.mohard.capabilities.mana.ManaCapability;
import com.lucca.mohard.help.Methods;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;


public class GenericArtifact extends Item {

    protected final double multiplier;
    protected final double meleeMultiplier = 0D;
    protected final double rangedMultiplier = 0D;
    protected final int cooldown;
    protected final int manaCost;

    public GenericArtifact(Properties p_i48487_1_, double multiplier, int cooldown, int mana) {
        super(p_i48487_1_);
        this.multiplier = multiplier;
        this.cooldown = cooldown;
        this.manaCost = mana;
    }



    protected double getMeleeMultiplier() {
        return meleeMultiplier;
    }

    protected double getRangedMultiplier() {
        return rangedMultiplier;
    }

    public int getCooldown() {
        return cooldown;
    }

    public double getMultiplier() {
        return multiplier;
    }

    protected int getPlayerCooldown(Player player){
        double playerIntellect = player.getAttributeValue(ModAttributes.INTELLECT); //0 - 100
        double percentLost = 1 - (playerIntellect / 125);
        return (int) (cooldown * percentLost);
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack item,
                                @Nullable Level world,
                                List<Component> tooltip,
                                TooltipFlag flagIn) {

        super.appendHoverText(item, world, tooltip, flagIn);

        if(multiplier != 0) tooltip.add(Methods.stringToText("\u00A79Magic multiplier: " +multiplier));
        if(this.getMeleeMultiplier() != 0) tooltip.add(Methods.stringToText("\u00A79Melee multiplier: " +this.getMeleeMultiplier()));
        if(this.getRangedMultiplier() != 0) tooltip.add(Methods.stringToText("\u00A79Ranged multiplier: " +this.getRangedMultiplier()));
        if(this.getManaCost() != 0) tooltip.add(Methods.stringToText("\u00A79Mana Cost: " +this.getManaCost()));

        tooltip.add(Methods.stringToText("\u00A79Base cooldown: " +cooldown/20+"s"));

    }
}
