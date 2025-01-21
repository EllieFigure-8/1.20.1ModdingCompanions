package com.elliefigure8.companions.item.custom.accessories.wings;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SteamPunkerWingsItem extends Item {

    private static int dashCooldown = 100; // 3 segundo de cooldown
    private static boolean isOnCooldown = false;

    public SteamPunkerWingsItem(Properties pProperties) {
        super(pProperties);
    }

    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        resetDashState();
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {

        handleDash(player);

        if (isOnCooldown) {
            dashCooldown--;
            if (dashCooldown <= 0) {
                resetCooldown();
            }
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    public static void handleDash(Player player) {
        if (KeyBindRegistry.DashAbilityKey.consumeClick() && !isOnCooldown)
        {
            executeDash(player);
            startCooldown();
        }
    }

    private static void executeDash(Player player) {

        Vec3 direction = player.getLookAngle();
        double dashSpeed = 2.5;
        player.setDeltaMovement(direction.scale(dashSpeed));
        player.hurtMarked = true;

        System.out.println("Dash executed. Speed = " + dashSpeed + ", Direction = " + direction);
    }

    private static void startCooldown() {
        dashCooldown = 100;
        isOnCooldown = true;
        System.out.println("Cooldown Started.");
    }
    private static void resetCooldown(){
        isOnCooldown = false;
        dashCooldown = 100;
        System.out.println("Cooldown Resetted.");
    }

    private static void resetDashState() {
        dashCooldown = 100;
        isOnCooldown = false;
        System.out.println("Dash state reset on login.");
    }

}