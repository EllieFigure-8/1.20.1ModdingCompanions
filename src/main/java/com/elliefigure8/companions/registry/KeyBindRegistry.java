package com.elliefigure8.companions.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;

public class KeyBindRegistry {
    public static final String CATEGORY = "key.category.alliesmod";
    public static final String KEY_WINGS_ABILITY = "key.wingsability.activate";
    public static final String KEY_SETBONUS_ABILITY = "key.setbonusability.activate";
    public static final String KEY_DASH_ABILITY = "key.dashability.activate";
    public static final String KEY_LEAP_ABILITY = "key.leapability.activate";
    public static final String KEY_PARRY_ABILITY = "key.parryability.activate";

    public static KeyMapping WingsAbilityKey;
    public static KeyMapping SetBonusAbilityKey;
    public static KeyMapping DashAbilityKey;
    public static KeyMapping LeapAbilityKey;
    public static KeyMapping ParryAbilityKey;

    public static void register() {
        WingsAbilityKey = new KeyMapping(KEY_WINGS_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, WingsAbilityKey);

        SetBonusAbilityKey = new KeyMapping(KEY_SETBONUS_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, SetBonusAbilityKey);

        DashAbilityKey = new KeyMapping(KEY_DASH_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, DashAbilityKey);

        LeapAbilityKey = new KeyMapping(KEY_LEAP_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, LeapAbilityKey);

        ParryAbilityKey = new KeyMapping(KEY_PARRY_ABILITY, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, ParryAbilityKey);
    }
}
