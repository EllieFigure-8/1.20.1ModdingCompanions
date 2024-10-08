package com.elliefigure8.companions.datagen;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider
{

    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, CompanionsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        //Add Item Tags Here
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.AURORITA_HELMET.get(),
                ModItems.AURORITA_CHESTPLATE.get(),
                ModItems.AURORITA_LEGGINGS.get(),
                ModItems.AURORITA_BOOTS.get());

    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
