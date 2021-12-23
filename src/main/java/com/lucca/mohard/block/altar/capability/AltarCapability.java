package com.lucca.mohard.block.altar.capability;

import net.minecraftforge.items.ItemStackHandler;

public class AltarCapability extends ItemStackHandler {

    public static final int SLOTS = 9;

    public AltarCapability(){
        super(SLOTS);
    }

    @Override
    public int getSlots() {
        return super.getSlots();
    }
}
