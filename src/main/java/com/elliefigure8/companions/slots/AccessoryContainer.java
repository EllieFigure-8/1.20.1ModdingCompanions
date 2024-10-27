package com.elliefigure8.companions.slots;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container.*;
import net.minecraftforge.common.extensions.IForgeMenuType;

public class AccessoryContainer extends AbstractContainerMenu {

    public AccessoryContainer(int id, Inventory playerInventory) {
        super(ModContainers.ACCESSORY_CONTAINER.get(), id);
        SimpleContainer container = new SimpleContainer(1); // Contenedor simple con un solo slot personalizado
        this.addSlot(new AccessoriesSlot(container, 0, 80, 35)); // Usa AccessorySlot

        // Agregar los slots del inventario del jugador
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY; // Manejo de transferencia rápida
    }
}
