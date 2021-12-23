package com.lucca.mohard.capabilities.mana;

public class ManaCapability {

    protected int mana = 0;
    protected int maxMana;

    public ManaCapability(int maxMana){
        this.maxMana = maxMana;
    }

    public void regenMana(int count){
        this.mana = Math.min(this.mana + count, this.maxMana);
    }

    public void setMana(int mana){
        this.mana = Math.min(mana, this.maxMana);
    }

    public int getMana(){
        return this.mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public boolean canRegenerate(){
        return this.mana < this.maxMana;
    }
}
