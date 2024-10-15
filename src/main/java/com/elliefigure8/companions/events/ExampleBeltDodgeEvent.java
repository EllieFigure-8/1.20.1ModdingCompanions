package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.dodges.ExampleBeltItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExampleBeltDodgeEvent
{
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (!(event.getEntity() instanceof Player player)) return;

        boolean hasExampleBeltEquipped = false;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {
                hasExampleBeltEquipped = true;
                break;
            }
        }
        if (!hasExampleBeltEquipped) return;

        if (ExampleBeltItem.canDodge)
        {
            float damage = event.getAmount();
            int exampleRoundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            ExampleBeltItem.dodgeCooldown = ExampleBeltItem.calculateCooldown(exampleRoundedDamage);
            ExampleBeltItem.canDodge = false;
            player.sendSystemMessage(Component.literal("Cooldown: " + ExampleBeltItem.dodgeCooldown / 20 + " seconds."));
        }
    }
}
