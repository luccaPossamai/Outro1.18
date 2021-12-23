package com.lucca.mohard.data.client;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ExampleMod.MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels() {

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandHeld = getExistingFile(mcLoc("item/handheld"));
        ModelFile fenceInventory = getExistingFile(mcLoc("block/fence_inventory"));
        ModelFile itemSpawnEgg = getExistingFile(mcLoc("item/template_spawn_egg"));

        withExistingParent("altar", new ResourceLocation(ExampleMod.MOD_ID, "block/altar"));
        withExistingParent("bloody_sand", new ResourceLocation(ExampleMod.MOD_ID, "block/bloody_sand"));
        withExistingParent("dense_sand", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_sand"));
        withExistingParent("heavy_sandstone", new ResourceLocation(ExampleMod.MOD_ID, "block/heavy_sandstone"));
        withExistingParent("cut_heavy_sandstone", new ResourceLocation(ExampleMod.MOD_ID, "block/cut_heavy_sandstone"));
        withExistingParent("chiseled_heavy_sandstone", new ResourceLocation(ExampleMod.MOD_ID, "block/chiseled_heavy_sandstone"));
        withExistingParent("smooth_heavy_sandstone", new ResourceLocation(ExampleMod.MOD_ID, "block/smooth_heavy_sandstone"));
        withExistingParent("heavy_sandstone_slab", new ResourceLocation(ExampleMod.MOD_ID, "block/heavy_sandstone_slab"));
        withExistingParent("cut_heavy_sandstone_slab", new ResourceLocation(ExampleMod.MOD_ID, "block/cut_heavy_sandstone_slab"));
        withExistingParent("smooth_heavy_sandstone_slab", new ResourceLocation(ExampleMod.MOD_ID, "block/smooth_heavy_sandstone_slab"));
        withExistingParent("heavy_sandstone_stairs", new ResourceLocation(ExampleMod.MOD_ID, "block/heavy_sandstone_stairs"));
        withExistingParent("smooth_heavy_sandstone_stairs", new ResourceLocation(ExampleMod.MOD_ID, "block/smooth_heavy_sandstone_stairs"));
        wallInventory("heavy_sandstone_wall", new ResourceLocation(ExampleMod.MOD_ID, "block/heavy_sandstone"));
        withExistingParent("dense_cactus", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_cactus"));
        withExistingParent("essence_exchanger", new ResourceLocation(ExampleMod.MOD_ID, "block/essence_exchanger_0"));
        withExistingParent("coppered_deepslate", new ResourceLocation(ExampleMod.MOD_ID, "block/coppered_deepslate"));
        withExistingParent("dense_spruce_leaves", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_leaves"));
        withExistingParent("dense_spruce_log", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_log"));
        withExistingParent("dense_spruce_planks", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_planks"));
        withExistingParent("dense_spruce_stairs", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_stairs"));
        withExistingParent("dense_spruce_fence", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_fence_post"));
        withExistingParent("dense_spruce_fence_gate", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_fence_gate"));
        withExistingParent("dense_spruce_slab", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_slab"));
        withExistingParent("dense_spruce_trapdoor", new ResourceLocation(ExampleMod.MOD_ID, "block/dense_spruce_trapdoor_bottom"));
        getBuilder("dense_spruce_door").texture("layer0", "item/dense_spruce_door").parent(itemGenerated);

        ModelFile scrollModelClosed = getBuilder("scroll_artifact_closed").parent(itemGenerated).texture("layer0","item/scroll_artifact_closed");
        getBuilder("scroll_artifact").texture("layer0", "item/scroll_artifact_opened").parent(itemGenerated).override().predicate(new ResourceLocation(ExampleMod.MOD_ID, "binded"), 1F).model(scrollModelClosed);

        ModelFile dashArtifact1 = getBuilder("dash_artifact_1").parent(itemGenerated).texture("layer0","item/dash_artifact_1");
        ModelFile dashArtifact2 = getBuilder("dash_artifact_2").parent(itemGenerated).texture("layer0","item/dash_artifact_2");
        getBuilder("dash_artifact").texture("layer0", "item/dash_artifact_0").parent(itemGenerated)
                .override().predicate(new ResourceLocation(ExampleMod.MOD_ID, "state"), 1F).model(dashArtifact1).end()
                .override().predicate(new ResourceLocation(ExampleMod.MOD_ID, "state"), 2F).model(dashArtifact2).end()
        ;


        ModelFile syntonizeWooden = getBuilder("wooden_syntonizer").parent(itemHandHeld).texture("layer0","item/wooden_syntonizer");
        ModelFile syntonizeStone = getBuilder("stone_syntonizer").parent(itemHandHeld).texture("layer0","item/stone_syntonizer");
        ModelFile syntonizeCopper = getBuilder("copper_syntonizer").parent(itemHandHeld).texture("layer0","item/copper_syntonizer");
        ModelFile syntonizeIron = getBuilder("iron_syntonizer").parent(itemHandHeld).texture("layer0","item/iron_syntonizer");
        ModelFile syntonizeLapis = getBuilder("lapis_syntonizer").parent(itemHandHeld).texture("layer0","item/lapis_syntonizer");
        ModelFile syntonizeRedstone = getBuilder("redstone_syntonizer").parent(itemHandHeld).texture("layer0","item/redstone_syntonizer");
        ModelFile syntonizeGold = getBuilder("gold_syntonizer").parent(itemHandHeld).texture("layer0","item/gold_syntonizer");
        ModelFile syntonizeDiamond = getBuilder("diamond_syntonizer").parent(itemHandHeld).texture("layer0","item/diamond_syntonizer");
        ModelFile syntonizeNetherite = getBuilder("netherite_syntonizer").parent(itemHandHeld).texture("layer0","item/netherite_syntonizer");
        ModelFile syntonizeVilio = getBuilder("vilio_syntonizer").parent(itemHandHeld).texture("layer0","item/vilio_syntonizer");
        getBuilder("essence_syntonizer").texture("layer0", "item/basic_syntonizer").parent(itemHandHeld).override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 1F).model(syntonizeWooden).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 2F).model(syntonizeStone).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 3F).model(syntonizeCopper).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 4F).model(syntonizeIron).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 5F).model(syntonizeLapis).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 6F).model(syntonizeRedstone).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 7F).model(syntonizeGold).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 8F).model(syntonizeDiamond).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 9F).model(syntonizeNetherite).end().override()
                .predicate(new ResourceLocation(ExampleMod.MOD_ID, "tier"), 10F).model(syntonizeVilio).end()
                ;


        builder(itemGenerated, "true_void_remnants", "true_void_remnants");
        builder(itemGenerated, "enchanted_gold_ingot", "enchanted_gold_ingot");
        builder(itemGenerated, "corrupted_berry", "corrupted_berry");
        getBuilder("dense_spruce_sapling").texture("layer0", "block/dense_spruce_sapling").parent(itemGenerated);

        builder(itemGenerated, "music_disc_breathe", "music_disc_breathe");

        builder(itemGenerated, "empty_essence", "empty_essence");

        builder(itemGenerated, "camo_boots", "camo_boots");
        builder(itemGenerated, "camo_leggings", "camo_leggings");
        builder(itemGenerated, "camo_chestplate", "camo_chestplate");
        builder(itemGenerated, "camo_helmet", "camo_helmet");

        builder(itemGenerated, "demon_boots", "demon_boots");
        builder(itemGenerated, "demon_leggings", "demon_leggings");
        builder(itemGenerated, "demon_chestplate", "demon_chestplate");
        builder(itemGenerated, "demon_helmet", "demon_helmet");

        builder(itemGenerated, "pure_boots", "pure_boots");
        builder(itemGenerated, "pure_leggings", "pure_leggings");
        builder(itemGenerated, "pure_chestplate", "pure_chestplate");
        builder(itemGenerated, "pure_helmet", "pure_helmet");

        builder(itemGenerated, "biohazard_boots", "biohazard_boots");
        builder(itemGenerated, "biohazard_leggings", "biohazard_leggings");
        builder(itemGenerated, "biohazard_chestplate", "biohazard_chestplate");
        builder(itemGenerated, "biohazard_helmet", "biohazard_helmet");

        builder2(itemGenerated, "magic_boots", "magic_boots", "magic_boots_overlay");
        builder2(itemGenerated, "magic_leggings", "magic_leggings", "magic_leggings_overlay");
        builder2(itemGenerated, "magic_chestplate", "magic_chestplate", "magic_chestplate_overlay");
        builder2(itemGenerated, "magic_helmet", "magic_helmet", "magic_helmet_overlay");

        builder(itemHandHeld,"vilio_axe", "vilio_axe");
        builder(itemHandHeld,"vilio_pickaxe", "vilio_pickaxe");
        builder(itemHandHeld,"vilio_hoe", "vilio_hoe");
        builder(itemHandHeld,"vilio_shovel", "vilio_shovel");

        builder(itemHandHeld,"dagger_artifact", "dagger_artifact");
        builder(itemHandHeld,"algid_axe_artifact", "algid_axe_artifact");
        builder(itemGenerated,"book_artifact", "book_artifact");
        builder(itemGenerated, "clock_breaker_artifact", "clock_breaker_artifact");

        builder(itemGenerated, "chimarrao", "chimarrao");

        builder2(itemSpawnEgg, "essence", "essence_1", "essence_2");


        //essence texure

        for(RegistryObject<Item> en: Registration.ITENS.getEntries()) {
            if (en.get() instanceof EssenceItem) {
                EssenceItem essence = (EssenceItem) en.get();
                ResourceLocation nome = essence.getRegistryName();
                withExistingParent(nome.toString(), modLoc("item/essence"));
            }
        }



    }




    private ItemModelBuilder builder(ModelFile itemGenerated, String nome, String texture) {
        return getBuilder(nome).parent(itemGenerated).texture("layer0", "item/"+texture);

    }
    private ItemModelBuilder builder2(ModelFile itemGenerated, String nome, String texture1, String texture2) {
        return getBuilder(nome).parent(itemGenerated).texture("layer0", "item/"+texture1).texture("layer1", "item/"+texture2);
    }

}
