package com.lucca.mohard.entities;

import com.lucca.mohard.entities.bargainers.BargainersRenderer;
import com.lucca.mohard.entities.bargainers.BargainersType;
import com.lucca.mohard.entities.corrupter.CorrupterModel;
import com.lucca.mohard.entities.corrupter.CorrupterRenderer;
import com.lucca.mohard.entities.dummy.DummyModel;
import com.lucca.mohard.entities.dummy.DummyRenderer;
import com.lucca.mohard.entities.etc.corruptedPlayer.CorruptedPlayerRenderer;
import com.lucca.mohard.entities.etc.creepy.CreepyModel;
import com.lucca.mohard.entities.etc.creepy.CreepyRenderer;
import com.lucca.mohard.entities.etc.skelly.SkellyRenderer;
import com.lucca.mohard.entities.etc.spidey.SpideyRenderer;
import com.lucca.mohard.entities.etc.thox.ThoxModel;
import com.lucca.mohard.entities.etc.thox.ThoxRenderer;
import com.lucca.mohard.entities.etc.zomby.ZombyRenderer;
import com.lucca.mohard.entities.illagers.diamondPillager.DiamondArmouredPillagerRenderer;
import com.lucca.mohard.entities.illagers.diamondVindicator.DiamondArmouredVindicatorRenderer;
import com.lucca.mohard.entities.illagers.enchanterIllager.EnchanterIllagerRenderer;
import com.lucca.mohard.entities.illagers.goldenPillager.GoldenArmouredPillagerRenderer;
import com.lucca.mohard.entities.illagers.goldenVindicator.GoldenArmouredVindicatorRenderer;
import com.lucca.mohard.entities.illagers.iceIsolator.IceIsolatorRenderer;
import com.lucca.mohard.entities.models.*;
import com.lucca.mohard.entities.mooers.visual.MooerEntityRenderer;
import com.lucca.mohard.entities.mooers.visual.MooerModel;
import com.lucca.mohard.entities.villagers.nomad.NomadRenderer;
import com.lucca.mohard.itens.artifacts.algidAxe.AlgidAxeRenderer;
import com.lucca.mohard.setup.init.ModEntities;
import com.lucca.mohard.setup.init.ModModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.ForgeHooksClient;


import static net.minecraft.client.model.geom.LayerDefinitions.INNER_ARMOR_DEFORMATION;
import static net.minecraft.client.model.geom.LayerDefinitions.OUTER_ARMOR_DEFORMATION;

public class EntitiesRenderer {

    public static void registryEntityRenderers(){


        EntityRenderDispatcher manager = Minecraft.getInstance().getEntityRenderDispatcher();
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        EntityRenderers.register(ModEntities.DIAMOND_ARMORED_PILLAGER.get(), DiamondArmouredPillagerRenderer::new);

        EntityRenderers.register(ModEntities.GOLDEN_ARMORED_PILLAGER.get(), GoldenArmouredPillagerRenderer::new);

        EntityRenderers.register(ModEntities.DIAMOND_ARMORED_VINDICATOR.get(), DiamondArmouredVindicatorRenderer::new);

        EntityRenderers.register(ModEntities.GOLDEN_ARMORED_VINDICATOR.get(), GoldenArmouredVindicatorRenderer::new);

        EntityRenderers.register(ModEntities.ENCHANTER.get(), EnchanterIllagerRenderer::new);

        EntityRenderers.register(ModEntities.ICE_ISOLATOR.get(), IceIsolatorRenderer::new);


        EntityRenderers.register(ModEntities.MOOER.get(), (context) -> {
            return new MooerEntityRenderer(context, ModModelLayers.MOOER);
        });

        EntityRenderers.register(ModEntities.GOLDEN_MOOER.get(), (context) -> {
            return new MooerEntityRenderer(context, ModModelLayers.MOOER);
        });

        EntityRenderers.register(ModEntities.MOOSHROOM_MOOER.get(), (context) -> {
            return new MooerEntityRenderer(context, ModModelLayers.MOOER);
        });


        EntityRenderers.register(ModEntities.CORRUPTED_PLAYER.get(), (context) -> {
            return new CorruptedPlayerRenderer(context, false);
        });


        EntityRenderers.register(ModEntities.NOMAD.get(), NomadRenderer::new);

        EntityRenderers.register(ModEntities.CREEPY.get(), CreepyRenderer::new);

        EntityRenderers.register(ModEntities.SKELLY.get(), SkellyRenderer::new);

        EntityRenderers.register(ModEntities.ZOMBY.get(), ZombyRenderer::new);

        EntityRenderers.register(ModEntities.SPIDEY.get(), SpideyRenderer::new);


        EntityRenderers.register(ModEntities.CORRUPTER.get(), CorrupterRenderer::new);

        EntityRenderers.register(ModEntities.THOX.get(), ThoxRenderer::new);

        EntityRenderers.register(ModEntities.THROWN_ALGID_AXE.get(), AlgidAxeRenderer::new);

        EntityRenderers.register(ModEntities.GASPI_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.GASPI, true);
        });
        EntityRenderers.register(ModEntities.CUSPILE_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.CUSPILE, true);
        });
        EntityRenderers.register(ModEntities.TUSJUS_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.TUSJUS, true);
        });
        EntityRenderers.register(ModEntities.OEL_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.OEL, false);
        });
        EntityRenderers.register(ModEntities.NIIPPA_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.NIIPPA, true);
        });
        EntityRenderers.register(ModEntities.SAATPON_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.SAATPON, true);
        });
        EntityRenderers.register(ModEntities.PABITTAS_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.PABITTAS, true);
        });
        EntityRenderers.register(ModEntities.NOJAS_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.NOJAS, true);
        });
        EntityRenderers.register(ModEntities.AAN_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.AAN, true);
        });
        EntityRenderers.register(ModEntities.HOCINZAL_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.HO_CINSAL, true);
        });
        EntityRenderers.register(ModEntities.CINVET_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.CINVET, true);
        });
        EntityRenderers.register(ModEntities.TASBEGO_ENTITY_TYPE.get(), (context) -> {
            return new BargainersRenderer(context, BargainersType.TASBEGO, true);
        });

        EntityRenderers.register(ModEntities.DUMMY.get(), (context) -> {
            return new DummyRenderer<>(context);
        });

    }

    public static void layersRegistry(){
        ForgeHooksClient.registerLayerDefinition(ModModelLayers.ARMORED_PILLAGER, () ->{
            return IllagerModel.createBodyLayer();
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.ARMORED_VINDICATOR, () -> {
            return ArmouredVindicatorModel.createBodyLayer();
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.ENCHANTER, () -> {
            return EnchanterModel.createBodyLayer();
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.MOOER, () -> {
            return LayerDefinition.create(MooerModel.createMesh(CubeDeformation.NONE), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.NOMAD, () ->{
            return LayerDefinition.create(NomadModel.createBodyModel(), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.BARGAINERS_NORMAL, () -> {
            return LayerDefinition.create(BargainersModel.createBodyModel(0), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.BARGAINERS_SMALL, () -> {
            return LayerDefinition.create(BargainersModel.createBodyModel(-1), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.BARGAINERS_TALL, () -> {
            return LayerDefinition.create(BargainersModel.createBodyModel(1), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.BARGAINERS_VERY_SMALL, () -> {
            return LayerDefinition.create(BargainersModel.createBodyModel(-2), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.BARGAINERS_VERY_TALL, () -> {
            return LayerDefinition.create(BargainersModel.createBodyModel(2), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.CORRUPTER, () -> {
            return LayerDefinition.create(CorrupterModel.createBodyModel(), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.THOX, () -> {
            return LayerDefinition.create(ThoxModel.createBodyModel(), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.DUMMY, () -> {
            return LayerDefinition.create(DummyModel.createMesh(CubeDeformation.NONE), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.CREEPY, () -> {
            return CreepyModel.createBodyLayer(CubeDeformation.NONE);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.CREEPY_ARMOR, () -> {
            return CreepyModel.createBodyLayer(new CubeDeformation(0.2F));
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.CORRUPTED_PLAYER_LAYER, () -> {
            return LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(0.001F), false), 64, 64);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.SKELLY, () -> {
            return SkeletonModel.createBodyLayer();
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.SKELLY_OUTER_ARMOR, () -> {
            return  LayerDefinition.create(HumanoidModel.createMesh(OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.SKELLY_INNER_ARMOR, () -> {
            return LayerDefinition.create(HumanoidModel.createMesh(INNER_ARMOR_DEFORMATION, 0.0F), 64, 32);
        });

        ForgeHooksClient.registerLayerDefinition(ModModelLayers.SKELLY_OUTER_LAYER, () -> {
            return LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F), 64, 32);
        });



    }


}
