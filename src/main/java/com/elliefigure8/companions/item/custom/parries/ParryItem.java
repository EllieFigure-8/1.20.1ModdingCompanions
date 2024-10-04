package com.elliefigure8.companions.item.custom.parries;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Properties;

public class ParryItem extends Item
{
    public ParryItem(Properties pProperties)
    {
        super(pProperties);
    }

    public static final int maxCooldown = 300;
    public static int cooldown = 0;
    public static final int maxParryDuration = 20;
    public static int parryDuration = 20;
    public static boolean hasParry = false;
    public static boolean hasPressedParry = false;


    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex)
    {
        if (KeyBindRegistry.ParryAbilityKey.consumeClick() && hasParry && !hasPressedParry) {
            hasPressedParry = true;
            System.out.println("Parry Activado.");
        }

        if (hasPressedParry) {
            parryDuration--;
            if (parryDuration <= 0) {
                cooldown = maxCooldown;
                hasParry = false;
                hasPressedParry = false;
                System.out.println("Parry Desactivado. Iniciando Cooldown.");
            }
        }

        if (!hasParry) {
            cooldown--;
            if (cooldown <= 0) {
                hasParry = true;
                parryDuration = maxParryDuration;
                System.out.println("Cooldown Terminado.");
            }
        }
    }
}
