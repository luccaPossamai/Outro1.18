package com.lucca.mohard.itens.essence.essenceHabilities;


import net.minecraft.world.entity.player.Player;

public enum EssenceHabilities {

    STONE_WISDOM(EssenceHabilitiesCategory.TANK, 2400, 300),
    GLASS_BEAST(EssenceHabilitiesCategory.DAMAGE, 1200, 200),
    CONTEMPLATION_SCREAM(EssenceHabilitiesCategory.TANK,2400, 500),
    CHICK_EVASION(EssenceHabilitiesCategory.SUPPORT, 2000, 200),
    AUTOCIDE(EssenceHabilitiesCategory.DAMAGE, 2000 ,20),
    DUMMY_SUPPORT(EssenceHabilitiesCategory.SUPPORT, 4800, 600),
    CURSED_FRIENDSHIP(EssenceHabilitiesCategory.TANK, 6000, 600),
    NAKAS_MASTERY(EssenceHabilitiesCategory.DAMAGE, 2000, 400),

    ;

    EssenceHabilitiesCategory category;
    int cooldown;
    int duration;

    EssenceHabilities(EssenceHabilitiesCategory essenceHabilitiesCategory,  int cooldown, int duration){
        this.category = essenceHabilitiesCategory;
        this.cooldown = cooldown;
        this.duration = duration;
    }

    public enum EssenceHabilitiesCategory{
        DAMAGE(0),
        TANK(1),
        SUPPORT(2);

        int categoryId;
        EssenceHabilitiesCategory(int id){
            this.categoryId = id;
        }

        public int getCategoryId() {
            return categoryId;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public EssenceHabilitiesCategory getCategory() {
        return category;
    }

    public int getDuration() {
        return duration;
    }
}

