package com.lucca.mohard.itens.armors;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.itens.armors.types.Demon;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.UUID;


import net.minecraft.world.item.Item.Properties;

public class DemonArmor extends ArmorItem implements IForgeItem{

    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<Attribute, AttributeModifier> modifiers;

    public DemonArmor(EquipmentSlot equip, Properties prop) {
        super(new Demon().DEMON, equip, prop);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[equip.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.material.getDefenseForSlot(slot), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.material.getToughness(), AttributeModifier.Operation.ADDITION));
        builder.put(ModAttributes.MAGIC_RESISTANCE, new AttributeModifier(uuid, "Strong Magic Resistance", 2F, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }

        this.modifiers = builder.build();
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(stack.getItem() instanceof DemonArmor){
            if(slot.getIndex() == 1){
                return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/demon_armor_layer_2.png").toString();
            }
            return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/demon_armor_layer_1.png").toString();
        }
        return null;
    }



    @Override
    public void onArmorTick(ItemStack stack, Level world, Player jogador) {
        if(jogador.getInventory().getArmor(0).getItem() instanceof DemonArmor &&
                jogador.getInventory().getArmor(1).getItem() instanceof DemonArmor &&
                jogador.getInventory().getArmor(2).getItem() instanceof DemonArmor &&
                jogador.getInventory().getArmor(3).getItem() instanceof DemonArmor ){
            jogador.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,
                    5,
                    0,
                    true,
                    false,
                    true));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_40390_) {
        return p_40390_ == this.slot ? this.modifiers : super.getDefaultAttributeModifiers(p_40390_);
    }

}
