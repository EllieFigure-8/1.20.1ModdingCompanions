package com.elliefigure8.companions.events.items.accessories;

import com.elliefigure8.companions.item.custom.parries.ExampleParryItem;
import com.elliefigure8.companions.sound.ModSounds;
import com.elliefigure8.companions.util.items.ParryItemUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExampleParryEvent
{
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("Evento de inicio de sesión detectado.");

        Player player = event.getEntity();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleParryItem) {

                CompoundTag ExampleParryNBT = stack.getOrCreateTag();

                ExampleParryNBT.putInt("Parrycooldown", 0);
                ExampleParryNBT.putBoolean("hasParry", true);
                System.out.println("Cooldown reiniciado. Parry está listo al entrar al mundo.");

                stack.setTag(ExampleParryNBT);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ExampleParryItem) {

                final int getMaxParryCooldown = 300;
                int parryDuration = ParryItemUtil.getParryDuration(stack);
                boolean hasPressedParry = ParryItemUtil.getHasPressedParry(stack);

                if (parryDuration >= 0 && hasPressedParry) {
                    player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.DAMAGE_PARRIED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                    System.out.println("Daño Parrieado.");
                    event.setAmount(0);

                    ParryItemUtil.setParryCooldown(stack, getMaxParryCooldown);
                    ParryItemUtil.setHasParry(stack, false);
                    ParryItemUtil.setHasPressedParry(stack, false);
                    ParryItemUtil.setHasParriedAttack(stack, true);
                }
                break;
            }

        }
    }
}



/*
                boolean hasRedBelt = player.getInventory().contains(new ItemStack(ModItems.RED_BELT.get()));boolean hasBlackBelt = player.getInventory().contains(new ItemStack(ModItems.BLACK_BELT.get()));
                boolean hasParryItemTheItem = player.getInventory().contains(new ItemStack(ModItems.PARRY_ITEM.get()));

                hasRedBelt || hasBlackBelt ||
                boolean hasParryItem =  hasParryItemTheItem;
                */




 /*
                         if (hasRedBelt)
                        {
                            RedBeltParryUsed = true;
                            System.out.println("RedBelt Shared Cooldown Activated.");
                        }
                        */