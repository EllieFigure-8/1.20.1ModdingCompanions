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
    public static final int DEFAULT_PARRY_COOLDOWN = 0;
    public static final int DEFAULT_PARRY_DURATION = 20;
    public static final boolean DEFAULT_HAS_PARRY = true;
    public static final boolean DEFAULT_HAS_PRESSED_PARRY = false;
    public static final boolean DEFAULT_HAS_PARRIED_ATTACK = false;

    public static int getParryCooldown(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("ParryCooldown")) {
            tag.putInt("ParryCooldown", DEFAULT_PARRY_COOLDOWN);
        }
        return tag.getInt("ParryCooldown");
    }

    public static void setParryCooldown(ItemStack stack, int cooldown) {
        stack.getOrCreateTag().putInt("ParryCooldown", cooldown);
    }

    public static int getParryDuration(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("ParryDuration")) {
            tag.putInt("ParryDuration", DEFAULT_PARRY_DURATION);
        }
        return tag.getInt("ParryDuration");
    }

    public static void setParryDuration(ItemStack stack, int duration) {
        stack.getOrCreateTag().putInt("ParryDuration", duration);
    }

    public static boolean getHasParry(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("HasParry")) {
            tag.putBoolean("HasParry", DEFAULT_HAS_PARRY);
        }
        return tag.getBoolean("HasParry");
    }

    public static void setHasParry(ItemStack stack, boolean hasParry) {
        stack.getOrCreateTag().putBoolean("HasParry", hasParry);
    }

    public static boolean getHasPressedParry(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("HasPressedParry")) {
            tag.putBoolean("HasPressedParry", DEFAULT_HAS_PRESSED_PARRY);
        }
        return tag.getBoolean("HasPressedParry");
    }

    public static void setHasPressedParry(ItemStack stack, boolean hasPressedParry) {
        stack.getOrCreateTag().putBoolean("HasPressedParry", hasPressedParry);
    }

    public static boolean getHasParriedAttack(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("HasParriedAttack")) {
            tag.putBoolean("HasParriedAttack", DEFAULT_HAS_PARRIED_ATTACK);
        }
        return tag.getBoolean("HasParriedAttack");
    }

    public static void setHasParriedAttack(ItemStack stack, boolean hasParriedAttack) {
        stack.getOrCreateTag().putBoolean("HasParriedAttack", hasParriedAttack);
    }

    //Red Belt Parry
    public static void setRedBeltParryUsed(ItemStack stack, boolean used) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("RedBeltParryUsed", used);
        stack.setTag(nbt);
    }

    public static boolean getRedBeltParryUsed(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getBoolean("RedBeltParryUsed");
    }
}
