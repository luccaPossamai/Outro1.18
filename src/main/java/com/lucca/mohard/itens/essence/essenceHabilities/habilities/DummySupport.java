package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.entities.dummy.DummyEntity;
import com.lucca.mohard.help.Methods;
import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.setup.init.ModEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public class DummySupport extends GenericEssenceHability {

    private DummyEntity dummy;

    public DummySupport(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        DummyEntity dummyEntity = ModEntityTypes.DUMMY_ENTITY_TYPE.create(player.level);
        dummyEntity.teleportTo(player.getX(), player.getY(), player.getZ());
        dummyEntity.setItemSlot(EquipmentSlot.CHEST, player.getItemBySlot(EquipmentSlot.CHEST));
        dummyEntity.setCustomName(player.getName());
        dummyEntity.setItemSlot(EquipmentSlot.HEAD, player.getItemBySlot(EquipmentSlot.HEAD));
        player.level.addFreshEntity(dummyEntity);
        this.dummy = dummyEntity;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.dummy != null && this.dummy.isAlive()) {
            TargetingConditions pred = TargetingConditions.forCombat().range(10);
            for (Mob mob : this.dummy.level.getNearbyEntities(Mob.class, pred, dummy, dummy.getBoundingBox().inflate(10))) {
                if (mob.getTarget() != dummy) {
                    mob.setTarget(dummy);
                }
                if(mob.getLastHurtByMob() instanceof Player){
                    mob.setLastHurtByMob(dummy);
                    mob.setLastHurtByPlayer(null);
                }
            }
        }
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }

    @Override
    public void stopEffects() {
        super.stopEffects();
        if(this.dummy != null){
            this.dummy.remove(Entity.RemovalReason.DISCARDED);
        }
    }
}
