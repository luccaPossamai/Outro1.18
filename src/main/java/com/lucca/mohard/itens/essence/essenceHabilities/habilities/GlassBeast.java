package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class GlassBeast extends GenericEssenceHability {

    private double origialPhysicalDamage;
    private double origialProjectileDamage;
    private double origialMagicDamage;
    private double originalHealth;

    public GlassBeast(Player player, int duration){
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        origialPhysicalDamage = this.player.getAttributeValue(ModAttributes.PHYSICAL_DAMAGE.get());
        origialProjectileDamage = this.player.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE.get());
        origialMagicDamage = this.player.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get());
        originalHealth = this.player.getMaxHealth();

        this.player.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()).setBaseValue(this.origialPhysicalDamage * 2);
        this.player.getAttribute(ModAttributes.PROJECTILE_DAMAGE.get()).setBaseValue(this.origialProjectileDamage * 2);
        this.player.getAttribute(ModAttributes.MAGIC_DAMAGE.get()).setBaseValue(this.origialMagicDamage * 2);
        this.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(1);

        this.player.hurt(DamageSource.IN_FIRE.bypassArmor(), 0.01F);
    }


    @Override
    public void stopEffects() {
        super.stopEffects();
        this.player.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()).setBaseValue(this.origialPhysicalDamage);
        this.player.getAttribute(ModAttributes.PROJECTILE_DAMAGE.get()).setBaseValue(this.origialProjectileDamage);
        this.player.getAttribute(ModAttributes.MAGIC_DAMAGE.get()).setBaseValue(this.origialMagicDamage);
        this.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.originalHealth);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }

}
