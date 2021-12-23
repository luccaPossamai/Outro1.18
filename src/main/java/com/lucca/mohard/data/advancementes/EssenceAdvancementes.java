package com.lucca.mohard.data.advancementes;

import com.lucca.mohard.itens.essence.EssenceData;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModEssences;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Consumer;

public class EssenceAdvancementes implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement advancement = Advancement.Builder.advancement().display(ModItens.GENERIC_ESSENCE.get(), new TranslatableComponent("advancements.essence.root.title"), new TranslatableComponent("advancements.essence.root.description"), new ResourceLocation("mohard:textures/block/heavy_sandstone.png"), FrameType.TASK, true, true, false).requirements(RequirementsStrategy.OR).addCriterion("killed_something", KilledTrigger.TriggerInstance.playerKilledEntity()).addCriterion("killed_by_something", KilledTrigger.TriggerInstance.entityKilledPlayer()).save(advancementConsumer, "essence/root");
        Advancement advancementEssenceExchanger = Advancement.Builder.advancement().parent(advancement).display(ModBlocks.ESSENCE_EXHANGER_BLOCK, new TranslatableComponent("advancements.essence.essence_exchanger.title"), new TranslatableComponent("advancements.essence.essence_exchanger.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ESSENCE_EXHANGER_BLOCK)).save(advancementConsumer, "essence/obtain_essence_exchanger");
        Advancement advancementZombie = Advancement.Builder.advancement().parent(advancement).display(ModEssences.ZOMBIE_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_zombie.title"), new TranslatableComponent("advancements.essence.essence_zombie.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_zombie", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.ZOMBIE_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_zombie");
        Advancement advancementPig = Advancement.Builder.advancement().parent(advancement).display(ModEssences.PIG_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_pig.title"), new TranslatableComponent("advancements.essence.essence_pig.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_pig", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.PIG_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_pig");
        Advancement advancementBat = Advancement.Builder.advancement().parent(advancement).display(ModEssences.BAT_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_bat.title"), new TranslatableComponent("advancements.essence.essence_bat.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.BAT_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_bat");
        Advancement advancementAxolotl = Advancement.Builder.advancement().parent(advancement).display(ModEssences.AXOLOTL_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_axolotl.title"), new TranslatableComponent("advancements.essence.essence_axolotl.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_axolotl", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.AXOLOTL_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_axolotl");
        Advancement advancementSquid = Advancement.Builder.advancement().parent(advancement).display(ModEssences.SQUID_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_squid.title"), new TranslatableComponent("advancements.essence.essence_squid.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_squid", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.SQUID_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_squid");
        Advancement advancementSalmon = Advancement.Builder.advancement().parent(advancement).display(ModEssences.SALMON_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_salmon.title"), new TranslatableComponent("advancements.essence.essence_salmon.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_salmon", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.SALMON_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_salmon");
        Advancement advancementVillager = Advancement.Builder.advancement().parent(advancement).display(ModEssences.VILLAGER_ESSENCE.get(), new TranslatableComponent("advancements.essence.essence_villager.title"), new TranslatableComponent("advancements.essence.essence_villager.description"), null, FrameType.TASK, false, false, false).addCriterion("essence_villager", InventoryChangeTrigger.TriggerInstance.hasItems(ModEssences.VILLAGER_ESSENCE.get())).save(advancementConsumer, "essence/obtain_essence_villager");

        for(EssenceData data : EssenceData.values()){
            EssenceItem essenceItem = data.getEssence();
            if(!essenceItem.getType(null).equals(EntityType.ZOMBIE) &&
                    !essenceItem.getType(null).equals(EntityType.PIG) &&
                    !essenceItem.getType(null).equals(EntityType.BAT) &&
                    !essenceItem.getType(null).equals(EntityType.AXOLOTL) &&
                    !essenceItem.getType(null).equals(EntityType.SQUID) &&
                    !essenceItem.getType(null).equals(EntityType.SALMON) &&
                    !essenceItem.getType(null).equals(EntityType.VILLAGER)) {

                String name = formatEssenceName(essenceItem.getRegistryName().toString());
                MobCategory category = essenceItem.getType(null).getCategory();
                if (!data.isEssenceExchangerOnly()) {
                    switch (category) {

                        case MONSTER:

                            advancementZombie = Advancement.Builder.advancement().parent(advancementZombie).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                        case CREATURE:
                            advancementPig = Advancement.Builder.advancement().parent(advancementPig).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                        case AMBIENT:
                            advancementBat = Advancement.Builder.advancement().parent(advancementBat).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                        case UNDERGROUND_WATER_CREATURE:
                            advancementAxolotl = Advancement.Builder.advancement().parent(advancementAxolotl).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                        case WATER_CREATURE:
                            advancementSquid = Advancement.Builder.advancement().parent(advancementSquid).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                        case WATER_AMBIENT:
                            advancementSalmon = Advancement.Builder.advancement().parent(advancementSalmon).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;
                        case MISC:
                            advancementVillager = Advancement.Builder.advancement().parent(advancementVillager).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                            break;

                    }
                } else {
                    Advancement.Builder.advancement().parent(advancementEssenceExchanger).display(essenceItem, new TranslatableComponent("advancements.essence." + name), new TranslatableComponent("advancements.essence." + name + ".description"), null, FrameType.TASK, false, false, false).addCriterion("essence_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(essenceItem)).save(advancementConsumer, "essence/obtain_essence_" + name);
                }

            }
        }

    }

    private String formatEssenceName(String name){
        if(name.contains("mohard:")){
            name = name.replace("mohard:", "");
        }
        if(name.contains("essence_")){
            name = name.replace("essence_", "");
        }

        return name;
    }
}
