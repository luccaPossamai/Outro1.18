package com.lucca.mohard.itens.essence.essenceSyntonizer;

import javax.annotation.Nullable;

public class EssenceSyntonizerTierHelper {

    @Nullable
    public static EssenceSyntonizerTier getEssenceSyntonizerTierByInt(int id){
        return id < EssenceSyntonizerTier.values().length ? EssenceSyntonizerTier.values()[id] : null;
    }

    @Nullable
    public static EssenceSyntonizerTier getEssenceSyntonizerTierByName(String name){
        for(EssenceSyntonizerTier tier : EssenceSyntonizerTier.values()){
            if(tier.name == name) {
                return tier;
            }
        }
        return null;
    }
}
