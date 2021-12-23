package com.lucca.mohard.effects.corruption;

import com.lucca.mohard.entities.etc.corruptedPlayer.CorruptedPlayer;
import com.lucca.mohard.entities.etc.creepy.Creepy;
import com.lucca.mohard.entities.etc.skelly.Skelly;
import com.lucca.mohard.entities.etc.spidey.Spidey;
import com.lucca.mohard.entities.etc.zomby.Zomby;
import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

public class CorruptionMechanics {


    private static final EntityType CONVERTIBLE_ENTITIES[] = {
            EntityType.SKELETON,
            EntityType.WITHER_SKELETON,
            EntityType.STRAY,
            EntityType.HUSK,
            EntityType.ZOMBIE,
            EntityType.CREEPER,
            EntityType.ZOMBIFIED_PIGLIN,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.DROWNED,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER
    };


    public static boolean convertible(EntityType type){
        return Arrays.stream(CONVERTIBLE_ENTITIES).anyMatch( ent ->
            ent.equals(type)
        );
    }

    public static boolean converted(LivingEntity entity){
        if(entity instanceof Creepy ||
                entity instanceof Zomby ||
                entity instanceof Skelly ||
                entity instanceof Spidey){
            return true;
        } else {
            return false;
        }
    }

    public static EntityType getCorruptedVariant(EntityType type){
        if(type.equals(EntityType.CREEPER)){
            return ModEntityTypes.CREEPY_ENTITY_TYPE;

        } else if(type.equals(EntityType.ZOMBIE)){
            return ModEntityTypes.ZOMBY_ENTITY_TYPE;

        } else if(type.equals(EntityType.SKELETON)){
            return ModEntityTypes.SKELLY_ENTITY_TYPE;

        } else {
            return ModEntityTypes.SPIDEY_ENTITY_TYPE;
        }
    }

    public static Entity generateCorruptedPlayer(Player player){
        CorruptedPlayer corruptedPlayer = ModEntityTypes.CORRUPTED_PLAYER_ENTITY_TYPE.create(player.level);
        corruptedPlayer.setPlayer(player.getUUID());
        corruptedPlayer.setCustomNameVisible(true);
        corruptedPlayer.setCustomName(player.getName());
        for(EquipmentSlot slot : EquipmentSlot.values()){
            ItemStack itemStack = player.getItemBySlot(slot).copy();
            player.getItemBySlot(slot).shrink(1);
            corruptedPlayer.setItemSlot(slot, itemStack);
        }
        corruptedPlayer.teleportTo(player.getX(), player.getY(), player.getZ());
        return corruptedPlayer;
    }

    public static EntityType[] getConvertibleEntities() {
        return CONVERTIBLE_ENTITIES;
    }
}
