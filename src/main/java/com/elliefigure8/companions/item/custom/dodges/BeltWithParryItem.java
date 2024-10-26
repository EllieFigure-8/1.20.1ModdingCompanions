package com.elliefigure8.companions.item.custom.dodges;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.sound.ModSounds;
import com.elliefigure8.companions.util.InventoryUtil;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import com.elliefigure8.companions.util.items.ParryItemUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BeltWithParryItem extends Item
{
    public BeltWithParryItem(Properties pProperties) {super(pProperties);}

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide)
        {
            boolean canDodge = BeltItemUtil.getCanDodge(stack);
            int dodgeCooldown = BeltItemUtil.getDodgeCooldown(stack);

            final int getMaxParryCooldown = 300;
            final int maxParryDuration = 60;
            int parryDuration = ParryItemUtil.getParryDuration(stack);
            int Parrycooldown = ParryItemUtil.getParryCooldown(stack);
            boolean hasParry = ParryItemUtil.getHasParry(stack);
            boolean hasPressedParry = ParryItemUtil.getHasPressedParry(stack);
            boolean hasParriedAttack = ParryItemUtil.getHasParriedAttack(stack);

            if (KeyBindRegistry.ParryAbilityKey.consumeClick() && hasParry && !hasPressedParry)
            {
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_ACTIVATED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                hasPressedParry = true;
                System.out.println("Parry Activado.");
            }

            if (hasPressedParry)
            {
                parryDuration--;
                if (hasParriedAttack)
                {
                    parryDuration = 0;
                    hasParriedAttack = false;
                    System.out.println("Hiciste Parry. Cancelando Parry Duration.");
                }
                if (parryDuration <= 0)
                {
                    Parrycooldown = getMaxParryCooldown;
                    parryDuration = maxParryDuration;
                    hasParry = false;
                    hasPressedParry = false;
                    System.out.println("Parry Desactivado. Iniciando Cooldown.");
                }
            }

            if (!hasParry)
            {
                Parrycooldown--;
                if (Parrycooldown <= 0) {
                    hasParry = true;
                    parryDuration = maxParryDuration;
                    player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_RECHARGED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                    System.out.println("Cooldown Terminado.");
                }
            }

            if (!canDodge) {
                if (dodgeCooldown > 0) {
                    dodgeCooldown--;
                    addDataToBelt(player, stack);
                    BeltItemUtil.setDodgeCooldown(stack, dodgeCooldown);
                } else {
                    canDodge = true;
                    player.sendSystemMessage(Component.literal("Cooldown Resetted! Dodge Ready!"));
                }
            }

            ParryItemUtil.setParryDuration(stack, parryDuration);
            ParryItemUtil.setParryCooldown(stack, Parrycooldown);
            ParryItemUtil.setHasParry(stack, hasParry);
            ParryItemUtil.setHasPressedParry(stack, hasPressedParry);
            ParryItemUtil.setHasParriedAttack(stack, hasParriedAttack);

            BeltItemUtil.setCanDodge(stack, canDodge);

        }
    }

    private void addDataToBelt(Player player, ItemStack stack) {
        ItemStack beltEquipped = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.BLACK_BELT.get()));

        CompoundTag data = new CompoundTag();
        int dodgeCooldown = BeltItemUtil.getDodgeCooldown(stack);
        if (dodgeCooldown >= 1)
        {
            data.putString("tooltip.companionsmod.add_data_to_belt.tooltip", "Dodge Cooldown: " + dodgeCooldown / 20 + " Seconds.");
            beltEquipped.setTag(data);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        int dodgeCooldown = BeltItemUtil.getDodgeCooldown(pStack);

        if(Screen.hasShiftDown()) //Item Description
        {
            if (pStack.getItem() == ModItems.RED_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.red_belt.tooltip.shift"));
            }
            else if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.tooltip.shift"));
            }
        }
        else if (Screen.hasControlDown()) //Values Info (Cooldown, Charges, Stats, Etc)
        {
            if (pStack.getItem() == ModItems.BLACK_BELT.get() && pStack.hasTag())
            {
                String currentDodgeCooldown = pStack.getTag().getString("tooltip.companionsmod.add_data_to_belt.tooltip");
                pTooltipComponents.add(Component.literal(currentDodgeCooldown));
            }
        }
        else if (Screen.hasAltDown()) //Lore
        {
            if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.lore"));
            }
        }

        else
        {
            if (pStack.getItem() == ModItems.RED_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.red_belt.tooltip"));
            }
            else if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.tooltip"));

                if (pStack.hasTag() && pStack.getTag().contains("tooltip.companionsmod.add_data_to_belt.tooltip") && dodgeCooldown > 0) {
                    String currentDodgeCooldown = pStack.getTag().getString("tooltip.companionsmod.add_data_to_belt.tooltip");
                    pTooltipComponents.add(Component.literal(currentDodgeCooldown).withStyle(ChatFormatting.RED));
                }
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

//if (pStack.hasTag() && pStack.getTag().contains("tooltip.companionsmod.add_data_to_belt.tooltip") && dodgeCooldown > 0) {
//                    String currentDodgeCooldown = pStack.getTag().getString("tooltip.companionsmod.add_data_to_belt.tooltip");
//                    pTooltipComponents.add(Component.literal(currentDodgeCooldown).withStyle(ChatFormatting.RED));
//                }
