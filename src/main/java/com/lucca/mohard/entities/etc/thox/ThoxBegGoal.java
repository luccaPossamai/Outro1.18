package com.lucca.mohard.entities.etc.thox;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class ThoxBegGoal extends Goal {

    private final Thox thox;
    private Player player;
    private final Level level;
    private final float lookDistance;
    private int lookTime;
    private final TargetingConditions begTargeting;

    public ThoxBegGoal(Thox p_25063_, float p_25064_) {
        this.thox = p_25063_;
        this.level = p_25063_.level;
        this.lookDistance = p_25064_;
        this.begTargeting = TargetingConditions.forNonCombat().range((double)p_25064_);
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.begTargeting, this.thox);
        return this.player == null ? false : this.playerHoldingInteresting(this.player);
    }

    public boolean canContinueToUse() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.thox.distanceToSqr(this.player) > (double)(this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.lookTime > 0 && this.playerHoldingInteresting(this.player);
        }
    }

    public void start() {
        this.thox.setIsInterested(true);
        this.lookTime = 40 + this.thox.getRandom().nextInt(40);
    }

    public void stop() {
        this.thox.setIsInterested(false);
        this.player = null;
    }

    public void tick() {
        this.thox.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.thox.getMaxHeadXRot());
        --this.lookTime;
    }

    private boolean playerHoldingInteresting(Player p_25067_) {
        for(InteractionHand interactionhand : InteractionHand.values()) {
            ItemStack itemstack = p_25067_.getItemInHand(interactionhand);
            if (this.thox.isTame() && itemstack.is(Items.BONE)) {
                return true;
            }

            if (this.thox.isFood(itemstack)) {
                return true;
            }
        }

        return false;
    }
}
