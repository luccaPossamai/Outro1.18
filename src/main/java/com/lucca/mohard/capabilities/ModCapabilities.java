package com.lucca.mohard.capabilities;

import com.lucca.mohard.block.altar.capability.AltarCapability;
import com.lucca.mohard.capabilities.mana.ManaCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModCapabilities {

    public static final Capability<AltarCapability> ALTAR_CAPABILITY = CapabilityManager.get(new CapabilityToken<AltarCapability>() {});
    public static final Capability<ManaCapability> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<ManaCapability>() {});

    @SubscribeEvent
    public void registerCap(RegisterCapabilitiesEvent event){
        event.register(AltarCapability.class);
        event.register(ManaCapability.class);
    }
}
