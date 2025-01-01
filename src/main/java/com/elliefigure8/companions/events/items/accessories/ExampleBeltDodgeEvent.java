package com.elliefigure8.companions.events.items.accessories;

import com.elliefigure8.companions.item.custom.accessories.belts.ExampleBeltItem;
import com.elliefigure8.companions.util.CooldownsUtil;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExampleBeltDodgeEvent {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("Evento de inicio de sesión detectado.");

        Player player = event.getEntity();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {
                BeltItemUtil.setDodgeCooldown(stack, 0);
                BeltItemUtil.setCanDodge(stack, true);
                System.out.println("Cooldown reiniciado. Dodge está listo al entrar al mundo.");
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {

                boolean canDodge = BeltItemUtil.getCanDodge(stack);

                if (canDodge) {
                    float damage = event.getAmount();
                    int exampleRoundedDamage = (int) Math.ceil(damage);
                    event.setAmount(0);

                    int calculatedCooldown = CooldownsUtil.calculateCooldown(exampleRoundedDamage);

                    player.sendSystemMessage(Component.literal("Cooldown: " + calculatedCooldown / 20 + " seconds."));

                    BeltItemUtil.setCanDodge(stack, false);
                    BeltItemUtil.setDodgeCooldown(stack, calculatedCooldown);
                    break;
                }
            }
        }
    }
}
