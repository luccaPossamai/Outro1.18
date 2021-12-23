package com.lucca.mohard.entities.illagers.diamondVindicator;

import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.level.Level;

public class DiamondArmoredVindicator extends Vindicator {

    public DiamondArmoredVindicator(EntityType<DiamondArmoredVindicator> diamondArmouredVindicatorEntityType, Level world) {
        this(world);
    }

    public DiamondArmoredVindicator(Level p_i50198_2_) {
        super(ModEntities.DIAMOND_ARMORED_VINDICATOR.get(), p_i50198_2_);
        this.setCanJoinRaid(true);
        this.setWave(4);
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.45F)
                .add(Attributes.MAX_HEALTH, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(ModAttributes.PHYSICAL_DAMAGE.get(), 5.0D)
                .add(Attributes.FOLLOW_RANGE, 17.0D)
                .add(ModAttributes.RAW_ARMOR.get(), 5)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 1)
                .add(ModAttributes.AGILITY.get(), 5)
                .add(ModAttributes.ARMOR_PENETRATION.get(), 6);
    }


}
