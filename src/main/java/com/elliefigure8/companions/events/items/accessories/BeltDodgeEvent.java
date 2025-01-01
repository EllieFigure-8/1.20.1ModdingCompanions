package com.elliefigure8.companions.events.items.accessories;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import com.elliefigure8.companions.util.CooldownsUtil;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BeltDodgeEvent
{
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("Evento de inicio de sesión detectado.");

        Player player = event.getEntity();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof BeltItem) {
                BeltItemUtil.setDodgeCooldown(stack, 0);
                BeltItemUtil.setCanDodge(stack, true);
                System.out.println("Cooldown reiniciado. Dodge está listo al entrar al mundo.");
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        boolean hasWhiteBelt = false;
        boolean hasGreenBelt = false;
        boolean hasBlueBelt = false;

        ItemStack activeBelt = ItemStack.EMPTY;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof BeltItem) {
                if (stack.getItem() == ModItems.BLUE_BELT.get()) {
                    hasBlueBelt = true;
                    activeBelt = stack;
                    break;
                } else if (stack.getItem() == ModItems.GREEN_BELT.get()) {
                    hasGreenBelt = true;
                    activeBelt = stack;
                } else if (stack.getItem() == ModItems.YELLOW_BELT.get()) {
                    activeBelt = stack;
                } else if (stack.getItem() == ModItems.WHITE_BELT.get() && activeBelt == ItemStack.EMPTY) {
                    hasWhiteBelt = true;
                    activeBelt = stack;
                }
            }
        }

        if (activeBelt.isEmpty()) return;

        boolean canDodge = BeltItemUtil.getCanDodge(activeBelt);

        if (canDodge) {
            if (hasWhiteBelt && Math.random() >= 0.5){player.sendSystemMessage(Component.literal("You didn't dodge the attack.")); return;}

            float damage = event.getAmount();
            int roundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            int calculatedCooldown = CooldownsUtil.calculateCooldown(roundedDamage);

            if (hasGreenBelt) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0));
            }

            if (hasBlueBelt) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0));
            }

            BeltItemUtil.setCanDodge(activeBelt, false);
            BeltItemUtil.setDodgeCooldown(activeBelt, calculatedCooldown);

            player.sendSystemMessage(Component.translatable("item.companionsmod.dodge_belt.dodge_activated"));
            player.sendSystemMessage(Component.literal("Cooldown: " + calculatedCooldown / 20 + " seconds."));
        }
    }
}


