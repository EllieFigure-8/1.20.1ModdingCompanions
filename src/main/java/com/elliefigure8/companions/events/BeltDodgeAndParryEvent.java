package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.dodges.BeltWithParryItem;
import com.elliefigure8.companions.sound.ModSounds;
import com.elliefigure8.companions.util.CooldownsUtil;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import com.elliefigure8.companions.util.items.ParryItemUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.damagesource.DamageSource;

import java.util.List;

public class BeltDodgeAndParryEvent {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("Evento de inicio de sesión detectado.");

        Player player = event.getEntity();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof BeltWithParryItem) {

                ParryItemUtil.setParryDuration(stack, ParryItemUtil.DEFAULT_PARRY_DURATION);
                ParryItemUtil.setParryCooldown(stack, ParryItemUtil.DEFAULT_PARRY_COOLDOWN);
                ParryItemUtil.setHasParry(stack, ParryItemUtil.DEFAULT_HAS_PARRY);
                ParryItemUtil.setHasPressedParry(stack, ParryItemUtil.DEFAULT_HAS_PRESSED_PARRY);
                ParryItemUtil.setHasParriedAttack(stack, ParryItemUtil.DEFAULT_HAS_PARRIED_ATTACK);

                BeltItemUtil.setDodgeCooldown(stack, BeltItemUtil.DEFAULT_DODGE_COOLDOWN);
                BeltItemUtil.setCanDodge(stack, BeltItemUtil.DEFAULT_CAN_DODGE);
                System.out.println("Cooldown reiniciado. Dodge está listo al entrar al mundo.");
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        boolean hasRedBelt = false;
        boolean hasBlackBelt = false;
        boolean hasGoldenBelt = false;
        System.out.println("This works?.");

        ItemStack activeBelt = ItemStack.EMPTY;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof BeltWithParryItem) {
                if (stack.getItem() == ModItems.GOLDEN_BELT.get()) {
                    hasGoldenBelt = true;
                    activeBelt = stack;
                    break;
                } else if (stack.getItem() == ModItems.BLACK_BELT.get()) {
                    hasBlackBelt = true;
                    activeBelt = stack;
                } else if (stack.getItem() == ModItems.RED_BELT.get() && activeBelt == ItemStack.EMPTY) {
                    hasRedBelt = true;
                    activeBelt = stack;
                }
            }
        }

        if (activeBelt.isEmpty()) return;

        boolean canDodge = BeltItemUtil.getCanDodge(activeBelt);

        final int getMaxParryCooldown = 300;
        int parryDuration = ParryItemUtil.getParryDuration(activeBelt);
        boolean hasPressedParry = ParryItemUtil.getHasPressedParry(activeBelt);

        if (hasPressedParry)
        {
            if (parryDuration >= 0) {
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.DAMAGE_PARRIED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                System.out.println("Daño Parrieado.");
                event.setAmount(0);

                if (hasRedBelt) {
                    BeltItemUtil.setCanDodge(activeBelt, false);
                    BeltItemUtil.setDodgeCooldown(activeBelt, getMaxParryCooldown);
                    System.out.println("RedBelt Cooldown Shared by: Parry");
                }
                ParryItemUtil.setHasParry(activeBelt, false);
                ParryItemUtil.setParryCooldown(activeBelt, getMaxParryCooldown);
                ParryItemUtil.setHasParriedAttack(activeBelt, true);
            }
        }
        else
        {
            if (canDodge)
            {
            float damage = event.getAmount();
            int roundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            int calculatedCooldown = CooldownsUtil.calculateCooldown(roundedDamage);

            if (hasRedBelt) {
                ParryItemUtil.setHasParry(activeBelt, false);
                ParryItemUtil.setParryCooldown(activeBelt, calculatedCooldown);
                System.out.println("RedBelt Cooldown Shared by: Dodge");
            }

            if (hasBlackBelt) {}
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0));

            BeltItemUtil.setCanDodge(activeBelt, false);
            BeltItemUtil.setDodgeCooldown(activeBelt, calculatedCooldown);

            player.sendSystemMessage(Component.translatable("item.companionsmod.dodge_belt.dodge_activated"));
            player.sendSystemMessage(Component.literal("Cooldown: " + calculatedCooldown / 20 + " seconds."));
            }
        }
    }
}
// windRingEffect(player, 5);