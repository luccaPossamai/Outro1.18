package com.lucca.mohard.entities.corrupter;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Witch;

public class CorrupterRenderer extends MobRenderer<Corrupter, CorrupterModel<Corrupter>> {

    public CorrupterRenderer(EntityRendererProvider.Context p_i50953_1_) {
        super(p_i50953_1_, new CorrupterModel<>(p_i50953_1_.bakeLayer(ModModelLayers.CORRUPTER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, p_i50953_1_.getModelSet()));

    }


    @Override
    public ResourceLocation getTextureLocation(Corrupter p_114482_) {
        String suffix = p_114482_.getCorrupterType().getSuffix();
        return new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/corrupter/corrupter_"+suffix+".png");
    }

}
