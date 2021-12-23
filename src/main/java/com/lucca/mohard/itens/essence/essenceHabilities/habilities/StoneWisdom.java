package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.setup.init.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;


public class StoneWisdom extends GenericEssenceHability {

    private double originalSpeed;

    public StoneWisdom(Player player, int duration){
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        calculateSpeed();
        instantHealth();
    }


    @Override
    public void stopEffects() {
        super.stopEffects();
        this.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.originalSpeed);
    }

    @Override
    public void tick() {
        super.tick();
        calculateHealth();
    }

    private void calculateHealth(){
        float maxHealth = this.player.getMaxHealth();
        float health = this.player.getHealth();
        float percentual = 0.001F;
        player.setHealth(Math.min((health + (maxHealth * percentual)), maxHealth));
    }

    private void calculateSpeed(){
        double speed = this.player.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
        if(speed != 0){
            this.originalSpeed = speed;
            this.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0F);
        }
    }

    private void instantHealth(){
        float maxHealth = this.player.getMaxHealth();
        float health = this.player.getHealth();
        float percentual = 0.1F;
        player.setHealth(Math.min((health + (maxHealth * percentual)), maxHealth));
    }

    @Override
    public void playSound() {
        this.player.level.playSound(this.player, this.player.getX(), this.player.getY(), this.player.getZ(),ModSounds.STONE_WISDOM_SOUND,SoundSource.PLAYERS, 1.0F , 1.0F);

    }

    @Override
    protected void showParticles() {

    }
}
