package com.elliefigure8.companions.util;

import com.elliefigure8.companions.CompanionsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.swing.text.html.HTML;

public class ModTags
{
    public static class Items
    {
        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(CompanionsMod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name)
        {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Blocks
    {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_AURORITA_TOOL = tag("needs_aurorita_tool");
        public static final TagKey<Block> PAXEL_MINEABLE = tag("mineable/paxel");

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(CompanionsMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name)
        {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
