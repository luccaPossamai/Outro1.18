package com.lucca.mohard.mechanics.adventure;

import com.lucca.mohard.evolution.GameLevel;
import com.lucca.mohard.gui.screen.RenderLevelArea;
import com.lucca.mohard.help.Methods;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class PlayerWalk {

    private static Map<Player, Integer> lastLevel = new HashMap<>();
    public static Map<Player, RenderLevelArea> shouldShowLevel = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerWalkEvent(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving() instanceof Player) {
            Player player = (Player) event.getEntityLiving();
            int actualLevelInt = (int) GameLevel.getLevel(player.getX(), player.getZ(), player.getLevel());
            if (!lastLevel.containsKey(player)) {
                updatePlayerLevel(player, actualLevelInt);
            }
            int lastLevelInt = lastLevel.get(player);
            if(lastLevelInt != actualLevelInt){
                updatePlayerLevel(player, actualLevelInt);
                shouldShowLevel.put(player, null);
                player.sendMessage(Methods.stringToText("You're in the level "+actualLevelInt+" zone"), player.getUUID());
            }
        }
    }

    public static void updatePlayerLevel(Player player, int level) {
        if (lastLevel.containsKey(player)) {
            lastLevel.remove(player);
        }
        lastLevel.put(player, level);
    }
}
