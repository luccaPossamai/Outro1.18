package com.lucca.mohard.itens.armors;

import com.lucca.mohard.help.Methods;
import com.lucca.mohard.itens.armors.types.Magic;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;


import net.minecraft.world.item.Item.Properties;

public class MagicArmor extends DyeableArmorItem implements IForgeItem {

    public MagicArmor(EquipmentSlot equip, Properties properties) {
        super(new Magic().MAGIC, equip, properties);
    }

    @Override
    public void setColor(ItemStack item, int color) {
        if(item.getItem() instanceof DyeableArmorItem){
            DyeableArmorItem colorItem = (DyeableArmorItem) item.getItem();
            if(!colorItem.hasCustomColor(item) && getAttributeColor(color) != null){
                super.setColor(item, color);
            }

        }
    }
    @Nullable
    private Attribute getAttributeColor(int color){
        if(color == 1908001){
            return ModAttributes.RAW_ARMOR.get();
            //BLACK

        } else if(color == 15961002){
            //PINK
            return ModAttributes.MAGIC_DAMAGE.get();

        } else if(color == 11546150){
            return ModAttributes.PHYSICAL_DAMAGE.get();
            //RED

        } else if(color == 1481884){
            return ModAttributes.AGILITY.get();
            //CYAN

        } else if(color == 8991416){
            return ModAttributes.INTELLECT;
            //ROXO

        } else if(color == 3949738){
            return ModAttributes.ARMOR_PENETRATION.get();
            //AZUL

        } else if(color == 16383998){
            return ModAttributes.PROJECTILE_DAMAGE.get();
            //WHITE

        } else if(color == 6192150){
            return Attributes.MAX_HEALTH;

        } else {
            return null;

        }

    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player jogador) {
        List<ItemStack> armor = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (jogador.getInventory().getArmor(i).getItem() instanceof MagicArmor) {
                armor.add(jogador.getInventory().getArmor(i));
            }
        }
        if(armor.size() > 0) {
            List<MobEffect> effects = new ArrayList<>();
            Map<MobEffect, Integer> effectAmp = new HashMap<>();
            for (ItemStack armorItem : armor) {
                MobEffect eff = Methods.getAttributeEffect(this.getAttributeColor(this.getColor(armorItem)));
                if(eff != null) {
                    if (effects.contains(eff)) {
                        int value = effectAmp.get(eff) + 1;
                        effectAmp.remove(eff);
                        effectAmp.put(eff, value);
                    } else {
                        effects.add(eff);
                        effectAmp.put(eff, 0);
                    }
                }

            }
            for (MobEffect eff : effects) {
                if(eff != null) {
                    if (effectAmp.containsKey(eff)) {
                        jogador.addEffect(new MobEffectInstance(eff,
                                5,
                                effectAmp.get(eff),
                                true,
                                false,
                                true));
                    } else {
                        jogador.addEffect(new MobEffectInstance(eff,
                                5,
                                0,
                                true,
                                false,
                                true));

                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, A properties) {
                HumanoidModel<?> model = slot.equals(EquipmentSlot.LEGS) ? new HumanoidModel<>(ModModelLayers.PLAYER_FIT_INNER_ARMOR.bakeRoot())
                        : new HumanoidModel<>(ModModelLayers.PLAYER_FIT_OUTER_ARMOR.bakeRoot());

                model.crouching = properties.crouching;
                model.riding = properties.riding;
                model.young = properties.young;

                return (A) model;

            }
        });
    }
}
