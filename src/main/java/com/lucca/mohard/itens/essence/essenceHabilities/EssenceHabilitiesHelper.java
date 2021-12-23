package com.lucca.mohard.itens.essence.essenceHabilities;

import com.lucca.mohard.itens.essence.essenceHabilities.habilities.*;
import com.lucca.mohard.itens.essence.essenceSyntonizer.EssenceSyntonizerTier;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class EssenceHabilitiesHelper {

    @Nullable
    public static GenericEssenceHability generateEssenceHability(EssenceHabilities hability, Player player, int duration){
        if(hability.equals(EssenceHabilities.STONE_WISDOM)) return new StoneWisdom(player, duration);

        if(hability.equals(EssenceHabilities.GLASS_BEAST)) return new GlassBeast(player, duration);

        if(hability.equals(EssenceHabilities.CONTEMPLATION_SCREAM)) return new ContemplationScream(player, duration);

        if(hability.equals(EssenceHabilities.CHICK_EVASION)) return new ChickEvasion(player, duration);

        if(hability.equals(EssenceHabilities.AUTOCIDE)) return new Autocide(player, duration);

        if(hability.equals(EssenceHabilities.DUMMY_SUPPORT)) return new DummySupport(player, duration);

        if(hability.equals(EssenceHabilities.CURSED_FRIENDSHIP)) return new CursedFriendship(player, duration);

        if(hability.equals(EssenceHabilities.NAKAS_MASTERY)) return new NakasMatery(player, duration);

        return null;
    }

    public static int calculateCooldown(EssenceHabilities hability, Player player, EssenceSyntonizerTier tier){
        double playerIntellect = player.getAttributeValue(ModAttributes.INTELLECT); //0 - 100
        double percentLost = 1 - (playerIntellect / 125);
        int cooldown = hability.getCooldown() - (tier.getId() * 20) - ((tier.getId() / 10) * 50);
        return (int) (cooldown * percentLost);
    }

    public static int calculateDuration(EssenceHabilities hability, Player player, EssenceSyntonizerTier tier){
        double playerIntellect = player.getAttributeValue(ModAttributes.INTELLECT); //0 - 100
        double percentGain = 1 + (playerIntellect / 80);
        int duration = hability.getDuration() + ((tier.getId() / 3) * 50) + ((tier.getId() / 10) * 50);
        return (int) (duration * percentGain);
    }
}
