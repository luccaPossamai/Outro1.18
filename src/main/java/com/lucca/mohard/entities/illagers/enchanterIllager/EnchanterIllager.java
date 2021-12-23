package com.lucca.mohard.entities.illagers.enchanterIllager;

import com.lucca.mohard.entities.illagers.enchanter.EnchanterEntity;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModEntities;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public class EnchanterIllager extends EnchanterEntity {

    public EnchanterIllager(EntityType<?> type, Level world){
        this(world);
    }

    protected EnchanterIllager(Level world) {
        super(ModEntities.ENCHANTER.get(), world);
        this.setCanJoinRaid(true);
        this.setWave(4);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.35F)
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(ModAttributes.PHYSICAL_DAMAGE.get(), 2D)
                .add(Attributes.FOLLOW_RANGE, 17.0D)
                .add(ModAttributes.RAW_ARMOR.get(), 2)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 1)
                .add(ModAttributes.AGILITY.get(), 1)
                .add(ModAttributes.ARMOR_PENETRATION.get(), 0)
                .add(ModAttributes.MAGIC_DAMAGE.get(), 5);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damage, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(damage, p_213333_2_, p_213333_3_);
        if(damage.getEntity() instanceof Player){
            if(Math.random() * 100 < 10){
                this.spawnAtLocation(ModItens.BOOK_ARTIFACT.get());
            }
        }

    }
}
