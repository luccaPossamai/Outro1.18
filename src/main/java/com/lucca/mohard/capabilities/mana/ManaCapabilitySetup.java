package com.lucca.mohard.capabilities.mana;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.capabilities.ModCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ManaCapabilitySetup {

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(ExampleMod.MOD_ID, "mana"), new ManaCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event){
        if (Minecraft.getInstance().level != null) {
            for (Player p : Minecraft.getInstance().level.players()) {
                p.getCapability(ModCapabilities.MANA_CAPABILITY, null).ifPresent(mana -> {
                    if (mana.canRegenerate()) {
                        mana.regenMana(1);
                    }
                });
            }
        }
    }
}
