package com.elliefigure8.companions.item;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers
{
    public static final Tier AURORITA = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1708, 9f, 4f, 26,
                    ModTags.Blocks.NEEDS_AURORITA_TOOL, () -> Ingredient.of(ModItems.AURORITA_FRAGMENT.get())),
            new ResourceLocation(CompanionsMod.MOD_ID, "aurorita"), List.of(Tiers.DIAMOND), List.of());

}
