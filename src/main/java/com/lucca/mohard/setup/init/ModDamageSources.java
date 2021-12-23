package com.lucca.mohard.setup.init;

import com.lucca.mohard.mechanics.damage.DirectEntityDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class ModDamageSources {


    public static DamageSource playerMagicAttack(Entity damager, @Nullable Entity owner, DirectEntityDamageSource.Type type) {
        return (new DirectEntityDamageSource("directMagic", damager, owner, type)).setMagic();

    }
}
