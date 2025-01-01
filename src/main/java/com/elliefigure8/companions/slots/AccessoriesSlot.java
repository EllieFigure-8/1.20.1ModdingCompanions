package com.elliefigure8.companions.slots;

import com.elliefigure8.companions.item.custom.accessories.belts.BeltItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AccessoriesSlot extends Slot {
    public AccessoriesSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.getItem() instanceof BeltItem;
    }
}
