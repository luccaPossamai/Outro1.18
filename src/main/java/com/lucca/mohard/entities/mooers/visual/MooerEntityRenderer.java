package com.lucca.mohard.entities.mooers.visual;

import com.google.common.collect.ImmutableMap;
import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MooerEntityRenderer extends HumanoidMobRenderer<Mob, MooerModel<Mob>> {

    private static final Map<EntityType<?>, ResourceLocation> resourceLocations = ImmutableMap.of(ModEntityTypes.MOOER_ENTITY_TYPE, new ResourceLocation("mohard", "textures/entity/mooers/mooer_default.png"), ModEntityTypes.GOLDEN_MOOER_ENTITY_TYPE, new ResourceLocation("mohard", "textures/entity/mooers/mooer_golden.png"), ModEntityTypes.MOOSHROOM_MOOER_ENTITY_TYPE, new ResourceLocation("mohard", "textures/entity/mooers/mooer_mooshroom.png"));


    public MooerEntityRenderer(EntityRendererProvider.Context p_i46168_1_, ModelLayerLocation layer1) {
        super(p_i46168_1_, createModel(p_i46168_1_, layer1), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel(p_i46168_1_.bakeLayer(ModelLayers.PIGLIN_INNER_ARMOR)), new HumanoidModel(p_i46168_1_.bakeLayer(ModelLayers.PIGLIN_OUTER_ARMOR))));
        this.addLayer(new ItemInHandLayer<>(this));
        this.addLayer(new ElytraLayer<>(this, p_i46168_1_.getModelSet()));
        this.addLayer(new CustomHeadLayer<>(this, p_i46168_1_.getModelSet()));
    }

    private static MooerModel<Mob> createModel(EntityRendererProvider.Context context, ModelLayerLocation p_174345_) {
        MooerModel<Mob> mooerModel = new MooerModel(context.bakeLayer(p_174345_));

        return mooerModel;
    }

    public ResourceLocation getTextureLocation(Mob p_110775_1_) {
        ResourceLocation resourcelocation = resourceLocations.get(p_110775_1_.getType());
        if (resourcelocation == null) {
            throw new IllegalArgumentException("I don't know what texture to use for " + p_110775_1_.getType());
        } else {
            return resourcelocation;
        }
    }
}
