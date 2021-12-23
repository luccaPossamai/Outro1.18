package com.lucca.mohard.entities.mooers.AI.tasks;

import com.lucca.mohard.entities.mooers.MooerEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class TradeWithPlayerMooerGoal  extends Goal {
    private final MooerEntity mob;

    public TradeWithPlayerMooerGoal(MooerEntity p_25958_) {
        this.mob = p_25958_;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    public boolean canUse() {
        if (!this.mob.isAlive()) {
            return false;
        } else if (this.mob.isInWater()) {
            return false;
        } else if (!this.mob.isOnGround()) {
            return false;
        } else if (this.mob.hurtMarked) {
            return false;
        } else {
            Player player = this.mob.getTradingPlayer();
            if (player == null) {
                return false;
            } else if (this.mob.distanceToSqr(player) > 16.0D) {
                return false;
            } else {
                return player.containerMenu != null;
            }
        }
    }

    public void start() {
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.mob.setTradingPlayer((Player)null);
    }
}
