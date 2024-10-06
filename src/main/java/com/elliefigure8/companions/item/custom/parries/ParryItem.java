
package com.elliefigure8.companions.item.custom.parries;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
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
    public static boolean hasParriedAttack = false;


    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex)
    {
        if (KeyBindRegistry.ParryAbilityKey.consumeClick() && hasParry && !hasPressedParry) {
            player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_ACTIVATED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
            hasPressedParry = true;
            System.out.println("Parry Activado.");
        }

        if (hasPressedParry) {
            parryDuration--;
            if (!ParryItem.hasParriedAttack)
            {
                if (parryDuration <= 0) {
                    cooldown = maxCooldown;
                    hasParry = false;
                    hasPressedParry = false;
                    System.out.println("Parry Desactivado. Iniciando Cooldown.");
                }
            }
            else
            {
                hasPressedParry = false;
            } //
        }

        if (!hasParry) {
            cooldown--;
            if (cooldown <= 0) {
                hasParry = true;
                hasParriedAttack = false;
                parryDuration = maxParryDuration;
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_RECHARGED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                System.out.println("Cooldown Terminado.");
            }
        }
    }
}
