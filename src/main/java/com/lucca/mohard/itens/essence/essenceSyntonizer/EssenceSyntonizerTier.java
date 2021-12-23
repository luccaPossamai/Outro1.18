package com.lucca.mohard.itens.essence.essenceSyntonizer;

public enum EssenceSyntonizerTier {

    NONE(0,"None", 1, "Wooden"),
    WOODEN(1,"Wooden", 10, "Stone"),
    STONE(2,"Stone", 50, "Copper"),
    COPPER(3,"Copper", 250, "Iron"),
    IRON(4,"Iron", 750, "Lapis"),
    LAPIS(5,"Lapis", 1250, "Redstone"),
    REDSTONE(6,"Redstone", 3000, "Gold"),
    GOLD(7,"Gold", 4000, "Diamond"),
    DIAMOND(8,"Diamond", 5250, "Netherite"),
    NETHERITE(9,"Netherite", 6750, "Vilio"),
    VILIO(10,"Vilio", 8500, "Null")
    ;

    int id;
    int xpToNextLevel;
    String name;
    String nextTier;


    EssenceSyntonizerTier(int id, String name, int xpToNextLevel, String nextTier){
        this.xpToNextLevel = xpToNextLevel;
        this.name = name;
        this.nextTier = nextTier;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
