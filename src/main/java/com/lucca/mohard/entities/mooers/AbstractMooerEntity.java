package com.lucca.mohard.entities.mooers;


import com.lucca.mohard.entities.mooers.AI.MooerAction;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class AbstractMooerEntity extends AgeableMob implements InventoryCarrier, Npc, Merchant {

    public AbstractMooerEntity(EntityType<? extends AbstractMooerEntity> p_i48553_1_, Level p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);

        this.setCanPickUpLoot(true);
        this.applyOpenDoorsAbility();
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    private void applyOpenDoorsAbility() {
        if (GoalUtils.hasGroundPathNavigation(this)) {
            ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public abstract MooerAction getArmPose();

    public boolean isAdult() {
        return !this.isBaby();
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return null;
    }

    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }
}
