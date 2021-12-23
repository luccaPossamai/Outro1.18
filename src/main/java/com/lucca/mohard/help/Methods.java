package com.lucca.mohard.help;


import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.ModAttributes;
import com.lucca.mohard.setup.init.ModEffects;
import com.lucca.mohard.setup.Registration;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Methods {

    private static HashMap<MobEffect, Integer> MOD_EFFECTS_ID = new HashMap<>();

    public static Component stringToText(String message) {
        return new TextComponent(message);
    }
    public static int arredondar(float fator){
        return Math.round(fator);
    }

    public static String formatarStats(String stats){
        String aspas = "''";
        stats = stats.replace("stat","");
        stats = stats.replace("{","");
        stats = stats.replace("}","");
        stats = stats.replace(":","");
        stats = stats.replace('"' + "","");
        return stats;

    }
    public static Attribute getRandomAttribute(){
        double random = Math.random() * 8;
        if(random < 1){
            return Attributes.MAX_HEALTH;

        } else if(random < 2){
            return ModAttributes.PHYSICAL_DAMAGE.get();

        } else if(random < 3){
            return ModAttributes.RAW_ARMOR.get();

        } else if(random < 4){
            return ModAttributes.AGILITY.get();

        } else if(random < 5){
            return ModAttributes.PROJECTILE_DAMAGE.get();

        } else if(random < 6){
            return ModAttributes.MAGIC_DAMAGE.get();

        } else if(random < 7){
            return ModAttributes.INTELLECT;

        } else {
            return ModAttributes.ARMOR_PENETRATION.get();

        }
    }
    public static List<Attribute> getAttributes(){
        return Arrays.asList(
                Attributes.MAX_HEALTH,

                ModAttributes.PHYSICAL_DAMAGE.get(),

                ModAttributes.RAW_ARMOR.get(),

                ModAttributes.AGILITY.get(),

                ModAttributes.PROJECTILE_DAMAGE.get(),

                ModAttributes.MAGIC_DAMAGE.get(),

                ModAttributes.INTELLECT,

                ModAttributes.ARMOR_PENETRATION.get()
        );
    }
    @SuppressWarnings("ZOMBIE VALUES")
    public static List<Double> getAttributesBaseValues(){
        return Arrays.asList(
                20.0,

                2.0,

                0.0,

                0.0,

                1.0,

                1.0,

                0.0,

                0.0
        );
    }
    @SuppressWarnings("GENERIC VALUES")
    public static double getAttributesOperations(int index, double value, int nivel){
        if(index == 0){
            return value + (value * (nivel / 100)) + nivel / 5;

        } else if(index == 1){
            return value + nivel / 2  + (value * (nivel / 50));

        } else if(index == 2){
            return value * nivel + nivel / 2;

        } else if(index == 3){
            return value + (nivel / 600);

        } else if(index == 4){
            return value + nivel / 2  + (value * (nivel / 50));

        } else if(index == 5){
            return value + nivel / 2  + (value * (nivel / 50));

        } else if(index == 6){
            return value = value + (nivel / 600);

        } else {
            return value + (nivel / 10);

        }
    }

    @Nullable
    public static MobEffect getAttributeEffect(Attribute att){
        if (ModAttributes.PHYSICAL_DAMAGE.get().equals(att)) {
            return ModEffects.PHYSICAL_DAMAGE_BUFF.get();

        } else if (Attributes.MAX_HEALTH.equals(att)) {
            return ModEffects.HEALTH_BUFF.get();

        } else if (ModAttributes.RAW_ARMOR.get().equals(att)) {
            return ModEffects.ARMOR_BUFF.get();

        } else if (Attributes.MOVEMENT_SPEED.equals(att) || ModAttributes.AGILITY.get().equals(att)) {
            return ModEffects.AGILITY_BUFF.get();

        } else if (ModAttributes.PROJECTILE_DAMAGE.get().equals(att)) {
            return ModEffects.PROJECTILE_DAMAGE_BUFF.get();

        } else if (ModAttributes.MAGIC_DAMAGE.get().equals(att)) {
            return ModEffects.MAGIC_DAMAGE_BUFF.get();

        } else if (ModAttributes.INTELLECT.equals(att)) {
            return ModEffects.INTELLECT_BUFF.get();

        } else if (ModAttributes.ARMOR_PENETRATION.get().equals(att)) {
            return ModEffects.ARMOR_PENETRATION_BUFF.get();
        } else {
            return null;
        }
    }
    private static void setupModEffects(){
        MOD_EFFECTS_ID.put(ModEffects.HEALTH_BUFF.get(), 101);
        MOD_EFFECTS_ID.put(ModEffects.PHYSICAL_DAMAGE_BUFF.get(), 102);
        MOD_EFFECTS_ID.put(ModEffects.ARMOR_BUFF.get(), 103);
        MOD_EFFECTS_ID.put(ModEffects.AGILITY_BUFF.get(), 104);
        MOD_EFFECTS_ID.put(ModEffects.INTELLECT_BUFF.get(), 105);
        MOD_EFFECTS_ID.put(ModEffects.PROJECTILE_DAMAGE_BUFF.get(), 106);
        MOD_EFFECTS_ID.put(ModEffects.MAGIC_DAMAGE_BUFF.get(), 107);
        MOD_EFFECTS_ID.put(ModEffects.ARMOR_PENETRATION_BUFF.get(), 108);
        MOD_EFFECTS_ID.put(ModEffects.STUN.get(), 109);
        MOD_EFFECTS_ID.put(ModEffects.INCINERATION.get(), 110);
        MOD_EFFECTS_ID.put(ModEffects.TELEPORTATION.get(), 111);
    }
    public static int getModEffectId(MobEffect eff){
        if(MOD_EFFECTS_ID.size() == 0){
            setupModEffects();
        }
        return MOD_EFFECTS_ID.containsKey(eff) ? MOD_EFFECTS_ID.get(eff) : 0;
    }
    @Nullable
    public static MobEffect getModEffectById(int index){
        if(MOD_EFFECTS_ID.size() == 0){
            setupModEffects();
        }
        for(RegistryObject<MobEffect> entry : Registration.EFFECTS.getEntries()){
            if(MOD_EFFECTS_ID.containsKey(entry.get())){
                if(MOD_EFFECTS_ID.get(entry.get()) == index){
                    return entry.get();
                }
            }
        }
        return null;
    }

    public static Component formatEntity(EntityType type){
        return new TranslatableComponent(type.getDescriptionId());
    }

    public static ItemEntity createEssenceItemEntity(EssenceItem essenceItem, Level world, double x, double y, double z){
        ItemStack itemStack = new ItemStack(essenceItem, 1);
        return new ItemEntity(world,
                x, y, z,
                itemStack
        );
    }

    public static Component formatStringEffect(MobEffectInstance effectInstance, String stringInitial){
        MobEffect effect = effectInstance.getEffect();
        int duration = effectInstance.getDuration();
        int amplifier = effectInstance.getAmplifier();
        Component mutablecomponent = new TranslatableComponent(effectInstance.getDescriptionId());
        if (amplifier > 0) {
            mutablecomponent = new TranslatableComponent("potion.withAmplifier", mutablecomponent, new TranslatableComponent("potion.potency." + amplifier));
        }

        if (duration > 20) {
            mutablecomponent = new TranslatableComponent("potion.withDuration", mutablecomponent, MobEffectUtil.formatDuration(effectInstance, 1F));
        }

        mutablecomponent = stringToText(stringInitial + mutablecomponent.getString());

        return mutablecomponent;
    }

}
