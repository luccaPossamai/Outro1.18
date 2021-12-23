package com.lucca.mohard.mechanics.damage;

import com.lucca.mohard.evolution.PlayerEvolution;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModSounds;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber()
public class DamageEvents {



    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void danoRecebido(LivingHurtEvent event){
        double rawArmor = getAttributeValue(event.getEntityLiving(), ModAttributes.RAW_ARMOR.get());
        double armorPen = getAttributeValue(event.getSource().getEntity(), ModAttributes.ARMOR_PENETRATION.get());
        double armorPenPer = getAttributeValue(event.getSource().getEntity(), ModAttributes.ARMOR_PENETRATION.get()) * 0.008;
        double originalDamage = event.getAmount();
        if(isDamageValid(event.getSource())) {
            double totalArmor = (rawArmor - armorPen) * (1 - armorPenPer);
            if (event.getSource().isBypassArmor()) {
                totalArmor = 0;
            }
            if (event.getSource().isMagic()) {
                event.setAmount(event.getAmount() * (float) (1 - PlayerEvolution.magicImunne(event.getEntityLiving())));
            }
            event.setAmount(event.getAmount() - (float) totalArmor);
            playSound(originalDamage, event.getAmount(), event.getEntityLiving());
        }


    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void danoGeral(LivingHurtEvent event){
        Entity causador = event.getSource().getEntity();
        double dano =  (float) getAttributeValue(causador, getAttributeSource(event.getSource()));
        System.out.println("dano: "+ dano);
        System.out.println("danoOriginal: "+ event.getAmount());
        Entity entity = event.getSource() != null ? event.getSource().getEntity() : null;
        dano = calculateDamage(entity, event.getAmount(), dano, getAttributeSource(event.getSource()));
        event.setAmount((float)dano);

    }



    @SubscribeEvent(priority = EventPriority.LOW)
    public static void danoExplosao(LivingHurtEvent event){
        Entity causador = event.getSource().getEntity();
        float dano =  event.getAmount() + (float) getAttributeValue(causador, ModAttributes.PHYSICAL_DAMAGE.get());
        if(event.getSource().isExplosion()) {
            if(event.getSource().getEntity() != null){
                if(event.getSource().getEntity() instanceof Player) {
                } else {
                    event.setAmount(5 * dano);
                }
            } else {
                event.setAmount(5 * dano);
            }
        }
    }

    @SubscribeEvent
    public static void damageLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            event.getEntity().hurt(DamageSource.LIGHTNING_BOLT, entity.getMaxHealth() / 5);
        }
    }


    private static double getAttributeValue(Entity entity, Attribute attribute){
        if(attribute != null) {
            if (entity instanceof Projectile) {
                Projectile tiro = (Projectile) entity;
                if (tiro.getOwner() instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) tiro.getOwner();
                    return getAttributeEspecificValue(livingEntity, attribute);
                }
            }
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                return getAttributeEspecificValue(livingEntity, attribute);
            }
        }
        return 0;
    }

    @SuppressWarnings("DO NOT USE")
    private static double getAttributeEspecificValue(LivingEntity livingEntity, Attribute attribute){
        if(attribute != null) {
            if (livingEntity.getAttribute(attribute) != null) {
                return livingEntity.getAttributeValue(attribute);
            }
        }
        return 0.0;
    }
    private static boolean isDamageValid(DamageSource damageSource){
        return (damageSource != null && damageSource.getEntity() != null);
    }

    @Nullable
    private static Attribute getAttributeSource(DamageSource damageSource) {
        if (damageSource.isMagic() && !damageSource.isBypassArmor()) return null;
        if (damageSource.isProjectile()) return ModAttributes.PROJECTILE_DAMAGE.get();
        if (damageSource.isMagic()) return ModAttributes.MAGIC_DAMAGE.get();
        if (damageSource.getEntity() != null) return ModAttributes.PHYSICAL_DAMAGE.get();

        return null;
    }
    private static double calculateDamage(@Nullable Entity damager, double originalDano, double dano, Attribute att){
        if(att != null) {

            //double danoT = att.equals(ModAttributes.MAGIC_DAMAGE.get()) ? Functions.lateGameScale(dano) :
            //        att.equals(ModAttributes.PROJECTILE_DAMAGE.get()) ? Functions.earlyGameScale(dano) :
            //                Functions.constantGameScale(dano);


            double efic = getDamageEfficiency(damager, att, originalDano);
            return (dano * efic) + originalDano;
        }
        return dano + originalDano;
    }

    private static double getDamageEfficiency(@Nullable Entity damager, Attribute att, double originalDamage){
        double total = 0.1;
        if(damager instanceof Projectile){
            Projectile proj = (Projectile) damager;
            if(!(proj.getOwner() instanceof Player)) return 1.0;
        } else {
            if(!(damager instanceof Player)) return 1.0;
        }
        if(att.equals(ModAttributes.MAGIC_DAMAGE.get()) && damager instanceof LivingEntity) {
            return 1.0;


        } else {
            total = originalDamage / 10;
        }

        return total;
    }
    private static void playSound(double originalDamage, double damage, LivingEntity vitima){
        if(damage <= 0){
            vitima.playSound(ModSounds.NO_DAMAGE_SOUND, 0.850F, new Random().nextFloat() * 0.2F + 0.85F);
        } else if(damage <= originalDamage * 0.4F){
            vitima.playSound(ModSounds.LOW_DAMAGE_SOUND, 0.85F, new Random().nextFloat() * 0.2F + 0.85F);
        }
    }
}
