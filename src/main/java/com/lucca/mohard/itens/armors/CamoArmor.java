package com.lucca.mohard.itens.armors;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.itens.armors.types.Camo;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.function.Consumer;


@Mod.EventBusSubscriber
public class CamoArmor extends ArmorItem implements IForgeItem {


    public CamoArmor(EquipmentSlot equip, Properties properties) {
        super(new Camo().CAMO, equip, properties);
    }



    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(stack.getItem() instanceof CamoArmor){
            if(slot.getIndex() == 1){
                return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/camo_armor_layer_2.png").toString();
            }
            return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/camo_armor_layer_1.png").toString();
        }
        return null;
    }

    private static boolean ableToHideArmor(Player jogador){
        if(!hasHarmulEffect(jogador) &&
                !jogador.hasEffect(MobEffects.GLOWING) &&
                !jogador.isSprinting() &&
                wearingArmor(jogador)){
            return true;
        }
        return false;
    }
    private static boolean wearingArmor(Player jogador){
        if(jogador.getInventory().getArmor(0).getItem() instanceof CamoArmor &&
                jogador.getInventory().getArmor(1).getItem() instanceof CamoArmor &&
                jogador.getInventory().getArmor(2).getItem() instanceof CamoArmor &&
                jogador.getInventory().getArmor(3).getItem() instanceof CamoArmor) {
            return true;
        }
        return false;
    }

    private static boolean hasHarmulEffect(Player jogador){
        for(MobEffectInstance eff : jogador.getActiveEffects()){
            if(eff.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void naoAtaca(LivingSetAttackTargetEvent event){
        if(event.getTarget() instanceof  Player){
            Player jogador = (Player) event.getTarget();
            if(event.getEntity() instanceof Mob && ableToHideArmor(jogador)){
                Mob mob = (Mob) event.getEntity();
                mob.setTarget(null);

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
