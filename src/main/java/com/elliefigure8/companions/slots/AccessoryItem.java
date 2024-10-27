package com.elliefigure8.companions.slots;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class AccessoryItem extends Item {

    public AccessoryItem(Properties rarity) {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (!context.getLevel().isClientSide) {
            // Abre el contenedor
            player.openMenu(new SimpleMenuProvider((windowId, inventory, playerEntity) -> new AccessoryContainer(windowId, inventory), Component.translatable("container.accessories")));
        }
        return InteractionResult.SUCCESS;
    }
}
