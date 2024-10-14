package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BeltDodgeEvent
{
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        boolean hasBeltEquipped = false;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof BeltItem) {
                hasBeltEquipped = true;
                break;
            }
        }
        if (!hasBeltEquipped) return;

        boolean hasWhiteBelt = player.getInventory().contains(new ItemStack(ModItems.WHITE_BELT.get()));
        boolean hasYellowBelt = player.getInventory().contains(new ItemStack(ModItems.YELLOW_BELT.get()));
        boolean hasGreenBelt = player.getInventory().contains(new ItemStack(ModItems.GREEN_BELT.get()));
        boolean hasBlueBelt = player.getInventory().contains(new ItemStack(ModItems.BLUE_BELT.get()));
        boolean hasRedBelt = player.getInventory().contains(new ItemStack(ModItems.RED_BELT.get()));
        boolean hasBlackBelt = player.getInventory().contains(new ItemStack(ModItems.BLACK_BELT.get()));

        //boolean hasDodgeBelt = hasYellowBelt || hasGreenBelt || hasBlueBelt || hasRedBelt || hasBlackBelt;
        //boolean hasParryItem = hasRedBelt || hasBlackBelt;

        if (hasWhiteBelt && Math.random() >= 0.5) return;
        //if (!hasDodgeBelt) return;

        //hasParryItem &&
        if (BeltItem.parryDuration > 0 && BeltItem.hasPressedParry) {
            player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.DAMAGE_PARRIED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
            System.out.println("Daño Parrieado.");
            event.setAmount(0);
            BeltItem.hasParriedAttack = true;
            if (hasRedBelt) {
                BeltItem.RedBeltParryUsed = true;
                System.out.println("RedBelt Shared Cooldown Activated.");
            }
        } else if (BeltItem.canDodge && !BeltItem.hasPressedParry) {
            float damage = event.getAmount();
            int roundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            if (hasGreenBelt) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0));
            }

            if (hasBlueBelt || hasRedBelt || hasBlackBelt) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0));
            }

            BeltItem.dodgeCooldown = BeltItem.calculateCooldown(roundedDamage);
            BeltItem.canDodge = false;
            player.sendSystemMessage(Component.translatable("item.companionsmod.dodge_belt.dodge_activated"));
            player.sendSystemMessage(Component.literal("Cooldown: " + BeltItem.dodgeCooldown / 20 + " seconds."));
        }
    }
}
