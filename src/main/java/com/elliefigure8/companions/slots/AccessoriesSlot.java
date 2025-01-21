package com.elliefigure8.companions.slots;

import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AccessoriesSlot extends Slot {
    //Coloca lugar visual. Define qué items pueden estar ahí.
    private final Class<? extends Item> allowedItemType;

    public AccessoriesSlot(Container pContainer, int pSlot, int pX, int pY, Class<? extends Item> allowedItemType) {
        super(pContainer, pSlot, pX, pY);
        this.allowedItemType = allowedItemType;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return allowedItemType.isInstance(pStack.getItem());
    }
}
