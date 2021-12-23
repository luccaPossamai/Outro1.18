package com.lucca.mohard.setup.init;


import com.lucca.mohard.setup.Registration;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {

    public static final RegistryObject<SimpleParticleType> BLOCK_OFF_SIDE_PARTICLE = Registration.PARTICLES.register("block_off_side", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> FREEZE_PARTICLE = Registration.PARTICLES.register("freeze", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> HEALTH_BUFF_PARTICLE = Registration.PARTICLES.register("health_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> PHYSICAL_DAMAGE_BUFF_PARTICLE = Registration.PARTICLES.register("physical_damage_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> RAW_ARMOR_BUFF_PARTICLE = Registration.PARTICLES.register("armor_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> AGILITY_BUFF_PARTICLE = Registration.PARTICLES.register("agility_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> PROJECTILE_DAMAGE_BUFF_PARTICLE = Registration.PARTICLES.register("projectile_damage_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> ARMOR_PENETRATION_BUFF_PARTICLE = Registration.PARTICLES.register("armor_penetration_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> INTELLECT_BUFF_PARTICLE = Registration.PARTICLES.register("intellect_buff", () ->
            new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> MAGIC_DAMAGE_BUFF_PARTICLE = Registration.PARTICLES.register("magic_damage_buff", () ->
            new SimpleParticleType(true));

    public static void register(){
    };

}
