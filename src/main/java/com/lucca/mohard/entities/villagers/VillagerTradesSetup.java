package com.lucca.mohard.entities.villagers;

import com.lucca.mohard.setup.init.ModEssences;
import com.lucca.mohard.setup.init.ModProfessions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

@Mod.EventBusSubscriber
public class VillagerTradesSetup {

    @SubscribeEvent
    public static void trades(VillagerTradesEvent event){
        if(event.getType().equals(ModProfessions.MASTER.get())){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.put(1, Arrays.asList(new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.CHICKEN_ESSENCE.get(), 26, 1, 4, 1),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.COW_ESSENCE.get(), 22, 1, 4, 1),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.SKELETON_ESSENCE.get(), 1, 1, 4, 2),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.ZOMBIE_ESSENCE.get(), 1,1, 4, 2)));

            trades.put(2, Arrays.asList(new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.SHEEP_ESSENCE.get(), 19, 1, 6, 2),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.PIG_ESSENCE.get(), 18, 1, 6, 2),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.CREEPER_ESSENCE.get(), 1,3, 6, 4),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.SPIDER_ESSENCE.get(), 1,2, 6, 4)));

            trades.put(3, Arrays.asList(new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.VILLAGER_ESSENCE.get(), 30, 1, 8,8),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.CAT_ESSENCE.get(), 29, 1, 8, 8),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.DROWNED_ESSENCE.get(), 1,10, 8, 15),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.PILLAGER_ESSENCE.get(), 1,16, 8, 15)));

            trades.put(4, Arrays.asList(new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.WANDERING_TRADER_ESSENCE.get(), 43, 1, 10, 15),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.TRADER_LLAMA_ESSENCE.get(), 43, 1, 10,15),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.WITHER_SKELETON_ESSENCE.get(),1, 20, 10, 20),
                    new VillagerTradesSetup.EmeraldForItemsTrade(ModEssences.BLAZE_ESSENCE.get(), 1,23, 10, 10)));

            trades.put(5, Arrays.asList(new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.IRON_GOLEM_ESSENCE.get(), 64, 1,1, 25),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.RAVAGER_ESSENCE.get(), 64, 1,1, 25),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.WITCH_ESSENCE.get(), 64, 1,1, 25),
                    new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.MOOER_ESSENCE.get(), 64, 1,1, 25),
                new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.MOOSHROOM_MOOER_ESSENCE.get(), 64, 1,1, 25),
                new VillagerTradesSetup.ItemsForEmeraldsTrade(ModEssences.GILDED_MOOER_ESSENCE.get(), 64, 1,1, 25)));
        }
    }



    static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final int emeraldValue;

        public EmeraldForItemsTrade(ItemLike item, int cost,int number, int uses, int xp) {
            this.item = item.asItem();
            this.cost = cost;
            this.maxUses = uses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
            this.emeraldValue = number;
        }

        public MerchantOffer getOffer(Entity entity, Random random) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.emeraldValue), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block block, int cost, int number, int uses, int xp) {
            this(new ItemStack(block), cost, number, uses, xp);
        }

        public ItemsForEmeraldsTrade(Item item, int cost, int number, int xp) {
            this(new ItemStack(item), cost, number, 12, xp);
        }

        public ItemsForEmeraldsTrade(Item item, int cost, int number, int uses, int xp) {
            this(new ItemStack(item), cost, number, uses, xp);
        }

        public ItemsForEmeraldsTrade(ItemStack itemStack, int cost, int number, int uses, int xp) {
            this(itemStack, cost, number, uses, xp, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack itemStack, int cost, int number, int uses, int xp, float multiplier
        ) {
            this.itemStack = itemStack;
            this.emeraldCost = cost;
            this.numberOfItems = number;
            this.maxUses = uses;
            this.villagerXp = xp;
            this.priceMultiplier = multiplier
            ;
        }

        public MerchantOffer getOffer(Entity entity, Random random) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }


}
