package com.lucca.mohard.itens.armors;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.itens.armors.types.Pure;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.UUID;


public class PureArmor extends ArmorItem implements IForgeItem {

    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<Attribute, AttributeModifier> modifiers;

    public PureArmor(EquipmentSlot equip, Properties prop) {
        super(new Pure().PURE, equip, prop);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[equip.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.material.getDefenseForSlot(slot), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.material.getToughness(), AttributeModifier.Operation.ADDITION));
        builder.put(ModAttributes.MAGIC_RESISTANCE, new AttributeModifier(uuid, "Strong Magic Resistance", -2.5F, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }

        this.modifiers = builder.build();
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(stack.getItem() instanceof PureArmor){
            if(slot.getIndex() == 1){
                return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/pure_armor_layer_2.png").toString();
            }
            return new ResourceLocation(ExampleMod.MOD_ID, "textures/models/armor/pure_armor_layer_1.png").toString();
        }
        return null;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_40390_) {
        return p_40390_ == this.slot ? this.modifiers : super.getDefaultAttributeModifiers(p_40390_);
    }
}
