package com.lucca.mohard.itens.essence.essenceSyntonizer;

import com.lucca.mohard.evolution.PlayerEvolution;
import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilities;
import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilitiesHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class EssenceSyntonizer extends Item {

    public EssenceSyntonizer(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        EssenceSyntonizerTier tier = getItemTier(p_41404_);
        if(tier == null){
            setupTags(p_41404_);
        }
    }

    private void upgradeTier(ItemStack itemStack){
        if(itemStack.getItem() instanceof EssenceSyntonizer) {
            EssenceSyntonizerTier tier = getItemTier(itemStack);
            itemStack.getTag().remove("Tier");
            itemStack.getTag().putInt("Tier", tier.id + 1);

        }
    }


    @Nullable
    public static EssenceSyntonizerTier getItemTier(ItemStack itemStack){
        if(itemStack.getItem() instanceof EssenceSyntonizer) {
            if (itemStack.hasTag()) {
                if (itemStack.getTag().contains("Tier")) {
                    return EssenceSyntonizerTierHelper.getEssenceSyntonizerTierByInt(itemStack.getTag().getInt("Tier"));
                }
            }
        }
        return null;
    }


    private void setupTags(ItemStack itemStack){
        if(itemStack.getItem() instanceof EssenceSyntonizer) {
            if (itemStack.hasTag()) {
                if (itemStack.getTag().contains("Tier")) {
                    itemStack.getTag().remove("Tier");
                }
                if (itemStack.getTag().contains("TierLevel")) {
                    itemStack.getTag().remove("TierLevel");
                }
                itemStack.getTag().putInt("Tier", EssenceSyntonizerTier.NONE.id);
                itemStack.getTag().putInt("TierLevel", 0);
            } else {
                CompoundTag tag = new CompoundTag();
                tag.putInt("Tier", EssenceSyntonizerTier.NONE.id);
                tag.putInt("TierLevel", 0);
                itemStack.setTag(tag);
            }
        }
    }


    private boolean canUpgrade(ItemStack itemStack){
        return getLevel(itemStack) >= getItemTier(itemStack).xpToNextLevel;

    }

    private int getLevel(ItemStack itemStack){
        if(itemStack.hasTag()) {
            if (itemStack.getTag().contains("TierLevel")) {
                return itemStack.getTag().getInt("TierLevel");
            }
        }
        return 0;
    }

    private void addCountToLevel(ItemStack  itemStack, int count){
        int level = getLevel(itemStack);
        itemStack.getTag().remove("TierLevel");
        itemStack.getTag().putInt("TierLevel", level + count);
        EssenceSyntonizerTier tier = getItemTier(itemStack);
        if(tier != null && canUpgrade(itemStack) && tier != EssenceSyntonizerTier.VILIO){
            upgradeTier(itemStack);
        }
    }



    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(getItemTier(stack) != EssenceSyntonizerTier.VILIO){
            addCountToLevel(stack, 1);

            return false;
        }
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack stack = p_41433_.getItemInHand(p_41434_);
        EssenceSyntonizerTier tier = getItemTier(stack);
        if(tier == null){
            setupTags(stack);
            tier = getItemTier(stack);
        }
        EssenceHabilities hability = PlayerEvolution.getPlayerHability(p_41433_);
        if(hability != null){
            p_41433_.getCooldowns().addCooldown(this, EssenceHabilitiesHelper.calculateCooldown(hability, p_41433_, tier));
            putPlayerOnEffect(p_41433_, hability, tier);
            setLastUsedSyntonizer(p_41433_, tier);
            return InteractionResultHolder.consume(stack);
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack item,
                                @Nullable Level world,
                                List<Component> tooltip,
                                TooltipFlag flagIn) {

        super.appendHoverText(item, world, tooltip, flagIn);
        if(item.getItem() instanceof EssenceSyntonizer) {
            EssenceSyntonizerTier tier = getItemTier(item);
            if(tier == null){
                setupTags(item);
                tier = getItemTier(item);
            }
            if(tier != null) {
                tooltip.add(new TextComponent("\u00A79" + tier.name));
                tooltip.add(new TextComponent("\u00A79" + "" + getLevel(item) + " / " + tier.xpToNextLevel));
                tooltip.add(new TextComponent("\u00A79" + "Cooldown: "+ (-(tier.getId()) - ((tier.getId() / 10) * 2.5)) +"s" ));
                tooltip.add(new TextComponent("\u00A79" + "Duration:  +"+ (((tier.getId() / 3) * 2.5) + ((tier.getId() / 10) * 2.5)) +"s"));

            }
        }
    }

    private void putPlayerOnEffect(Player player, EssenceHabilities hability, EssenceSyntonizerTier tier){
        if(PlayerEvolution.onEffect.containsKey(player)){
            if(PlayerEvolution.onEffect.get(player) != null) PlayerEvolution.onEffect.get(player).stopEffects();
        }
        PlayerEvolution.onEffect.put(player, EssenceHabilitiesHelper.generateEssenceHability(hability, player, EssenceHabilitiesHelper.calculateDuration(hability, player, tier)));
        PlayerEvolution.onEffect.get(player).startEffects();
    }
    private void setLastUsedSyntonizer(Player player, EssenceSyntonizerTier tier){
        if(PlayerEvolution.lastSyntonizerTierUsed.containsKey(player)){
            PlayerEvolution.lastSyntonizerTierUsed.remove(player);
        }
        PlayerEvolution.lastSyntonizerTierUsed.put(player, tier);
    }



}
