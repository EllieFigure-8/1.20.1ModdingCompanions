package com.elliefigure8.companions.item.custom.accessories.wings;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AquaritaFinsItem extends Item {

    public AquaritaFinsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {

        MobEffectInstance AquamarineFinsSwimSpeedBoost = new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 3, false, false);
        player.addEffect(AquamarineFinsSwimSpeedBoost);

        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }
}
