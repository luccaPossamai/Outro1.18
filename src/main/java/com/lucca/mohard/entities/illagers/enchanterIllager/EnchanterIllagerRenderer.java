package com.lucca.mohard.entities.illagers.enchanterIllager;

import com.lucca.mohard.entities.models.EnchanterModel;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

public class EnchanterIllagerRenderer extends MobRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("mohard", "textures/entity/illager/enchanter.png");

    public EnchanterIllagerRenderer(EntityRendererProvider.Context p_i50953_1_) {
        super(p_i50953_1_, new EnchanterModel<EnchanterIllager>(p_i50953_1_.bakeLayer(ModModelLayers.ENCHANTER)), 0.5F);


    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_110775_1_) {
        return TEXTURE;
    }


}
