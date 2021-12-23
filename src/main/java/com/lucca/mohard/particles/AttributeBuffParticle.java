package com.lucca.mohard.particles;

import com.lucca.mohard.help.Methods;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class AttributeBuffParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public AttributeBuffParticle(ClientLevel p_172136_, double p_172137_, double p_172138_, double p_172139_, double p_172140_, double p_172141_, double p_172142_, SpriteSet p_172143_) {
        super(p_172136_, p_172137_, p_172138_, p_172139_, p_172140_, p_172141_, p_172142_);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = p_172143_;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSpriteFromAge(p_172143_);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float p_172146_) {
        float f = ((float) this.age + p_172146_) / (float) this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(p_172146_);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int) (f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class SimpleBuff implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public SimpleBuff(SpriteSet p_172238_) {
            this.sprite = p_172238_;
        }

        public Particle createParticle(SimpleParticleType p_172249_, ClientLevel p_172250_, double p_172251_, double p_172252_, double p_172253_, double p_172254_, double p_172255_, double p_172256_) {
            AttributeBuffParticle buff = new AttributeBuffParticle(p_172250_, p_172251_, p_172252_, p_172253_, 0.0D, 0.0D, 0.0D, this.sprite);
            double SPEED_FACTOR = 0.5D;
            buff.setParticleSpeed(p_172254_ * SPEED_FACTOR / 2.0D, p_172255_ * SPEED_FACTOR, p_172256_ * SPEED_FACTOR / 2.0D);
            buff.setLifetime(p_172250_.random.nextInt(30) + 10);
            return buff;
        }
    }

    public static SimpleParticleType getAttributeParticle(Attribute att){
        if (Attributes.MAX_HEALTH.equals(att)) {
            return ModParticles.HEALTH_BUFF_PARTICLE.get();
        } else if (ModAttributes.PHYSICAL_DAMAGE.get().equals(att)) {
            return ModParticles.PHYSICAL_DAMAGE_BUFF_PARTICLE.get();
        } else if (ModAttributes.RAW_ARMOR.get().equals(att)) {
            return ModParticles.RAW_ARMOR_BUFF_PARTICLE.get();
        } else if (ModAttributes.AGILITY.get().equals(att)) {
            return ModParticles.AGILITY_BUFF_PARTICLE.get();
        } else if (ModAttributes.PROJECTILE_DAMAGE.get().equals(att)) {
            return ModParticles.PROJECTILE_DAMAGE_BUFF_PARTICLE.get();
        } else if (ModAttributes.MAGIC_DAMAGE.get().equals(att)) {
            return ModParticles.MAGIC_DAMAGE_BUFF_PARTICLE.get();
        } else if (ModAttributes.INTELLECT.equals(att)) {
            return ModParticles.INTELLECT_BUFF_PARTICLE.get();
        } else if (ModAttributes.ARMOR_PENETRATION.get().equals(att)) {
            return ModParticles.ARMOR_PENETRATION_BUFF_PARTICLE.get();
        }
        return ModParticles.HEALTH_BUFF_PARTICLE.get();
    }

}
