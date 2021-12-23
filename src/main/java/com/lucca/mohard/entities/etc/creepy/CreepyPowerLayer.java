package com.lucca.mohard.entities.etc.creepy;

import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreepyPowerLayer extends EnergySwirlLayer<Creepy, CreepyModel<Creepy>> {
    private static final ResourceLocation POWER_LOCATION = new ResourceLocation("mohard", "textures/entity/creepy/creepy.png");
    private final CreepyModel<Creepy> model;

    public CreepyPowerLayer(RenderLayerParent<Creepy, CreepyModel<Creepy>> p_174471_, EntityModelSet p_174472_) {
        super(p_174471_);
        this.model = new CreepyModel<>(p_174472_.bakeLayer(ModModelLayers.CREEPY_ARMOR));
    }

    protected float xOffset(float p_116683_) {
        return p_116683_ * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return POWER_LOCATION;
    }

    protected EntityModel<Creepy> model() {
        return this.model;
    }
}