package com.elliefigure8.companions.datagen.loot;

import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider
{
    public ModBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());

    }

    @Override
    protected void generate()
    {
        this.add(ModBlocks.AURORITA_ORE.get(),
                block -> createOreDrop(ModBlocks.AURORITA_ORE.get(), ModItems.AURORITA_FRAGMENT.get()));
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.dropSelf(ModBlocks.GRAVESTONE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
