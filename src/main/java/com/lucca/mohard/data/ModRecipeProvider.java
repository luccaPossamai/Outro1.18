package com.lucca.mohard.data;

import com.lucca.mohard.setup.init.ModBlocks;
import com.lucca.mohard.setup.init.ModItens;
import net.minecraft.core.Registry;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.ALTAR_BLOCK)
                .define('#', ModBlocks.DENSE_SAND.asItem())
                .define('&', ModItens.TRUE_VOID_REMNANTS.get())
                .pattern("###")
                .pattern("#&#")
                .pattern("###")
                .unlockedBy("has_item", has(ModBlocks.DENSE_SAND.asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.DENSE_SAND)
                .define('#', Items.SAND.asItem())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(Items.SAND.asItem()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModBlocks.BLOODY_SAND).requires(ModBlocks.DENSE_SAND, 1).requires(ModItens.TRUE_VOID_REMNANTS.get()).unlockedBy("has_true_void_remnants", has(ModItens.TRUE_VOID_REMNANTS.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItens.ESSENCE_SYNTONIZER.get()).requires(Items.STICK, 1).requires(ModItens.TRUE_VOID_REMNANTS.get()).unlockedBy("has_true_void_remnants", has(ModItens.TRUE_VOID_REMNANTS.get())).save(consumer);


        ShapedRecipeBuilder.shaped(ModBlocks.HEAVY_SANDSTONE).define('#', ModBlocks.DENSE_SAND).pattern("##").pattern("##").unlockedBy("has_dense_sand", has(ModBlocks.DENSE_SAND)).save(consumer);
        slabBuilder(ModBlocks.HEAVY_SANDSTONE_SLAB, Ingredient.of(ModBlocks.HEAVY_SANDSTONE, ModBlocks.CHISELED_HEAVY_SANDSTONE));
        ShapedRecipeBuilder.shaped(ModBlocks.CUT_HEAVY_SANDSTONE_SLAB, 6).define('#', ModBlocks.CUT_HEAVY_SANDSTONE).pattern("###").unlockedBy("has_cut_heavy_sandstone", has(ModBlocks.CUT_HEAVY_SANDSTONE)).save(consumer);
        stairBuilder(ModBlocks.HEAVY_SANDSTONE_STAIRS, Ingredient.of(ModBlocks.HEAVY_SANDSTONE, ModBlocks.CHISELED_HEAVY_SANDSTONE, ModBlocks.CUT_HEAVY_SANDSTONE));

        ShapelessRecipeBuilder.shapeless(ModBlocks.DENSE_SPRUCE_PLANKS, 4).requires(ModBlocks.DENSE_SPRUCE_LOG).group("planks").unlockedBy("has_log", has(ModBlocks.DENSE_SPRUCE_LOG)).save(consumer);
        trapdoorBuilder(ModBlocks.DENSE_SPRUCE_TRAPDOOR, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));
        slabBuilder(ModBlocks.DENSE_SPRUCE_SLAB, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));
        fenceBuilder(ModBlocks.DENSE_SPRUCE_FENCE, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));
        fenceGateBuilder(ModBlocks.DENSE_SPRUCE_FENCE_GATE, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));
        stairBuilder(ModBlocks.DENSE_SPRUCE_STAIRS, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));
        doorBuilder(ModBlocks.DENSE_SPRUCE_DOOR, Ingredient.of(ModBlocks.DENSE_SPRUCE_PLANKS));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.CUT_HEAVY_SANDSTONE).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "cut_heavy_sandstone_from_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.HEAVY_SANDSTONE_SLAB, 2).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "heavy_sandstone_slab_from_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.CUT_HEAVY_SANDSTONE_SLAB, 2).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "cut_heavy_sandstone_slab_from_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CUT_HEAVY_SANDSTONE), ModBlocks.CUT_HEAVY_SANDSTONE_SLAB, 2).unlockedBy("has_heavy_cut_sandstone", has(ModBlocks.CUT_HEAVY_SANDSTONE)).save(consumer, "cut_heavy_sandstone_slab_from_cut_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.HEAVY_SANDSTONE_STAIRS).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "heavy_sandstone_stairs_from_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.HEAVY_SANDSTONE_WALL).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "heavy_sandstone_wall_from_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HEAVY_SANDSTONE), ModBlocks.CHISELED_HEAVY_SANDSTONE).unlockedBy("has_heavy_sandstone", has(ModBlocks.HEAVY_SANDSTONE)).save(consumer, "chiseled_heavy_sandstone_from_heavy_sandstone_stonecutting");


        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.SMOOTH_HEAVY_SANDSTONE), ModBlocks.SMOOTH_HEAVY_SANDSTONE_SLAB, 2).unlockedBy("has_smooth_heavy_sandstone", has(ModBlocks.SMOOTH_HEAVY_SANDSTONE)).save(consumer, "smooth_heavy_sandstone_slab_from_smooth_heavy_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.SMOOTH_HEAVY_SANDSTONE), ModBlocks.SMOOTH_HEAVY_SANDSTONE_STAIRS).unlockedBy("has_smooth_heavy_sandstone", has(ModBlocks.SMOOTH_HEAVY_SANDSTONE)).save(consumer, "smooth_heavy_sandstone_stairs_from_smooth_heavy_sandstone_stonecutting");
    }
    private static RecipeBuilder fenceBuilder(ItemLike p_176679_, Ingredient p_176680_) {
        int i = p_176679_ == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = p_176679_ == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : Items.STICK;
        return ShapedRecipeBuilder.shaped(p_176679_, i).define('W', p_176680_).define('#', item).pattern("W#W").pattern("W#W");
    }
    private static RecipeBuilder fenceGateBuilder(ItemLike p_176685_, Ingredient p_176686_) {
        return ShapedRecipeBuilder.shaped(p_176685_).define('#', Items.STICK).define('W', p_176686_).pattern("#W#").pattern("#W#");
    }
    private static RecipeBuilder doorBuilder(ItemLike p_176671_, Ingredient p_176672_) {
        return ShapedRecipeBuilder.shaped(p_176671_, 3).define('#', p_176672_).pattern("##").pattern("##").pattern("##");
    }
    private static void slab(Consumer<FinishedRecipe> p_176701_, ItemLike p_176702_, ItemLike p_176703_) {
        slabBuilder(p_176702_, Ingredient.of(p_176703_)).unlockedBy(getHasName(p_176703_), has(p_176703_)).save(p_176701_);
    }

    private static RecipeBuilder slabBuilder(ItemLike p_176705_, Ingredient p_176706_) {
        return ShapedRecipeBuilder.shaped(p_176705_, 6).define('#', p_176706_).pattern("###");
    }
    private static String getHasName(ItemLike p_176603_) {
        return "has_" + getItemName(p_176603_);
    }
    private static String getItemName(ItemLike p_176633_) {
        return Registry.ITEM.getKey(p_176633_.asItem()).getPath();
    }
    private static RecipeBuilder trapdoorBuilder(ItemLike p_176721_, Ingredient p_176722_) {
        return ShapedRecipeBuilder.shaped(p_176721_, 2).define('#', p_176722_).pattern("###").pattern("###");
    }
    private static RecipeBuilder stairBuilder(ItemLike p_176711_, Ingredient p_176712_) {
        return ShapedRecipeBuilder.shaped(p_176711_, 4).define('#', p_176712_).pattern("#  ").pattern("## ").pattern("###");
    }

}
