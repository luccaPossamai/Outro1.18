package com.lucca.mohard.entities.corrupter;

import net.minecraft.world.item.Item;

import java.util.Map;

public interface CorruptedEntity {

    Map<Item, Double> getDrops();

    int getEssenceXp();

}
