package com.lucca.mohard.setup.init;

import com.lucca.mohard.particles.AttributeBuffParticle;
import com.lucca.mohard.particles.ModGlowParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticlesSetup {

    @SubscribeEvent
    public static void registerParticle(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticles.BLOCK_OFF_SIDE_PARTICLE.get(), ModGlowParticle.BlockOffSideProvider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.FREEZE_PARTICLE.get(), ModGlowParticle.FreezeProvider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.HEALTH_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.PHYSICAL_DAMAGE_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.RAW_ARMOR_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.AGILITY_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.PROJECTILE_DAMAGE_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.ARMOR_PENETRATION_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.INTELLECT_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.MAGIC_DAMAGE_BUFF_PARTICLE.get(), AttributeBuffParticle.SimpleBuff::new);

    }

}
