package com.lucca.mohard.entities.illagers.diamondPillager;

import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DiamondArmoredPillager extends Pillager {

    public DiamondArmoredPillager(EntityType<DiamondArmoredPillager> diamondArmouredPillagerEntityType, Level world) {
        this(world);
    }

    public DiamondArmoredPillager(Level p_i50198_2_) {
        super(ModEntities.DIAMOND_ARMORED_PILLAGER.get(), p_i50198_2_);
        this.setCanJoinRaid(true);
        this.setWave(4);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.45F)
                .add(Attributes.MAX_HEALTH, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 17.0D)
                .add(ModAttributes.RAW_ARMOR.get(), 1)
                .add(ModAttributes.PROJECTILE_DAMAGE.get(), 7)
                .add(ModAttributes.PHYSICAL_DAMAGE.get(), 7)
                .add(ModAttributes.AGILITY.get(), 7)
                .add(ModAttributes.ARMOR_PENETRATION.get(), 6);

    }


}
