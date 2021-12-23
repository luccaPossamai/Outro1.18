package com.lucca.mohard.itens.essence;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EssenceDataHelper {

    static final Map<EssenceItem, EssenceData> essenceMap = setupEssenceMap();
    static final Map<EntityType<?>, List<EssenceItem>> entityMap = setupEntityMap();

    static Map<EssenceItem, EssenceData> setupEssenceMap(){
        Map<EssenceItem, EssenceData> map = new HashMap<>();
        for(EssenceData essenceData : EssenceData.values()){
            map.put(essenceData.getEssence(), essenceData);
        }
        return map;
    }

    static Map<EntityType<?>, List<EssenceItem>> setupEntityMap(){
        Map<EntityType<?>, List<EssenceItem>> map = new HashMap<>();
        for(EssenceData essenceData : EssenceData.values()) {
            EntityType<?> type = essenceData.getEssence().getType(null);
            if(map.containsKey(type)) {
                map.get(type).add(essenceData.getEssence());
            } else {
                List<EssenceItem> essences = new ArrayList<>();
                essences.add(essenceData.getEssence());
                map.put(essenceData.getEssence().getType(null), essences);
            }
        }
        return map;
    }

    @Nullable
    public static EssenceData getEssenceDataByEssence(EssenceItem essence){
        return essenceMap.getOrDefault(essence, null);
    }

    @Nullable
    public static EssenceItem getEssenceItemByEntity(EntityType<?> type){
        return entityMap.containsKey(type) ?
                entityMap.get(type).stream().filter((essenceItem -> !essenceItem.isAlternative())).toList().get(0)
                : null;
    }

    public static int getEssenceLevel(ItemStack stack){
        return stack.getOrCreateTag().getInt("Level");
    }

    public static int getEssenceNegativeLevel(ItemStack stack){
        return stack.getOrCreateTag().getInt("NegativeLevel");
    }

    public static ItemStack upgradeLevel(ItemStack stack){
        if(canUpgradeEssence(stack)){
            ItemStack itemStack = stack.copy();
            CompoundTag tag = itemStack.getOrCreateTag();
            String levelS = "Level";
            int level = tag.getInt(levelS);
            tag.remove(levelS);
            tag.putInt(levelS, level + 1);
            itemStack.setTag(tag);
            return itemStack;
        }
        return stack;
    }

    public static ItemStack upgradeNegativeLevel(ItemStack stack){
        if(canUpgradeNegativeEssence(stack)){
            ItemStack itemStack = stack.copy();
            CompoundTag tag = itemStack.getOrCreateTag();
            String levelS = "NegativeLevel";
            int level = tag.getInt(levelS);
            tag.remove(levelS);
            tag.putInt(levelS, level + 1);
            itemStack.setTag(tag);
            return itemStack;
        }
        return stack;
    }

    public static boolean canUpgradeEssence(ItemStack stack){
        return getEssenceLevel(stack) < 3;
    }

    public static boolean canUpgradeNegativeEssence(ItemStack stack){
        if(stack.getItem() instanceof EssenceItem){
            EssenceData data = EssenceDataHelper.getEssenceDataByEssence((EssenceItem) stack.getItem());
            boolean flag = false;
            for(Double stat: data.getStats(0,0)){
                if(stat < 0){
                    flag = true;
                    break;
                }
            }
            return getEssenceNegativeLevel(stack) < 3 && flag;
        }
        return false;
    }

}
