package com.lucca.mohard.evolution;

import com.lucca.mohard.help.Methods;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GameLevel {

    public static int SERVER_LEVEL = 0;

    public static double getLevel(double x,
                                  double z,
                                  Level world){

        if(x < 0){
            x = x * (-1);
        }
        if(z < 0){
            z = z * (-1);
        }

        double deltaSpawn = world.getLevelData().getXSpawn();
        double fator = x;
        if(x < z){
            fator = z;
            deltaSpawn = world.getLevelData().getZSpawn();
        }
        if(deltaSpawn < 0){
            deltaSpawn = deltaSpawn * (-1);
        }
        fator = fator - deltaSpawn;
        if(fator < 0){
            fator = fator * (-1);
        }

        double contagem = Math.ceil(fator / 50);

        //contagem = contagem + (contagem * (contagem - 1) + contagem * (contagem - 1));

        if(contagem > 4000){
            contagem = 4000;
        }
        return contagem + SERVER_LEVEL;
    }

    private static int getServerLevel(Level level){
        int serverLevel = 0;
        for(Player players : level.players()) {
            serverLevel = serverLevel + (int) getPlayerLevel(players);
        }
        return serverLevel;
    }
    static double getPlayerLevel(Player player){
        return (Methods.getAttributes().stream().map(player::getAttributeValue).mapToDouble(Double::doubleValue).sum())/8;
    }

    static void updateserverLevel(Level level){
        SERVER_LEVEL = getServerLevel(level);
        System.out.println("[UPDATING SERVER LEVEL] CurrentLevel is "+SERVER_LEVEL);
    }

}
