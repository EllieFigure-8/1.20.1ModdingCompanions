package com.elliefigure8.companions.datagen;

import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    //private static final List<ItemLike> AURORITA_SMELTABLES = List.of(ModItems.AURORITA_FRAGMENT.get(), ModBlocks.AURORITA_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AURORITA_ORE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.AURORITA_FRAGMENT.get())
                .unlockedBy("has_bronze_coin", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModItems.AURORITA_FRAGMENT.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AURORITA_FRAGMENT.get(), 9)
                .requires(ModBlocks.AURORITA_ORE.get())
                .unlockedBy("has_aurorita_ore", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.AURORITA_ORE.get()).build()))
                .save(pWriter);
        //nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        //oreSmelting(pWriter, );
    }
}
