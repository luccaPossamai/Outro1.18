package com.lucca.mohard.itens.essence.essenceHabilities;

import com.lucca.mohard.evolution.PlayerEvolution;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public abstract class GenericEssenceHability {

    protected Player player;
    protected int duration;
    protected final int maxDuration;

    public GenericEssenceHability(Player player, int duration) {
        this.player = player;
        this.duration = duration;
        this.maxDuration = duration;
    }


    public void startEffects(){
        this.playSound();
        this.showParticles();
    }


    public void stopEffects(){
        if(PlayerEvolution.onEffect.containsKey(player)) PlayerEvolution.onEffect.remove(player);
    }

    public void tick(){
        this.duration--;
        if(duration <= 0 || (this.player == null || !this.player.isAlive())){
            stopEffects();
        }
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public int getDuration() {
        return duration;
    }

    protected abstract void playSound();

    protected abstract void showParticles();

    public void onHit(LivingEntity livingEntity){}
}
