package com.elliefigure8.companions.util.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class BeltItemUtil {

    public static final boolean DEFAULT_CAN_DODGE = true;
    public static final int DEFAULT_DODGE_COOLDOWN = 0;

    public static boolean getCanDodge(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("CanDodge")) {
            tag.putBoolean("CanDodge", DEFAULT_CAN_DODGE);
        }
        return tag.getBoolean("CanDodge");
    }

    public static void setCanDodge(ItemStack stack, boolean value) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("CanDodge", value);
        stack.setTag(tag);
    }

    public static int getDodgeCooldown(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("DodgeCooldown")) {
            tag.putInt("DodgeCooldown", DEFAULT_DODGE_COOLDOWN);
        }
        return tag.getInt("DodgeCooldown");
    }

    public static void setDodgeCooldown(ItemStack stack, int cooldown) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("DodgeCooldown", cooldown);
        stack.setTag(tag);
    }

    //Red Belt Dodge
    public static void setRedBeltDodgeUsed(ItemStack stack, boolean ParryDodgeSharedCooldown) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("RedBeltDodgeUsed", ParryDodgeSharedCooldown);
        stack.setTag(nbt);
    }

    public static boolean getRedBeltDodgeUsed(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("RedBeltDodgeUsed");
    }
}
