package com.elliefigure8.companions.item.custom.dodges;

import net.minecraft.nbt.CompoundTag;
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
            // Obtener el NBT del ItemStack
            CompoundTag nbt = stack.getOrCreateTag();

            // Leer el estado actual del NBT
            boolean canDodge = nbt.getBoolean("CanDodge");
            int dodgeCooldown = nbt.getInt("DodgeCooldown");

            // Lógica de cooldown
            if (!canDodge) {
                if (dodgeCooldown > 0) {
                    dodgeCooldown--;
                } else {
                    canDodge = true;
                    player.sendSystemMessage(Component.literal("Cooldown Resetted! Dodge Ready!"));
                }
            }

            // Guardar el estado actualizado en el NBT
            nbt.putBoolean("CanDodge", canDodge);
            nbt.putInt("DodgeCooldown", dodgeCooldown);
            stack.setTag(nbt); // Aplicar el NBT actualizado al ItemStack
        }
    }

    public static int calculateCooldown(int exampleRoundedDamage) {
        int ticksCooldown = exampleRoundedDamage * 270;
        if (ticksCooldown > 1800) { ticksCooldown = 1800; }
        if (ticksCooldown < 300) { ticksCooldown = 300; }
        return ticksCooldown;
    }
}
