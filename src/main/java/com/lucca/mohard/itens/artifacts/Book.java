package com.lucca.mohard.itens.artifacts;

import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModDamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.List;

public class Book extends GenericArtifact implements IForgeItem {


    public Book(Properties p_i48487_1_) {
        super(p_i48487_1_, 0.8, 30 * 20, 180);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        int itemCooldown =  getPlayerCooldown(player);
        player.getCooldowns().addCooldown(this, itemCooldown);
        TargetingConditions pred = TargetingConditions.forCombat().range(5);
        double dano = player.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get());

        List<LivingEntity> livingEntityList = world.getNearbyEntities(LivingEntity.class, pred, player, player.getBoundingBox().inflate(5));
        if(livingEntityList.size() > 0) {
            for (Entity entity : livingEntityList) {
                DamageSource damage = ModDamageSources.playerMagicAttack(player, player, DirectEntityDamageSource.Type.ANCIENT_BOOK);
                entity.hurt(damage, (float) dano);
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
    }



}
