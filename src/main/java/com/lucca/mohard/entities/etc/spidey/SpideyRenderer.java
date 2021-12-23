package com.lucca.mohard.entities.etc.spidey;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpideyRenderer<T extends Spidey> extends MobRenderer<T, SpiderModel<T>> {

    private static final ResourceLocation SPIDEY_LOCATION = new ResourceLocation("mohard:textures/entity/spidey/spidey.png");
    private static final float SCALE = 0.3F;

    public SpideyRenderer(EntityRendererProvider.Context p_173946_) {
        super(p_173946_, new SpiderModel<>(p_173946_.bakeLayer(ModelLayers.SPIDER)), 0.8F);
        this.addLayer(new SpideyEyesLayer<>(this));
        this.shadowRadius = SCALE;
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return SPIDEY_LOCATION;
    }

    protected void scale(Spidey p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(SCALE, SCALE, SCALE);
    }
}

