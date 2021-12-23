package com.lucca.mohard.gui.altar;


public enum AttributesScreenText {

    HEALTH("Affects the creature's maximum health. " +
            "The minimum amount possible is 1 and the maximum is 1024.", "", ""),
    MELEE_DAMAGE("Affects the damage the creature is " +
            "capable of dealing with melee attacks. Like " +
            "attacks with swords, axes, shovels etc.", "", ""),
    RAW_ARMOR("Affects protection from any entity attacks." +
            "Like creeper explosions, zombie attacks, projectile shots etc.", "", ""),
    AGILITY("Affects the creature's movement speed and " +
            "the initial speed at which its projectiles are fired.", "Movement speed: ", "Projectile speed: "),
    PROJECTILE_DAMAGE("Affects the damage the creature is " +
            "capable of dealing with projectiles. Like " +
            "tridents, arrow and even eggs.", "", ""),
    ARMOR_PENETRATION("Affects how much armor will be disdained.", "Armor disdained: ", ""),

    INTELLECT("Affects the cooldown reduction time of the artifacts" +
            " and of the essence habilities.", "Cooldown reduction:", ""),
    MAGIC_DAMAGE("Affects the damage the creature is " +
            "capable of dealing with magic features. Like" +
            "thrown potions, guardian bean, artifacts etc", "", "");



    String description;
    String aditionalDescription;
    String aditionalDescription2;

    AttributesScreenText(String description, String moreDescription, String moreDescription2){
        this.description = description;
        this.aditionalDescription = moreDescription;
        this.aditionalDescription2 = moreDescription2;
    }


}
