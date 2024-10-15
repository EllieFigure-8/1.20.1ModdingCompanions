package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.dodges.ExampleBeltItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExampleBeltDodgeEvent {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {
                // Obtener el NBT del ItemStack
                CompoundTag nbt = stack.getOrCreateTag();

                // Leer el estado del NBT
                boolean canDodge = nbt.getBoolean("CanDodge");
                int dodgeCooldown = nbt.getInt("DodgeCooldown");
                int exampleRoundedDamage = nbt.getInt("ExampleRoundedDamage");

                if (canDodge) {
                    float damage = event.getAmount();
                    exampleRoundedDamage = (int) Math.ceil(damage); // Calcular el daño redondeado
                    event.setAmount(0); // Cancelar el daño

                    // Calcular y establecer el nuevo cooldown
                    dodgeCooldown = ExampleBeltItem.calculateCooldown(exampleRoundedDamage);
                    canDodge = false; // Desactivar la capacidad de dodge
                    player.sendSystemMessage(Component.literal("Cooldown: " + dodgeCooldown / 20 + " seconds."));

                    // Guardar el estado actualizado en el NBT
                    nbt.putBoolean("CanDodge", canDodge);
                    nbt.putInt("DodgeCooldown", dodgeCooldown);
                    nbt.putInt("ExampleRoundedDamage", exampleRoundedDamage);
                    stack.setTag(nbt); // Aplicar el NBT actualizado al ItemStack

                    break; // Salir del bucle si se ha activado el dodge
                }
            }
        }
    }
}
