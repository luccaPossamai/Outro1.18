package com.lucca.mohard.itens.essence;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@FunctionalInterface
@OnlyIn(Dist.CLIENT)
public interface EssencePredicate<T extends LivingEntity> {

    boolean test(LivingEntity entity);



}
