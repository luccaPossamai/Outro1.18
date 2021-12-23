package com.lucca.mohard.entities.illagers.diamondPillager;

import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class DiamondArmouredPillagerRenderer extends IllagerRenderer<DiamondArmoredPillager> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("mohard", "textures/entity/illager/diamond_pillager.png");

    public DiamondArmouredPillagerRenderer(EntityRendererProvider.Context p_i50966_1_) {
        super(p_i50966_1_, new IllagerModel<>(p_i50966_1_.bakeLayer(ModModelLayers.ARMORED_PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
        this.model.getHat().visible = true;
    }


    @Override
    public ResourceLocation getTextureLocation(DiamondArmoredPillager p_110775_1_) {
        return this.TEXTURE;
    }

    @Override
    protected void scale(DiamondArmoredPillager p_114919_, PoseStack p_114920_, float p_114921_) {
        float f = 1.1850F;
        p_114920_.scale(f, f, f);
        super.scale(p_114919_, p_114920_, p_114921_);
    }
}
