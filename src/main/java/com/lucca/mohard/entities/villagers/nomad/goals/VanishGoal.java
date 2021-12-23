package com.lucca.mohard.entities.villagers.nomad.goals;

import com.lucca.mohard.entities.villagers.nomad.NomadEntity;
import com.lucca.mohard.evolution.GameLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class VanishGoal extends Goal {

    private final Mob mob;
    private int cooldown;
    private int halfCooldown;

    public VanishGoal(Mob mobEntity, int count) {
        this.mob = mobEntity;
        this.cooldown = count;
        this.halfCooldown = cooldown / 2;
    }

    @Override
    public boolean canUse() {
        boolean can = false;
        if(mob.getLastDamageSource() != null){
            can = mob.getLastDamageSource().getEntity() instanceof Player;
        }
        return can && (GameLevel.getLevel(mob.getLastDamageSource().getEntity().getX(),
                mob.getLastDamageSource().getEntity().getZ(),
                mob.getLastDamageSource().getEntity().level)) < 500;

    }

    @Override
    public boolean canContinueToUse() {
        return this.cooldown > 0;
    }

    @Override
    public void start() {
        super.start();
        if(mob instanceof NomadEntity){
            NomadEntity nomadEntity = (NomadEntity) mob;
            nomadEntity.setVanishing(true);
            nomadEntity.setDenying(true);
        }
        //TODO SOUND
    }


    @Override
    public void tick() {
        super.tick();
        this.cooldown--;
        if(cooldown <= halfCooldown) {
            if (mob instanceof NomadEntity) {
                NomadEntity nomadEntity = (NomadEntity) mob;
                nomadEntity.setDenying(false);
            }
        }


    }

    @Override
    public void stop() {
        super.stop();
        this.mob.remove(Entity.RemovalReason.CHANGED_DIMENSION);
        //TODO SOUND PARTICLUS
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }
}
