package com.lucca.mohard.entities.illagers.goldenPillager;

import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class GoldenArmouredPillagerRenderer extends IllagerRenderer<GoldenArmoredPillager> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("mohard", "textures/entity/illager/golden_pillager.png");

    public GoldenArmouredPillagerRenderer(EntityRendererProvider.Context p_i50966_1_) {
        super(p_i50966_1_, new IllagerModel<>(p_i50966_1_.bakeLayer(ModModelLayers.ARMORED_PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
        this.model.getHat().visible = true;
    }


    @Override
    public ResourceLocation getTextureLocation(GoldenArmoredPillager p_110775_1_) {
        return this.TEXTURE;
    }


    @Override
    protected void scale(GoldenArmoredPillager p_114919_, PoseStack p_114920_, float p_114921_) {
        float f = 1.0625F;
        p_114920_.scale(f, f, f);
        super.scale(p_114919_, p_114920_, p_114921_);
    }
}
