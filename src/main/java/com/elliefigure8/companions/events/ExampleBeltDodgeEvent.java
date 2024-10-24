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
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleBeltItem) {

                CompoundTag nbt = stack.getOrCreateTag();
                boolean canDodge = nbt.getBoolean("CanDodge");
                int dodgeCooldown = nbt.getInt("DodgeCooldown");
                int exampleRoundedDamage = nbt.getInt("ExampleRoundedDamage");

                if (canDodge)
                {
                    float damage = event.getAmount();
                    exampleRoundedDamage = (int) Math.ceil(damage);
                    event.setAmount(0);

                    dodgeCooldown = ExampleBeltItem.calculateCooldown(exampleRoundedDamage);
                    canDodge = false;
                    player.sendSystemMessage(Component.literal("Cooldown: " + dodgeCooldown / 20 + " seconds."));

                    nbt.putBoolean("CanDodge", canDodge);
                    nbt.putInt("DodgeCooldown", dodgeCooldown);
                    nbt.putInt("ExampleRoundedDamage", exampleRoundedDamage);
                    stack.setTag(nbt);

                    break;
                }
            }
        }
    }
}
