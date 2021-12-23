package com.lucca.mohard.entities.villagers.nomad.goals;

import com.lucca.mohard.entities.villagers.nomad.NomadEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class ArmsGoal extends Goal {

    private final NomadEntity mob;

    public ArmsGoal(NomadEntity mobEntity) {
        this.mob = mobEntity;
    }

    @Override
    public boolean canUse() {
        return (mob.getArmState().isCrossed() && mob.level.isDay()) || (mob.getArmState().isNormal() && mob.level.isNight());
    }


    @Override
    public void start() {
        super.start();
        if(this.mob.level.isDay()) this.mob.resetArmStates();
        if(this.mob.level.isNight()) this.mob.setCrossing(true);
    }
}
