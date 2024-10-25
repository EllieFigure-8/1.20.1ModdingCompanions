package com.elliefigure8.companions.item.custom.dodges;

import com.elliefigure8.companions.util.items.BeltItemUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExampleBeltItem extends Item {

    public ExampleBeltItem(Properties pProperties) {
        super(pProperties);
    }

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
}
