package com.elliefigure8.companions.slots;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class AccessoryMenuProvider implements MenuProvider {
    //Define título de inventario
    //Proporciona lógica del contenedor para que Forge abra correctamente el inventario

    @Override
    public Component getDisplayName() {
        return Component.literal("Accessory Inventory");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new AccessoryContainer(id, playerInventory);
    }
}
