package com.lucca.mohard.entities.bargainers.especific;

import com.lucca.mohard.entities.bargainers.BargainersEntity;
import com.lucca.mohard.entities.bargainers.BargainersType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class TUSJUS extends BargainersEntity {
    public TUSJUS(EntityType<BargainersEntity> bargainersEntityEntityType, Level world) {
        this(bargainersEntityEntityType, world, BargainersType.TUSJUS);
    }

    public TUSJUS(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_, BargainersType type) {
        super(p_i48575_1_, p_i48575_2_, type);
    }


}
