package com.lucca.mohard.data;

import com.google.common.collect.ImmutableList;
import com.lucca.mohard.block.CorruptedBerryBush;
import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.Registration;
import com.lucca.mohard.setup.init.ModItens;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator p_i50789_1_) {
        super(p_i50789_1_);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables(){
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker){
        map.forEach((p_218436_2_, p_218436_3_) -> LootTables.validate(validationTracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLoot {

        @Override
        protected void addTables() {
            dropSelf(ModBlocks.ALTAR_BLOCK);
            dropSelf(ModBlocks.BLOODY_SAND);
            dropSelf(ModBlocks.DENSE_SAND);
            dropSelf(ModBlocks.CHISELED_HEAVY_SANDSTONE);
            dropSelf(ModBlocks.CUT_HEAVY_SANDSTONE);
            dropSelf(ModBlocks.HEAVY_SANDSTONE);
            dropSelf(ModBlocks.SMOOTH_HEAVY_SANDSTONE);
            dropSelf(ModBlocks.HEAVY_SANDSTONE_SLAB);
            dropSelf(ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB);
            dropSelf(ModBlocks.CUT_HEAVY_SANDSTONE_SLAB);
            dropSelf(ModBlocks.HEAVY_SANDSTONE_STAIRS);
            dropSelf(ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS);
            dropSelf(ModBlocks.HEAVY_SANDSTONE_WALL);
            dropSelf(ModBlocks.DENSE_CACTUS);
            dropSelf(ModBlocks.ESSENCE_EXHANGER_BLOCK);
            dropSelf(ModBlocks.COPPERED_DEEPSLATE_BLOCK);
            dropSelf(ModBlocks.DENSE_SPRUCE_LOG);
            dropSelf(ModBlocks.DENSE_SPRUCE_SAPLING);
            dropSelf(ModBlocks.DENSE_SPRUCE_PLANKS);
            dropSelf(ModBlocks.DENSE_SPRUCE_STAIRS);
            dropSelf(ModBlocks.DENSE_SPRUCE_SLAB);
            dropSelf(ModBlocks.DENSE_SPRUCE_FENCE);
            dropSelf(ModBlocks.DENSE_SPRUCE_FENCE_GATE);
            dropSelf(ModBlocks.DENSE_SPRUCE_DOOR);
            dropSelf(ModBlocks.DENSE_SPRUCE_TRAPDOOR);

            add(ModBlocks.DENSE_SPRUCE_LEAVES, (leaves) -> (
                    createLeavesDrops(ModBlocks.DENSE_SPRUCE_LEAVES, ModBlocks.DENSE_SPRUCE_SAPLING, new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F}))
            );

            add(ModItens.CORRUPTED_BERRY.getBlock(), applyExplosionDecay(ModBlocks.CORRUPTED_BUSH, LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORRUPTED_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CorruptedBerryBush.AGE, 3))).add(LootItem.lootTableItem(ModItens.CORRUPTED_BERRY)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))).withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORRUPTED_BUSH).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CorruptedBerryBush.AGE, 2))).add(LootItem.lootTableItem(ModItens.CORRUPTED_BERRY)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

        }

        @Override
        protected Iterable<Block> getKnownBlocks(){
            return Registration.BLOCOS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }



}
