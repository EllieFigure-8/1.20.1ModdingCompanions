package com.elliefigure8.companions.item.custom.dodges;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BeltItem extends Item {
    public BeltItem(Properties pProperties) {super(pProperties);}

    public static boolean canDodge;
    public static int dodgeCooldown = 0;

    public static final int getMaxParryCooldown = 300;
    public static int Parrycooldown = 0;
    public static final int maxParryDuration = 20;
    public static int parryDuration = 20;
    public static boolean hasParry = false;
    public static boolean hasPressedParry = false;
    public static boolean hasParriedAttack = false;

    public static boolean RedBeltParryUsed = false;
    public static int RedBeltParrySharedCooldown = 300;

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex)
    {
        if (!level.isClientSide) {
            boolean beltCanParry = player.getInventory().contains(new ItemStack(ModItems.RED_BELT.get())) ||
                    player.getInventory().contains(new ItemStack(ModItems.BLACK_BELT.get()));

            if (KeyBindRegistry.ParryAbilityKey.consumeClick() && beltCanParry && hasParry && !hasPressedParry) {
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_ACTIVATED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                hasPressedParry = true;
                System.out.println("Parry Activado.");
            }

            if (hasPressedParry) {
                parryDuration--;
                if (!hasParriedAttack && parryDuration <= 0) {
                    Parrycooldown = getMaxParryCooldown;
                    hasParry = false;
                    hasPressedParry = false;
                    System.out.println("Parry Desactivado. Iniciando Cooldown.");
                } else if (hasParriedAttack) {
                    Parrycooldown = getMaxParryCooldown;
                    parryDuration = maxParryDuration;
                    hasParry = false;
                    hasPressedParry = false;
                    hasParriedAttack = false;
                    System.out.println("Hiciste Parry. Cancelando Parry Duration.");
                }
            }

            if (!hasParry) {
                Parrycooldown--;
                if (Parrycooldown <= 0) {
                    hasParry = true;
                    parryDuration = maxParryDuration;
                    player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_RECHARGED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                    System.out.println("Cooldown Terminado.");
                }
            }

            if (!RedBeltParryUsed && !level.isClientSide && !canDodge) {
                if (dodgeCooldown > 0) {
                    dodgeCooldown--;
                } else {
                    canDodge = true;
                    player.sendSystemMessage(Component.literal("Cooldown Resetted! Dodge Ready!"));
                }
            }

            if (RedBeltParryUsed) {
                RedBeltParrySharedCooldown--;
                if (RedBeltParrySharedCooldown <= 0) {
                    RedBeltParryUsed = false;
                    RedBeltParrySharedCooldown = 300;
                    System.out.println("RedBelt Dodge and Parry can be used!");
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(Screen.hasShiftDown())
        {
            if (pStack.getItem() == ModItems.WHITE_BELT.get())
            {
            pTooltipComponents.add(Component.translatable("tooltip.companionsmod.white_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.YELLOW_BELT.get())
            {
            pTooltipComponents.add(Component.translatable("tooltip.companionsmod.yellow_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.GREEN_BELT.get())
            {
            pTooltipComponents.add(Component.translatable("tooltip.companionsmod.green_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.BLUE_BELT.get())
            {
            pTooltipComponents.add(Component.translatable("tooltip.companionsmod.blue_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.RED_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.red_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.tooltip.shift"));
            }
        }
        else
        {
            if (pStack.getItem() == ModItems.WHITE_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.white_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.YELLOW_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.yellow_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.GREEN_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.green_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.BLUE_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.blue_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.RED_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.red_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.tooltip"));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

