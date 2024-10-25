package com.elliefigure8.companions.util.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class ParryItemUtil {

    //Dodge
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

    //Parry
    public static void setParryCooldown(ItemStack stack, int cooldown) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("ParryCooldown", cooldown);
        stack.setTag(nbt);
    }

    public static int getParryCooldown(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("ParryCooldown");
    }

    public static void setParryDuration(ItemStack stack, int duration) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("ParryDuration", duration);
        stack.setTag(nbt);
    }

    public static int getParryDuration(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("ParryDuration");
    }

    public static void setHasParry(ItemStack stack, boolean hasParry) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("HasParry", hasParry);
        stack.setTag(nbt);
    }

    public static boolean getHasParry(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("HasParry");
    }

    public static void setHasPressedParry(ItemStack stack, boolean hasPressed) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("HasPressedParry", hasPressed);
        stack.setTag(nbt);
    }

    public static boolean getHasPressedParry(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("HasPressedParry");
    }

    public static void setHasParriedAttack(ItemStack stack, boolean hasParried) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("HasParriedAttack", hasParried);
        stack.setTag(nbt);
    }

    public static boolean getHasParriedAttack(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("HasParriedAttack");
    }

    public static void setRedBeltParryUsed(ItemStack stack, boolean used) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("RedBeltParryUsed", used);
        stack.setTag(nbt);
    }

    public static boolean getRedBeltParryUsed(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("RedBeltParryUsed");
    }

    public static void setRedBeltParrySharedCooldown(ItemStack stack, int sharedCooldown) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("RedBeltParrySharedCooldown", sharedCooldown);
        stack.setTag(nbt);
    }

    public static int getRedBeltParrySharedCooldown(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("RedBeltParrySharedCooldown");
    }
}
