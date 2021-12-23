package com.lucca.mohard.itens.artifacts;

import com.lucca.mohard.help.Methods;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class Scroll extends GenericArtifact{

    public Scroll(Properties p_i48487_1_) {
        super(p_i48487_1_, 0.5, 20, 120);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack p_111207_1_, Player p_111207_2_, LivingEntity p_111207_3_, InteractionHand p_111207_4_) {
        if(!getBind(p_111207_1_)){
            if(p_111207_3_.getActiveEffects().size() > 0) {
                setBind(p_111207_1_, true);
                List<MobEffectInstance> effects = new ArrayList<>(p_111207_3_.getActiveEffects());
                for(MobEffectInstance effectInstance : effects) p_111207_3_.removeEffect(effectInstance.getEffect());
                writeEffects(effects, p_111207_1_);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.FAIL;
        }


        return InteractionResult.FAIL;



    }

    public static void setBind(ItemStack itemStack, boolean value){
        if(itemStack.getItem() instanceof Scroll){
            if(itemStack.hasTag()){
                if(itemStack.getTag().contains("Binded")){
                    itemStack.getTag().remove("Binded");
                }
                itemStack.getTag().putBoolean("Binded", value);
            } else {
                CompoundTag tag = new CompoundTag();
                tag.putBoolean("Binded", value);
                itemStack.setTag(tag);
            }
        }
    }

    public static boolean getBind(ItemStack itemStack){
        if(itemStack.getItem() instanceof Scroll){
            if(itemStack.hasTag()) {
                if (itemStack.getTag().get("Binded") != null) {
                    return itemStack.getTag().getBoolean("Binded");
                }
            }
        }
        return false;
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (getBind(stack) && entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            for (MobEffectInstance effectInstance : getEffects(stack)) {
                livingEntity.addEffect(effectInstance);
            }
            stack.shrink(1);
        }
        return false;
    }

    private void writeEffects(List<MobEffectInstance> effectInstances, ItemStack itemStack) {
        List<Integer> ids = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        List<Integer> duration = new ArrayList<>();
        for (MobEffectInstance effectInstance : effectInstances) {
            ids.add(MobEffect.getId(effectInstance.getEffect()));
            level.add(effectInstance.getAmplifier());
            duration.add(effectInstance.getDuration());
        }
        itemStack.getTag().putIntArray("Effects", ids);
        itemStack.getTag().putIntArray("Levels", level);
        itemStack.getTag().putIntArray("Durations", duration);


    }

    private List<MobEffectInstance> getEffects(ItemStack itemStack) {
        List<MobEffectInstance> effectInstances = new ArrayList<>();
        if (itemStack.getTag().contains("Effects") && itemStack.getTag().contains("Levels") && itemStack.getTag().contains("Durations")) {
            int[] ids = itemStack.getTag().getIntArray("Effects");
            int[] level = itemStack.getTag().getIntArray("Levels");
            int[] duration = itemStack.getTag().getIntArray("Durations");
            if (ids.length != level.length || ids.length != duration.length) return new ArrayList<>();

            for (int i = 0; i < ids.length; i++) {
                effectInstances.add(new MobEffectInstance(MobEffect.byId(ids[i]), (int) Math.floor(duration[i] * multiplier), (int) Math.floor(level[i] * multiplier)));
            }


        }
        return effectInstances;
    }
    @Override
    public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(item, world, tooltip, flagIn);
        if(getBind(item)) {

            List<MobEffectInstance> effectInstances = getEffects(item);
            System.out.println(effectInstances);
            if (effectInstances.size() > 0) {
                for (int i = 0; i < effectInstances.size(); i++) {
                    Component effectText = Methods.formatStringEffect(effectInstances.get(i), i == 0 ? "Effects: " : "         ");
                    tooltip.add(effectText);
                }
            }

        } else {
            tooltip.add(Methods.stringToText("Empty"));
        }
    }


}
