package com.lucca.mohard.entities.etc.zomby;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ZombyRenderer extends AbstractZombieRenderer<Zomby, DrownedModel<Zomby>> {

    private static final ResourceLocation ZOMBIE_LOCATION = new ResourceLocation("mohard:textures/entity/zomby/zomby.png");


    public ZombyRenderer(EntityRendererProvider.Context p_173964_) {
        super(p_173964_, new DrownedModel<>(p_173964_.bakeLayer(ModelLayers.DROWNED)), new DrownedModel<>(p_173964_.bakeLayer(ModelLayers.DROWNED_INNER_ARMOR)), new DrownedModel<>(p_173964_.bakeLayer(ModelLayers.DROWNED_OUTER_ARMOR)));
        this.addLayer(new ZombyOuterLayer(this, p_173964_.getModelSet()));
    }

    @Override
    protected void scale(Zomby p_114907_, PoseStack p_114908_, float p_114909_) {
        float f = 0.850F;
        p_114908_.scale(f, f, f);
        super.scale(p_114907_, p_114908_, p_114909_);
    }

    @Override
    public ResourceLocation getTextureLocation(Zomby p_114891_) {
        return ZOMBIE_LOCATION;
    }
}
