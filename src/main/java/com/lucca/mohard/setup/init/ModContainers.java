package com.lucca.mohard.setup.init;

import com.lucca.mohard.block.altar.GUI.ModAltarContainer;
import com.lucca.mohard.block.essenceExchanger.EssenceExchangerContainer;
import com.lucca.mohard.gui.altar.AltarGUI;
import com.lucca.mohard.gui.essenceExchanger.EssenceExchangerGUI;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static void register(){}


    public static final RegistryObject<MenuType<ModAltarContainer>> ALTAR_TYPE =
            Registration.CONTAINERS.register("altar_gui", () ->
                    new AltarGUI(ModAltarContainer::new));

    public static final RegistryObject<MenuType<EssenceExchangerContainer>> ESSENCE_EXCHANGER =
            Registration.CONTAINERS.register("essence_exchanger", () ->
                    new EssenceExchangerGUI(EssenceExchangerContainer::new));



}
