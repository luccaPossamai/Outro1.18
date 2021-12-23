package com.lucca.mohard;

import com.lucca.mohard.biome.denseDesert.DenseDesertBiomeMaker;
import com.lucca.mohard.biome.denseTaiga.DenseTaigaBiomeMaker;
import com.lucca.mohard.block.altar.appearence.AltarTileEntityRenderer;
import com.lucca.mohard.entities.EntitiesRenderer;
import com.lucca.mohard.gui.altar.AltarScreen;
import com.lucca.mohard.gui.essenceExchanger.EssenceExchangerScreen;
import com.lucca.mohard.itens.artifacts.Dash;
import com.lucca.mohard.itens.artifacts.Scroll;
import com.lucca.mohard.itens.essence.essenceSyntonizer.EssenceSyntonizer;
import com.lucca.mohard.setup.*;
import com.lucca.mohard.setup.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeDictionary.Type.OVERWORLD;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod
{

    public static final String MOD_ID = "mohard";
    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {

        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);

        }

    private void setup(final FMLCommonSetupEvent event) {

        ModWoodTypes.register();
        DenseDesertBiomeMaker.setupFeatures();
        DenseTaigaBiomeMaker.setupFeatures();
        DenseDesertBiomeMaker.setupStructureFeatures();


        ItemProperties.register(ModItens.SCROLL_ARTIFACT.get(), new ResourceLocation(MOD_ID, "binded"), (p_239427_0_, p_239427_1_, p_239427_2_, p_239427_3_) ->{
            if(p_239427_0_.getItem() instanceof Scroll) return Scroll.getBind(p_239427_0_) ? 1F : 0F;
            return 0F;
        });

        ItemProperties.register(ModItens.ESSENCE_SYNTONIZER.get(), new ResourceLocation(MOD_ID, "tier"), (p_239427_0_, p_239427_1_, p_239427_2_, p_239427_3_) ->{
            if(p_239427_0_.getItem() instanceof EssenceSyntonizer) return EssenceSyntonizer.getItemTier(p_239427_0_) != null ? EssenceSyntonizer.getItemTier(p_239427_0_).getId() : 0;
            return 0F;
        });

        ItemProperties.register(ModItens.DASH_ARTIFACT.get(), new ResourceLocation(MOD_ID, "state"), (p_239427_0_, p_239427_1_, p_239427_2_, p_239427_3_) ->{
            if(p_239427_0_.getItem() instanceof Dash) return Dash.getState(p_239427_0_);
            return 0F;
        });



        MenuScreens.register(ModContainers.ALTAR_TYPE.get(), AltarScreen::new);
        MenuScreens.register(ModContainers.ESSENCE_EXCHANGER.get(), EssenceExchangerScreen::new);


        BlockEntityRenderers.register(ModTileEntityTypes.ALTAR.get(),
                AltarTileEntityRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DENSE_SPRUCE_LEAVES, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DENSE_CACTUS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CORRUPTED_BUSH, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DENSE_SPRUCE_SAPLING, RenderType.cutout());
        event.enqueueWork(()->
                setupBiome()
                );

    }



    private static void setupBiome(){
        ModBiomeKeys.register();

        SurfaceRules.ConditionSource surfacerules$conditionsource = SurfaceRules.isBiome(ModBiomeKeys.DENSE_DESERT);
        SurfaceRules.RuleSource HEAVY_SANDSTONE = SurfaceRules.state(ModBlocks.HEAVY_SANDSTONE.defaultBlockState());
        SurfaceRules.RuleSource DENSE_SAND = SurfaceRules.state(ModBlocks.DENSE_SAND.defaultBlockState());
        SurfaceRules.RuleSource surfacerules$rulesource1 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, HEAVY_SANDSTONE), DENSE_SAND);

        SurfaceRules.ifTrue(surfacerules$conditionsource, SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, true, CaveSurface.FLOOR), HEAVY_SANDSTONE));
        SurfaceRules.ifTrue(surfacerules$conditionsource, surfacerules$rulesource1);

        BiomeDictionary.addTypes(ModBiomeKeys.DENSE_DESERT, HOT, DRY, SANDY, OVERWORLD);
        BiomeDictionary.addTypes(ModBiomeKeys.TAIGA, COLD, CONIFEROUS, FOREST, OVERWORLD);
        BiomeDictionary.addTypes(ModBiomeKeys.TAIGA_SNOWY, COLD, CONIFEROUS, FOREST, SNOWY, OVERWORLD);
        BiomeDictionary.addTypes(Biomes.OLD_GROWTH_SPRUCE_TAIGA, DENSE, FOREST, RARE, OVERWORLD);
        BiomeDictionary.addTypes(Biomes.OLD_GROWTH_PINE_TAIGA, COLD, CONIFEROUS, FOREST, OVERWORLD);

        BiomeManager.addAdditionalOverworldBiomes(ModBiomeKeys.TAIGA);
        BiomeManager.addAdditionalOverworldBiomes(ModBiomeKeys.DENSE_DESERT);
        BiomeManager.addAdditionalOverworldBiomes(ModBiomeKeys.OLD_GROWTH_DENSE_PINE_TAIGA);
        BiomeManager.addAdditionalOverworldBiomes(ModBiomeKeys.OLD_GROWTH_DENSE_SPRUCE_TAIGA);
        BiomeManager.addAdditionalOverworldBiomes(ModBiomeKeys.TAIGA_SNOWY);
    }




    private void doClientStuff(final FMLClientSetupEvent event) {
        EntitiesRenderer.layersRegistry();
        EntitiesRenderer.registryEntityRenderers();

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

}
