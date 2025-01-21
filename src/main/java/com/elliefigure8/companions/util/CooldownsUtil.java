package com.elliefigure8.companions.util;

public class CooldownsUtil {

    public static int calculateCooldown(int exampleRoundedDamage) {
        System.out.println("calculateCooldown is being used.");
        int ticksCooldown = exampleRoundedDamage * 270;
        if (ticksCooldown > 1800) {ticksCooldown = 1800;}
        if (ticksCooldown < 300) {ticksCooldown = 300;}
        return ticksCooldown;
    }

    public static boolean DashIsOnCooldown = false;
    public static int MaxCooldown = 180;
    public static int Cooldown = 180;

    public static void DashActivateCooldown()
    {
        DashIsOnCooldown = true;
    }

    public static void DashUpdateCooldown()
    {
        if (DashIsOnCooldown)
        {
            Cooldown--;
            if (Cooldown <= 0)
            {
                DashResetCooldown();
            }
        }
    }

    public static void DashResetCooldown()
    {
        Cooldown = MaxCooldown;
        DashIsOnCooldown = false;
    }

    // Otras funciones relacionadas con cooldowns se pueden agregar aquí si lo necesitas en el futuro.
}
