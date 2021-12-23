package com.lucca.mohard.block.altar.capability;

import com.lucca.mohard.capabilities.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AltarCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private AltarCapability ALTAR_HANDLER;

    @Nonnull
    private AltarCapability getInventory() {
        if (ALTAR_HANDLER == null) {
            ALTAR_HANDLER = new AltarCapability();
        }
        return ALTAR_HANDLER;
    }

    private final LazyOptional<AltarCapability> lazyInventory = LazyOptional.of(this::getInventory);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ALTAR_CAPABILITY){
            return (LazyOptional<T>)(lazyInventory);
        }
        return LazyOptional.empty();
    }


    @Override
    public CompoundTag serializeNBT() {
        return this.getInventory().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        //CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(getInventory(), null, nbt);
        this.getInventory().deserializeNBT(nbt);
    }

}
