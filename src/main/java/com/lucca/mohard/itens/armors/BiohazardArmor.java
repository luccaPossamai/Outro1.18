package com.lucca.mohard.itens.armors;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.itens.armors.types.BiohazardSuit;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Consumer;

import net.minecraft.world.item.Item.Properties;

public class BiohazardArmor extends ArmorItem implements IForgeItem {

    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<Attribute, AttributeModifier> modifiers;

    public BiohazardArmor(EquipmentSlot equip, Properties prop) {
        super(new BiohazardSuit().BIOHAZARD, equip, prop);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[equip.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.material.getDefenseForSlot(slot), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.material.getToughness(), AttributeModifier.Operation.ADDITION));
        builder.put(ModAttributes.MAGIC_RESISTANCE, new AttributeModifier(uuid, "Strong Magic Resistance", 1F, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }

        modifiers = builder.build();
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(stack.getItem() instanceof BiohazardArmor){
            if(slot.getIndex() == 1){
                return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/biohazard_layer_2.png").toString();
            }
            return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/biohazard_layer_1.png").toString();
        }
        return null;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_40390_) {
        return p_40390_ == this.slot ? this.modifiers : super.getDefaultAttributeModifiers(p_40390_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player jogador) {
        if (jogador.getInventory().getArmor(0).getItem() instanceof BiohazardArmor &&
                jogador.getInventory().getArmor(1).getItem() instanceof BiohazardArmor &&
                jogador.getInventory().getArmor(2).getItem() instanceof BiohazardArmor &&
                jogador.getInventory().getArmor(3).getItem() instanceof BiohazardArmor) {
            for (MobEffect e : jogador.getActiveEffectsMap().keySet()) {
                if (!e.isBeneficial()) {
                    jogador.removeEffect(e);
                    for (int i = 0; i < 2; ++i) {
                        jogador.level.addParticle(ParticleTypes.SMOKE, jogador.getRandomX(0.5D), jogador.getRandomY(), jogador.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
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
