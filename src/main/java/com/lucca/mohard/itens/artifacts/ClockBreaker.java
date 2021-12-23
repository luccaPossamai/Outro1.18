package com.lucca.mohard.itens.artifacts;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ClockBreaker extends GenericArtifact{

    public ClockBreaker(Properties p_i48487_1_) {
        super(p_i48487_1_, 0.0, 60 * 20, 600);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        boolean used = false;
        for(ItemStack itemStack : player.getInventory().items){
            if(player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                player.getCooldowns().removeCooldown(itemStack.getItem());
                used = true;
            }
        }

        if(used) {
            int itemCooldown = getPlayerCooldown(player);
            player.getCooldowns().addCooldown(this, itemCooldown);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
    }

}
