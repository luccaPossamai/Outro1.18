package com.lucca.mohard.mechanics.damage;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;

public class DirectEntityDamageSource extends EntityDamageSource {

    @Nullable
    private final Entity owner;
    @Nullable
    private final Type type;

    public DirectEntityDamageSource(String p_19406_, Entity p_19407_, @Nullable Entity p_19408_, Type type) {
        super(p_19406_, p_19407_);
        this.owner = p_19408_;
        this.type = type;
    }

    @Nullable
    public Entity getDirectEntity() {
        return this.entity;
    }

    @Nullable
    public Entity getEntity() {
        return this.owner;
    }

    public Component getLocalizedDeathMessage(LivingEntity p_19410_) {
        Component component = this.owner == null ? this.entity.getDisplayName() : this.owner.getDisplayName();
        String s = "death.attack." + this.msgId;
        s = this.type != null ? s + "."+this.type.name : "";
        return new TranslatableComponent(s, p_19410_.getDisplayName(), component);
    }

    public enum Type{

        ANCIENT_BOOK("ancientBook", Rarity.COMMON),
        DAGGER("floydDagger", Rarity.RARE),
        ALGID_AXE("algidAxe", Rarity.RARE),
        CURSED_FRIENDSHIP("cursedFriendship", Rarity.RARE),
        CONTEMPLATION_SCREAM("contemplationScream", Rarity.RARE),
        ;

        final String name;
        final Rarity rarity;

        Type(String name, Rarity rarity){
            this.name = name;
            this.rarity = rarity;
        }
    }
}
