package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.custom.dodges.ExampleBeltItem;
import net.minecraft.nbt.CompoundTag;
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

                CompoundTag nbt = stack.getOrCreateTag();

                nbt.putInt("DodgeCooldown", 0);
                nbt.putBoolean("CanDodge", true);
                System.out.println("Cooldown reiniciado. Dodge está listo al entrar al mundo.");

                stack.setTag(nbt);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {

                CompoundTag nbt = stack.getOrCreateTag();
                boolean canDodge = nbt.getBoolean("CanDodge");

                if (canDodge)
                {
                    float damage = event.getAmount();
                    int exampleRoundedDamage = (int) Math.ceil(damage);
                    event.setAmount(0);

                    int calculatedCooldown = ExampleBeltItem.calculateCooldown(exampleRoundedDamage);

                    player.sendSystemMessage(Component.literal("Cooldown: " + calculatedCooldown / 20 + " seconds."));

                    nbt.putBoolean("CanDodge", false);
                    nbt.putInt("DodgeCooldown", calculatedCooldown);
                    stack.setTag(nbt);

                    break;
                }
            }
        }
    }
}
