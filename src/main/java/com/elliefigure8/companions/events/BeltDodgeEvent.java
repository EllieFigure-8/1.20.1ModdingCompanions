package com.elliefigure8.companions.events;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.elliefigure8.companions.item.custom.dodges.BeltItem.canDodge;

public class BeltDodgeEvent
{

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (!(event.getEntity() instanceof Player player)) return;
        boolean hasWhiteBelt = player.getInventory().contains(new ItemStack(ModItems.WHITE_BELT.get()));
        boolean hasYellowBelt = player.getInventory().contains(new ItemStack(ModItems.YELLOW_BELT.get()));
        boolean hasGreenBelt = player.getInventory().contains(new ItemStack(ModItems.GREEN_BELT.get()));
        boolean hasBlueBelt = player.getInventory().contains(new ItemStack(ModItems.BLUE_BELT.get()));
        boolean hasRedBelt = player.getInventory().contains(new ItemStack(ModItems.RED_BELT.get()));
        boolean hasBlackBelt = player.getInventory().contains(new ItemStack(ModItems.BLACK_BELT.get()));

        boolean hasDodgeBelt = hasYellowBelt || hasGreenBelt || hasBlueBelt || hasRedBelt || hasBlackBelt;

        if (hasWhiteBelt && Math.random() >= 0.5) return;
        if (!hasDodgeBelt) return;

        if (BeltItem.canDodge)
        {
            float damage = event.getAmount();
            int roundedDamage = (int) Math.ceil(damage);
            event.setAmount(0);

            if (hasGreenBelt)
            {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0));
            }

            if (hasBlueBelt || hasRedBelt || hasBlackBelt)
            {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0));
            }

            BeltItem.dodgeCooldown = BeltItem.calculateCooldown(roundedDamage);
            canDodge = false;
            player.sendSystemMessage(Component.translatable("item.companionsmod.dodge_belt.dodge_activated"));
            player.sendSystemMessage(Component.literal("Cooldown: " + BeltItem.dodgeCooldown / 20 + " seconds."));
        }
    }
}
