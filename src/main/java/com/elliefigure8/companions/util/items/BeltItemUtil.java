package com.elliefigure8.companions.util.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class BeltItemUtil {
    public static boolean getCanDodge(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getBoolean("CanDodge");
    }

    public static void setCanDodge(ItemStack stack, boolean value) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("CanDodge", value);
        stack.setTag(tag);
    }

    public static int getDodgeCooldown(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt("DodgeCooldown");
    }

    public static void setDodgeCooldown(ItemStack stack, int cooldown) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("DodgeCooldown", cooldown);
        stack.setTag(tag);
    }
}
