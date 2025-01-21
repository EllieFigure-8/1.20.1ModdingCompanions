package com.elliefigure8.companions.slots;

import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import com.elliefigure8.companions.item.custom.accessories.dashes.VeilItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class AccessoryContainer extends AbstractContainerMenu {
    //Maneja inventario como Items, también muestra la mochila y el cinturón.

    private final SimpleContainer container;

    public AccessoryContainer(int id, Inventory playerInventory) {
        super(ModContainers.ACCESSORY_CONTAINER.get(), id);

        this.container = new SimpleContainer(2) {
            @Override
            public void setChanged() {
                super.setChanged();
                saveAccessoryData(playerInventory.player);  // Guarda los datos cuando cambian los contenidos
            }
        };

        // Slot para BeltItem
        this.addSlot(new AccessoriesSlot(container, 0, 80, 35, BeltItem.class));
        // Slot para VeilItem
        this.addSlot(new AccessoriesSlot(container, 1, 100, 35, VeilItem.class));


        // Agregar slots del inventario del jugador (como antes)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        loadAccessoryData(playerInventory.player);  // Cargar el ítem al abrir
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
    public void saveAccessoryData(Player player) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);

            if (!stack.isEmpty()) {
                // Obtener el ResourceLocation desde el Item
                ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(stack.getItem());

                // Si el ResourceLocation no es nulo, guardamos el ID del ítem y la cantidad
                if (itemId != null) {
                    player.getPersistentData().putString("accessoryItemID_" + i, itemId.toString());
                    player.getPersistentData().putInt("accessoryItemCount_" + i, stack.getCount());
                }
            }
        }
    }

    public void loadAccessoryData(Player player) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            String itemId = player.getPersistentData().getString("accessoryItemID_" + i);
            int itemCount = player.getPersistentData().getInt("accessoryItemCount_" + i);

            if (!itemId.isEmpty() && itemCount > 0) {
                // Usar el ResourceLocation para obtener el ítem
                ResourceLocation resourceLocation = new ResourceLocation(itemId);
                Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);

                if (item != null) {
                    // Crear el ItemStack con el ítem cargado
                    ItemStack stack = new ItemStack(item, itemCount);
                    container.setItem(i, stack); // Coloca el ítem cargado en el slot correspondiente
                }
            }
        }
    }
}
