package com.elliefigure8.companions.item.custom.dodges;

import com.elliefigure8.companions.item.ModItems;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BeltItem extends Item {
    public BeltItem(Properties pProperties) {super(pProperties);}

    public static boolean canDodge = true;
    public static int dodgeCooldown = 0;

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex)
    {
        if(level.isClientSide)
        {
            if (!canDodge)
            {
                if (dodgeCooldown > 0)
                {
                    dodgeCooldown--;
                }
                else
                {
                    canDodge = true;
                    player.sendSystemMessage(Component.literal("Cooldown Resetted! Dodge Ready!"));
                }
            }
        }
    }

    public static int calculateCooldown(int roundedDamage)
    {
        int ticksCooldown = roundedDamage * 270;

        if (ticksCooldown > 1800) {
            ticksCooldown = 1800;
        }
        if (ticksCooldown < 300) {
            ticksCooldown = 300;
        }
        return ticksCooldown;
    }
}

