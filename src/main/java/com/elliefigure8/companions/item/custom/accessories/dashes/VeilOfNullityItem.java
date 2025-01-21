package com.elliefigure8.companions.item.custom.accessories.dashes;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.util.CooldownsUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VeilOfNullityItem extends Item {

    public VeilOfNullityItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        handleDash(player);
        CooldownsUtil.DashUpdateCooldown();
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    public static void handleDash(Player player) {
        if (KeyBindRegistry.DashAbilityKey.consumeClick() && !player.onGround())
        {
            executeDash(player);
        }
    }

    private static void executeDash(Player player) {

        if (CooldownsUtil.DashIsOnCooldown)
        {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        Vec3 CameraFront = player.getLookAngle();
        Vec3 CameraBackward = CameraFront.scale(-1);

        Vec3 up = new Vec3(0, 1, 0);
        Vec3 down = new Vec3(0, -1, 0);

        Vec3 left = up.cross(CameraFront).normalize();
        Vec3 right = CameraFront.cross(up).normalize();

        double dashSpeed = 1.88;
        //0.75 = 4 Blocks
        //1.12 = 6 Blocks
        //1.5 = 8 Blocks
        //1.88 = 10 Blocks

        Vec3 currentVelocity = player.getDeltaMovement();

        Vec3 newMovement = new Vec3(currentVelocity.x, 0, currentVelocity.z);

        boolean isMoving = false;

        if (mc.options.keyUp.isDown()) {
            Vec3 horizontalCameraFront = new Vec3(CameraFront.x, 0, CameraFront.z).normalize();
            newMovement = newMovement.add(horizontalCameraFront.scale(dashSpeed));
            isMoving = true;
        }

        if (mc.options.keyDown.isDown()) {
            newMovement = newMovement.add(CameraBackward.scale(dashSpeed));
            isMoving = true;
        }

        if (mc.options.keyLeft.isDown()) {
            newMovement = newMovement.add(left.scale(dashSpeed));
            isMoving = true;
        }

        if (mc.options.keyRight.isDown()) {
            newMovement = newMovement.add(right.scale(dashSpeed));
            isMoving = true;
        }

        if (!isMoving) {
            Vec3 horizontalCameraFront = new Vec3(CameraFront.x, 0, CameraFront.z).normalize();
            newMovement = newMovement.add(horizontalCameraFront.scale(dashSpeed));
        }

        player.setDeltaMovement(newMovement);
        player.hurtMarked = true;

        CooldownsUtil.DashActivateCooldown();
    }
}
