package com.elliefigure8.companions.item.custom.accessories.wings;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;

public class LeafElytrasItem extends Item {

    public LeafElytrasItem(Properties pProperties) {
        super(pProperties);
    }

    private static boolean hasSlowFall = true;

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.options.keyJump.consumeClick() && !player.onGround() && hasSlowFall) {
            MobEffectInstance LeafElytraSlowFall = new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, false, false);
            player.addEffect(LeafElytraSlowFall);
            hasSlowFall = false;
        }
        if (player.onGround()) {hasSlowFall = true;}
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }
}

