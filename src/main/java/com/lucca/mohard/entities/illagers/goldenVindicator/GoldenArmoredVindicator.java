package com.lucca.mohard.entities.illagers.goldenVindicator;

import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.level.Level;

public class GoldenArmoredVindicator extends Vindicator {

    public GoldenArmoredVindicator(EntityType<GoldenArmoredVindicator> goldenArmouredVindicatorEntityType, Level world) {
        this(world);
    }

    public GoldenArmoredVindicator(Level p_i50198_2_) {
        super(ModEntities.GOLDEN_ARMORED_VINDICATOR.get(), p_i50198_2_);
        this.setCanJoinRaid(true);
        this.setWave(3);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.4F)
                .add(Attributes.MAX_HEALTH, 36.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(ModAttributes.PHYSICAL_DAMAGE.get(), 3)
                .add(Attributes.FOLLOW_RANGE, 15.0D)
                .add(ModAttributes.RAW_ARMOR.get(), 3)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 1)
                .add(ModAttributes.AGILITY.get(), 4)
                .add(ModAttributes.ARMOR_PENETRATION.get(), 6);
    }


}
