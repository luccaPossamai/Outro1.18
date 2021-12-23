package com.lucca.mohard.entities.bargainers;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.entities.models.BargainersModel;
import com.lucca.mohard.setup.init.ModEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BargainersRenderer extends MobRenderer<BargainersEntity, BargainersModel<BargainersEntity>> {

    private static final Map<EntityType<?>, ResourceLocation> resourceLocations = resources();

    public BargainersRenderer(EntityRendererProvider.Context p_i50953_1_, BargainersType type, boolean smallNose) {
        super(p_i50953_1_, new BargainersModel<>(p_i50953_1_.bakeLayer(type.layerDefinition), smallNose), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, p_i50953_1_.getModelSet()));
        this.addLayer(new ItemInHandLayer<>(this) {
            public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, BargainersEntity p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
            }
        });
    }


    @Override
    public ResourceLocation getTextureLocation(BargainersEntity p_110775_1_) {
        ResourceLocation resourcelocation = resourceLocations.get(p_110775_1_.getType());
        if (resourcelocation == null) {
            throw new IllegalArgumentException("I don't know what texture to use for " + p_110775_1_.getType());
        } else {
            return resourcelocation;
        }
    }

    private static Map<EntityType<?>, ResourceLocation> resources(){
        Map<EntityType<?>, ResourceLocation> rLocsMap = new HashMap<>();
        List<ResourceLocation> rLocs = new ArrayList<>();
        for(BargainersType type : BargainersType.values()){
            rLocs.add(new ResourceLocation("mohard", "textures/entity/bargainers/"+type.name+"_bargainer.png"));
        }

        rLocsMap.put(ModEntities.GASPI_ENTITY_TYPE.get(), rLocs.get(0));
        rLocsMap.put(ModEntities.CUSPILE_ENTITY_TYPE.get(), rLocs.get(1));
        rLocsMap.put(ModEntities.TUSJUS_ENTITY_TYPE.get(), rLocs.get(2));
        rLocsMap.put(ModEntities.OEL_ENTITY_TYPE.get(), rLocs.get(3));
        rLocsMap.put(ModEntities.NIIPPA_ENTITY_TYPE.get(), rLocs.get(4));
        rLocsMap.put(ModEntities.SAATPON_ENTITY_TYPE.get(), rLocs.get(5));
        rLocsMap.put(ModEntities.PABITTAS_ENTITY_TYPE.get(), rLocs.get(6));
        rLocsMap.put(ModEntities.NOJAS_ENTITY_TYPE.get(), rLocs.get(7));
        rLocsMap.put(ModEntities.AAN_ENTITY_TYPE.get(), rLocs.get(8));
        rLocsMap.put(ModEntities.HOCINZAL_ENTITY_TYPE.get(), rLocs.get(9));
        rLocsMap.put(ModEntities.CINVET_ENTITY_TYPE.get(), rLocs.get(10));
        rLocsMap.put(ModEntities.TASBEGO_ENTITY_TYPE.get(), rLocs.get(11));

        return rLocsMap;
    }

    @Override
    protected void scale(BargainersEntity p_225620_1_, PoseStack p_225620_2_, float p_225620_3_) {
        float f = 0.9375F;
        p_225620_2_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
