package com.lucca.mohard.mechanics.damage;

import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ProjectileEvents {



    @SubscribeEvent
    public static void projectile(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof Projectile){
            Projectile projectile = (Projectile) event.getEntity();
            Vec3 vector = projectile.getDeltaMovement();
            double multiplier = getShootPowerMultiplier(projectile.getOwner());
            projectile.setDeltaMovement((vector.x * multiplier), vector.y * multiplier, (vector.z * multiplier));

        }
    }

    private static double getShootPowerMultiplier(@Nullable Entity entity){
        double multiplier = 1.0;
        if(entity != null){
            if(entity instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) entity;
                Attribute att = livingEntity.getAttribute(ModAttributes.AGILITY.get()).getAttribute();
                double agilityValue = att != null ? livingEntity.getAttributeValue(ModAttributes.AGILITY.get()) : 0.0;
                multiplier = multiplier + (agilityValue / 100);
            }
        }
        return multiplier;
    }


}
