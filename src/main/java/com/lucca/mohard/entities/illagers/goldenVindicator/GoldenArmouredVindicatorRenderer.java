package com.lucca.mohard.entities.illagers.goldenVindicator;

import com.lucca.mohard.entities.models.ArmouredVindicatorModel;
import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class GoldenArmouredVindicatorRenderer extends MobRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("mohard", "textures/entity/illager/golden_vindicator.png");

    public GoldenArmouredVindicatorRenderer(EntityRendererProvider.Context p_i50966_1_) {
        super(p_i50966_1_, new ArmouredVindicatorModel(p_i50966_1_.bakeLayer(ModModelLayers.ARMORED_VINDICATOR)), 0.5F);
        this.addLayer(new ItemInHandLayer<GoldenArmoredVindicator, IllagerModel<GoldenArmoredVindicator>>(this) {
            public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, GoldenArmoredVindicator p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
                if (p_225628_4_.isAggressive()) {
                    super.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_, p_225628_8_, p_225628_9_, p_225628_10_);
                }

            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_110775_1_) {
        return this.TEXTURE;
    }

    @Override
    protected void scale(LivingEntity p_115314_, PoseStack p_115315_, float p_115316_) {
        float f = 1.0625F;
        p_115315_.scale(f, f, f);
        super.scale(p_115314_, p_115315_, p_115316_);
    }
}
