package com.lucca.mohard.block.altar.capability;


import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.capabilities.ModCapabilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AltarCapabilitySetup {

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof  Player) {
            Player jogador = (Player) event.getObject();
            event.addCapability(new ResourceLocation(ExampleMod.MOD_ID, "altar_inventory"), new AltarCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void resetStats(PlayerEvent.Clone event) {
        Player jogadorOriginal = event.getOriginal();
        Player jogador = event.getPlayer();
        jogadorOriginal.reviveCaps();
        jogadorOriginal.getCapability(ModCapabilities.ALTAR_CAPABILITY).ifPresent(inv -> {
            jogador.getCapability(ModCapabilities.ALTAR_CAPABILITY).ifPresent(inv2 -> {
                for (int i = 0; i < 9; i++) {
                    inv2.setStackInSlot(i, inv.getStackInSlot(i));
                }
            });
        });
        event.getOriginal().invalidateCaps();

    }

}
