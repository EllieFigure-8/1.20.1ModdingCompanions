/* package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import com.elliefigure8.companions.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ParryItemEvent
{
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        boolean hasRedBelt = player.getInventory().contains(new ItemStack(ModItems.RED_BELT.get()));
        boolean hasBlackBelt = player.getInventory().contains(new ItemStack(ModItems.BLACK_BELT.get()));
        boolean hasParryItemTheItem = player.getInventory().contains(new ItemStack(ModItems.PARRY_ITEM.get()));

        boolean hasParryItem = hasRedBelt || hasBlackBelt || hasParryItemTheItem;

        if (!BeltItem.hasParry) return;

        if (hasParryItem)
        {
            if (BeltItem.parryDuration > 0 && BeltItem.hasPressedParry)
            {
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.DAMAGE_PARRIED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                System.out.println("Daño Parrieado.");
                event.setAmount(0);
                BeltItem.cooldown = BeltItem.maxCooldown;
                BeltItem.hasParry = false;
                BeltItem.hasParriedAttack = true;
                if (hasRedBelt)
                {
                    BeltItem.RedBeltParryUsed = true;
                    System.out.println("RedBelt Shared Cooldown Activated.");
                }
            }
        }
    }
} */
