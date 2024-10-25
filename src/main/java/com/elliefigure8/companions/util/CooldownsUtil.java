package com.elliefigure8.companions.util;

public class CooldownsUtil {

    public static int calculateCooldown(int exampleRoundedDamage) {
        System.out.println("calculateCooldown is being used.");
        int ticksCooldown = exampleRoundedDamage * 270;
        if (ticksCooldown > 1800) {ticksCooldown = 1800;}
        if (ticksCooldown < 300) {ticksCooldown = 300;}
        return ticksCooldown;
    }

    // Otras funciones relacionadas con cooldowns se pueden agregar aquí si lo necesitas en el futuro.
}
