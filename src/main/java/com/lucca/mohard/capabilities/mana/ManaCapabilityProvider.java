package com.lucca.mohard.capabilities.mana;

import com.lucca.mohard.capabilities.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private ManaCapability MANA_CAPABILITY;

    @Nonnull
    private ManaCapability getManaCap() {
        if (MANA_CAPABILITY == null) {
            MANA_CAPABILITY = new ManaCapability(600);
        }
        return MANA_CAPABILITY;
    }

    private final LazyOptional<ManaCapability> lazyMana = LazyOptional.of(this::getManaCap);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.MANA_CAPABILITY) {
            return (LazyOptional<T>)(lazyMana);
        }
        return LazyOptional.empty();
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        int mana = this.getManaCap().getMana();
        tag.putInt("Mana", mana);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getManaCap().setMana(nbt.contains("Mana") ? nbt.getInt("Mana") : 0);
    }

}

