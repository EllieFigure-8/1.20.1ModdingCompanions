package com.elliefigure8.companions.slots;

import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AccessoryContainer extends AbstractContainerMenu {

    private final SimpleContainer container;

    public AccessoryContainer(int id, Inventory playerInventory) {
        super(ModContainers.ACCESSORY_CONTAINER.get(), id);

        this.container = new SimpleContainer(1) {
            @Override
            public void setChanged() {
                super.setChanged();
                saveAccessoryData();  // Guarda los datos cuando cambian los contenidos
            }
        };

        this.addSlot(new AccessoriesSlot(container, 0, 80, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof BeltItem;  // Solo permite `BeltItem`
            }
        });

        // Agregar slots del inventario del jugador (como antes)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        loadAccessoryData();  // Cargar el ítem al abrir
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    // Métodos para mover ítems
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            if (itemStack.getItem() instanceof BeltItem) {
                if (this.moveItemStackTo(itemStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    // Métodos para guardar y cargar el ítem
    public void saveAccessoryData() {
        ItemStack stack = container.getItem(0);
        // Aquí debes guardar el `ItemStack` en algún lugar, como en la `PlayerData`
        // Puedes hacer uso de `Player.getPersistentData()` o similar para almacenar datos específicos
    }

    public void loadAccessoryData() {
        // Aquí debes cargar el `ItemStack` desde donde lo hayas guardado previamente
        // y colocarlo en `container.setItem(0, itemStack);` si existe.
    }
}
