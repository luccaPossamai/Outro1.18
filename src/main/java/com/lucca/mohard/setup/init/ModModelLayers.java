package com.lucca.mohard.setup.init;

import com.lucca.mohard.ExampleMod;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;


public class ModModelLayers {

    public static void register(){}

    public static final ModelLayerLocation MOOER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "mooer"), "main");

    public static final ModelLayerLocation NOMAD = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "nomad"), "main");

    public static final ModelLayerLocation ENCHANTER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "enchater"), "main");

    public static final ModelLayerLocation ARMORED_PILLAGER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "armored_pillager"), "main");

    public static final ModelLayerLocation ARMORED_VINDICATOR = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "armored_vindicator"), "main");

    public static final ModelLayerLocation BARGAINERS_SMALL = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "bargainers_small"), "main");

    public static final ModelLayerLocation BARGAINERS_NORMAL = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "bargainers_normal"), "main");

    public static final ModelLayerLocation BARGAINERS_TALL = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "bargainers_tall"), "main");

    public static final ModelLayerLocation BARGAINERS_VERY_SMALL = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "bargainers_very_small"), "main");

    public static final ModelLayerLocation BARGAINERS_VERY_TALL = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "bargainers_very_tall"), "main");

    public static final ModelLayerLocation CORRUPTER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "corrupter"), "main");

    public static final ModelLayerLocation THOX = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "thox"), "main");

    public static final ModelLayerLocation DUMMY = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "dummy"), "main");

    public static final ModelLayerLocation CREEPY = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "creepy"), "main");

    public static final ModelLayerLocation CREEPY_ARMOR = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "creepy"), "armor");

    public static final ModelLayerLocation CORRUPTED_PLAYER_LAYER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "corrupted_player"), "armor");

    public static final ModelLayerLocation SKELLY = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "skelly"), "main");

    public static final ModelLayerLocation SKELLY_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "skelly"), "inner_armor");

    public static final ModelLayerLocation SKELLY_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "skelly"), "outer_armor");

    public static final ModelLayerLocation SKELLY_OUTER_LAYER = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "skelly"), "outer");

    public static final LayerDefinition PLAYER_FIT_OUTER_ARMOR = LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.3F), 0.0F), 64, 32);

    public static final LayerDefinition PLAYER_FIT_INNER_ARMOR = LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.2F), 0.0F), 64, 32);



}
