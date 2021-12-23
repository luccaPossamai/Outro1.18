package com.lucca.mohard.itens.essence;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;


public class EssenceItem extends Item {

    private final int color1;
    private final int color2;
    private final EntityType<?> defaultType;
    private final EntityType<?> corruptedType;
    private final EssencePredicate<LivingEntity> alternativeSupplier;
    private final boolean alternative;

    public EssenceItem(Properties properties, @Nullable EntityType type, @Nullable EntityType corruptedType, int color1, int color2, EssencePredicate<LivingEntity> condition, boolean alternative){
        super(properties);
        this.defaultType = type;
        this.corruptedType = corruptedType;
        this.color1 = color1;
        this.color2 = color2;
        this.alternativeSupplier = condition;
        this.alternative = alternative;
    }

    public boolean isAlternative() {
        return alternative;
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int p_195983_1_) {
        return p_195983_1_ == 0 ? this.color1 : this.color2;
    }

    public EssenceItem getEssence(LivingEntity entity){
        EssenceData essenceData = EssenceDataHelper.getEssenceDataByEssence(this);
        if(essenceData.hasAlternative()){
            if(this.alternativeSupplier.test(entity)){
                return essenceData.getAlternativeEssence();
            }
        }
        return this;
    }

    public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
        return this.defaultType;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        CompoundTag tag = p_41404_.getOrCreateTag();
        if(!tag.contains("Level")){
            tag.putInt("Level", 0);
        }
        if(!tag.contains("NegativeLevel")){
            tag.putInt("NegativeLevel", 0);
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack item,
                                @Nullable Level world,
                                List<Component> tooltip,
                                TooltipFlag flagIn) {

        super.appendHoverText(item, world, tooltip, flagIn);
        if(item.getItem() instanceof EssenceItem) {
            EssenceItem essence = (EssenceItem) item.getItem();
            int upgradeLevel = EssenceDataHelper.getEssenceLevel(item);
            int negativeUpgradeLevel = EssenceDataHelper.getEssenceNegativeLevel(item);
            List<String> lista =  EssenceDataHelper.getEssenceDataByEssence(essence).getFormatedStats(upgradeLevel, negativeUpgradeLevel);
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).contains("0")) {
                        if (lista.get(i).contains("1") ||
                                lista.get(i).contains("2") ||
                                lista.get(i).contains("3") ||
                                lista.get(i).contains("4") ||
                                lista.get(i).contains("5") ||
                                lista.get(i).contains("6") ||
                                lista.get(i).contains("7") ||
                                lista.get(i).contains("8") ||
                                lista.get(i).contains("9")) {

                            tooltip.add(new TextComponent("\u00A79" + lista.get(i)));
                        }

                    } else {
                        tooltip.add(new TextComponent("\u00A79" + lista.get(i)));
                    }
                }
            } else {
                tooltip.add(new TextComponent("\u00A79null"));
            }
        }
    }

    public boolean isBossEssence(){
        return getType(null).equals(EntityType.WITHER) ||
                getType(null).equals(EntityType.ELDER_GUARDIAN) ||
                getType(null).equals(EntityType.ENDER_DRAGON);
    }
}
