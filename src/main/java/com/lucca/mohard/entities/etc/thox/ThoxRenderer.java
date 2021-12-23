package com.lucca.mohard.entities.etc.thox;

import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ThoxRenderer extends MobRenderer<Thox, ThoxModel<Thox>> {

    private static final ResourceLocation THOX_LOCATION = new ResourceLocation("mohard:textures/entity/thox/thox.png");

    public ThoxRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new ThoxModel<>(p_174304_.bakeLayer(ModModelLayers.THOX)), 0.5F);

    }

    @Override
    protected void scale(Thox p_115314_, PoseStack p_115315_, float p_115316_) {
        super.scale(p_115314_, p_115315_, p_115316_);
    }

    @Override
    public ResourceLocation getTextureLocation(Thox p_114482_) {
        return THOX_LOCATION;
    }
}
