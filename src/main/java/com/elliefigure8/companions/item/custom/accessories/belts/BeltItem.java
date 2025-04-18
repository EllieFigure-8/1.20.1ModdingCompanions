package com.elliefigure8.companions.item.custom.accessories.belts;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.util.InventoryUtil;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;

import java.util.List;

public class BeltItem extends Item {
    public BeltItem(Properties pProperties) {super(pProperties);}

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide) {

            boolean canDodge = BeltItemUtil.getCanDodge(stack);
            int dodgeCooldown = BeltItemUtil.getDodgeCooldown(stack);

            if (!canDodge) {
                if (dodgeCooldown > 0) {
                    dodgeCooldown--;
                } else {
                    canDodge = true;
                    player.sendSystemMessage(Component.literal("Cooldown Resetted! Dodge Ready!"));
                }
            }

            BeltItemUtil.setCanDodge(stack, canDodge);
            BeltItemUtil.setDodgeCooldown(stack, dodgeCooldown);
        }
    }

    private void addDataToBelt(Player player, int dodgeCooldown)
    {
        ItemStack beltEquipped = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.BLACK_BELT.get()));

        CompoundTag data = new CompoundTag();
        data.putString("tooltip.companionsmod.add_data_to_belt.tooltip", "Dodge Cooldown: " + dodgeCooldown / 20 + " Seconds.");

        beltEquipped.setTag(data);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced ) {

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

            /* else if (pStack.getItem() == ModItems.BLACK_BELT.get())
            {
                pTooltipComponents.add(Component.translatable("tooltip.companionsmod.black_belt.tooltip"));

                if (pStack.hasTag() && pStack.getTag().contains("tooltip.companionsmod.add_data_to_belt.tooltip") && dodgeCooldown > 0) {
                    String currentDodgeCooldown = pStack.getTag().getString("tooltip.companionsmod.add_data_to_belt.tooltip");
                    pTooltipComponents.add(Component.literal(currentDodgeCooldown).withStyle(ChatFormatting.RED));
                }
            }*/
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

