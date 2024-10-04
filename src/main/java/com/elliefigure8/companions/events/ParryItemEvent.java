package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.parries.ParryItem;
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

        if (!ParryItem.hasParry) return;

        if (hasParryItem)
        {
            if (ParryItem.parryDuration > 0 && ParryItem.hasPressedParry)
            {
                System.out.println("Daño Parrieado.");
                event.setAmount(0);
                ParryItem.cooldown = ParryItem.maxCooldown;
                ParryItem.hasParry = false;
                if (hasRedBelt)
                {
                    BeltItem.RedBeltParryUsed = true;
                    System.out.println("RedBelt Shared Cooldown Activated.");
                }
            }
        }
    }
}
