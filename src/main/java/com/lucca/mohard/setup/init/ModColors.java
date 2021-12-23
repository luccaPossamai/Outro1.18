package com.lucca.mohard.setup.init;

import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.Registration;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModColors {

    @SubscribeEvent
    public static void setupItemColors(ColorHandlerEvent.Item event){
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();

        //MAGIC ARMOR
        itemColors.register((p_210239_0_, p_210239_1_) -> {
            return p_210239_1_ > 0 ? -1 : ((DyeableLeatherItem)p_210239_0_.getItem()).getColor(p_210239_0_);
        }, ModItens.MAGIC_CHESTPLATE.get(), ModItens.MAGIC_HELMET.get(), ModItens.MAGIC_BOOTS.get(), ModItens.MAGIC_LEGGINGS.get());

        //ESSENCES
        for(RegistryObject<Item> en: Registration.ITENS.getEntries()) {
            if (en.get() instanceof EssenceItem) {
                EssenceItem essence = (EssenceItem) en.get();
                itemColors.register((p_198141_1_, p_198141_2_) ->{
                    return essence.getColor(p_198141_2_);
                }, essence);
            }
        }

        itemColors.register((p_92687_, p_92688_) -> {
            BlockState blockstate = ((BlockItem)p_92687_.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, p_92688_);
        }, ModBlocks.DENSE_SPRUCE_LEAVES);

    }

    @SubscribeEvent
    public static void setupBlockColors(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();
        blockColors.register((p_92626_, p_92627_, p_92628_, p_92629_) -> {
            return p_92627_ != null && p_92628_ != null ? BiomeColors.getAverageFoliageColor(p_92627_, p_92628_) : FoliageColor.getDefaultColor();
        }, ModBlocks.DENSE_SPRUCE_LEAVES);

    }
}
