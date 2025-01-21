package com.elliefigure8.companions.item.custom.accessories.wings;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AuroritaBoostersItem extends Item
{
    public AuroritaBoostersItem(Properties pProperties) {
        super(pProperties);
    }

    private static boolean canLeap;


    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex)
    {
        //player.fallDistance = 0;
        handleLeap(player);

        if (!canLeap) {if (player.onGround()) {canLeap = true;}}

        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }


    public static void handleLeap(Player player)
    {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.keyJump.consumeClick() && !player.onGround() && canLeap)
        {
            executeDoubleJump(player);
        }
    }

    public static void executeDoubleJump(Player player)
    {
        Vec3 currentVelocity = player.getDeltaMovement();
        double verticalVelocity = 0.5;

        if (player.isSprinting()) {
            Vec3 lookDirection = player.getLookAngle();

            Vec3 sprintBoost = new Vec3(lookDirection.x * 0.2, 0, lookDirection.z * 0.2);

            Vec3 leapVelocity = new Vec3(
                    currentVelocity.x + sprintBoost.x,
                    verticalVelocity,
                    currentVelocity.z + sprintBoost.z
            );
            player.setDeltaMovement(leapVelocity);
        } else {
            Vec3 leapVelocity = new Vec3(currentVelocity.x, verticalVelocity, currentVelocity.z);
            player.setDeltaMovement(leapVelocity);
        }

        player.hurtMarked = true;
        canLeap = false;
    }
}
