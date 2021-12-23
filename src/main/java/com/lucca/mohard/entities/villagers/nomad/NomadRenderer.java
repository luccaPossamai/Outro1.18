package com.lucca.mohard.entities.villagers.nomad;

import com.lucca.mohard.entities.models.NomadModel;
import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;

public class NomadRenderer extends MobRenderer<NomadEntity, NomadModel<NomadEntity>> {
    private static final ResourceLocation NOMAD_BASE_SKIN = new ResourceLocation("mohard", "textures/entity/nomad.png");


    public NomadRenderer(EntityRendererProvider.Context p_i50953_1_) {
        super(p_i50953_1_, new NomadModel<>(p_i50953_1_.bakeLayer(ModModelLayers.NOMAD)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, p_i50953_1_.getModelSet()));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    public ResourceLocation getTextureLocation(NomadEntity p_110775_1_) {
        return NOMAD_BASE_SKIN;
    }

    protected void scale(NomadEntity p_225620_1_, PoseStack p_225620_2_, float p_225620_3_) {
        float f = 0.9375F;
        p_225620_2_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
