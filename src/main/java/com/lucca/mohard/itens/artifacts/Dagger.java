package com.lucca.mohard.itens.artifacts;

import com.lucca.mohard.capabilities.ModCapabilities;
import com.lucca.mohard.capabilities.mana.ManaCapability;
import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModDamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.common.extensions.IForgeItem;

public class Dagger extends GenericArtifact implements IForgeItem {

    public Dagger(Properties p_i48487_1_) {
        super(p_i48487_1_, 1.5, 10 * 20, 60);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        ManaCapability manaCapability = player.getCapability(ModCapabilities.MANA_CAPABILITY).orElse(null);
        if (!player.getCooldowns().isOnCooldown(this)) {
            if (manaCapability.getMana() >= this.manaCost) {

                DamageSource damage = ModDamageSources.playerMagicAttack(player, player, DirectEntityDamageSource.Type.DAGGER);
                double dano = player.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get());
                entity.hurt(damage, (float) (dano * multiplier));

                player.getCooldowns().addCooldown(this, this.getPlayerCooldown(player));





            }
        }
        return true;
    }


}
