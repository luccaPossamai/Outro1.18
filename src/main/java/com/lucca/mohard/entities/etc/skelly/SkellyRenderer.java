package com.lucca.mohard.entities.etc.skelly;

import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SkellyRenderer extends SkeletonRenderer {
    private static final ResourceLocation SKELLY_SKELETON_LOCATION = new ResourceLocation("mohard:textures/entity/skeleton/skelly.png");

    public SkellyRenderer(EntityRendererProvider.Context p_174409_) {
        super(p_174409_, ModModelLayers.SKELLY, ModModelLayers.SKELLY_INNER_ARMOR, ModModelLayers.SKELLY_OUTER_ARMOR);
        this.addLayer(new SkellyClothingLayer<>(this, p_174409_.getModelSet()));
    }

    public ResourceLocation getTextureLocation(AbstractSkeleton p_116049_) {
        return SKELLY_SKELETON_LOCATION;
    }
}
