package com.lucca.mohard.entities.illagers.iceIsolator;

import com.lucca.mohard.itens.artifacts.algidAxe.AlgidAxe;
import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import com.lucca.mohard.setup.init.ModDamageSources;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AlgidAxeMeleeAttackGoal<T extends IceIsolator> extends MeleeAttackGoal {

    protected final IceIsolator iceIsolator;
    protected final AlgidAxe algidAxe;

    public AlgidAxeMeleeAttackGoal(T p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
        this.iceIsolator = p_25552_;
        if(this.iceIsolator.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof AlgidAxe){
            this.algidAxe = (AlgidAxe) this.iceIsolator.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        } else {
            this.algidAxe = (AlgidAxe) ModItens.ALGID_AXE_ARTIFACT.get();
        }
    }

    @Override
    public boolean canUse() {
        return super.canUse() && this.iceIsolator.isRangedAttackOnCooldown() && !this.iceIsolator.isMeleeAttackOnCooldown();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && !this.iceIsolator.isMeleeAttackOnCooldown();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_) {
        double d0 = this.getAttackReachSqr(p_25557_);
        if (p_25558_ <= d0 && !this.iceIsolator.isMeleeAttackOnCooldown()) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            p_25557_.hurt(ModDamageSources.playerMagicAttack(this.mob, this.mob, DirectEntityDamageSource.Type.ALGID_AXE), (float)this.algidAxe.getMeleeDamage(this.mob));
            this.iceIsolator.setMeleeAttackCooldown(100);
            this.iceIsolator.setTarget(null);
        }
    }
}
