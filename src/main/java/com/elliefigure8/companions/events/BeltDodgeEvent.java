package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.dodges.YellowBeltItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.elliefigure8.companions.item.custom.dodges.YellowBeltItem.canDodge;

public class BeltDodgeEvent
{

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {

        if (!(event.getEntity() instanceof Player player)) return;
        boolean hasYellowBelt = player.getInventory().contains(new ItemStack(ModItems.YELLOW_BELT.get()));
        if (!hasYellowBelt) return;

        if (YellowBeltItem.canDodge)
        {
            float damage = event.getAmount();
            int roundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            YellowBeltItem.dodgeCooldown = YellowBeltItem.calculateCooldown(roundedDamage);
            canDodge = false;
            player.sendSystemMessage(Component.translatable("item.companionsmod.yellow_belt.dodge_activated"));
            player.sendSystemMessage(Component.literal("Cooldown: " + YellowBeltItem.dodgeCooldown / 20 + " seconds."));
        }
    }
}
