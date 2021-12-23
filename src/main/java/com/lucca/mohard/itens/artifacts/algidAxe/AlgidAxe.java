package com.lucca.mohard.itens.artifacts.algidAxe;

import com.lucca.mohard.itens.artifacts.GenericArtifact;
import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModDamageSources;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;


public class AlgidAxe extends GenericArtifact {


    public AlgidAxe(Properties p_i48487_1_) {
        super(p_i48487_1_, 0.90, 30 * 20, 90);
    }

    @Override
    protected double getMeleeMultiplier() {
        return 0.75;
    }

    @Override
    protected double getRangedMultiplier() {
        return 0.75;

    }
    @Override
    public void releaseUsing(ItemStack item, Level world, LivingEntity entity, int tick) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            int i = this.getUseDuration(item) - tick;
            if (i >= 10) {
                if (!world.isClientSide) {
                    int itemCooldown = getPlayerCooldown(player) / 3;
                    player.getCooldowns().addCooldown(this, itemCooldown);
                    ThrownAlgidAxe thrownAxe = new ThrownAlgidAxe(world, player, this, calculatePower(entity));
                    thrownAxe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F , 1.0F);
                    if (player.getAbilities().instabuild) {
                        thrownAxe.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    world.addFreshEntity(thrownAxe);
                    world.playSound((Player) null, thrownAxe, SoundEvents.ARMOR_EQUIP_IRON, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(item);
                    }
                }


                player.awardStat(Stats.ITEM_USED.get(this));
            }

        }
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!player.getCooldowns().isOnCooldown(this)) {
            int itemCooldown = getPlayerCooldown(player);
            player.getCooldowns().addCooldown(this, itemCooldown);

            double dano = getMeleeDamage(player);

            DamageSource damage = ModDamageSources.playerMagicAttack(player, player, DirectEntityDamageSource.Type.ALGID_AXE);

            entity.hurt(damage, (float) dano);

        }
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.SPEAR;
    }

    public double getRangedDamage(LivingEntity owner){
        double damage = 0.0D;
        damage = damage + owner.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE.get()) * this.getRangedMultiplier();
        damage = damage + owner.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get()) * this.getMultiplier();
        return damage;
    }

    public double getMeleeDamage(LivingEntity owner){
        double damage = 0.0D;
        damage = damage + owner.getAttributeValue(ModAttributes.PHYSICAL_DAMAGE.get()) * this.getMeleeMultiplier();
        damage = damage + owner.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get()) * getMultiplier();
        return damage;
    }


    public int calculatePower(LivingEntity entity){
        int power = 1;
        if(entity.getAttribute(ModAttributes.AGILITY.get()) != null){
            power = power + (int)Math.floor(entity.getAttributeValue(ModAttributes.AGILITY.get()) / 25);
        }
        return power;
    }
}
