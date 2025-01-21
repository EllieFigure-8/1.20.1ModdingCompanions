package com.elliefigure8.companions.slots;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

public class AccessoryItem extends Item {
    //Item para abrir el Inventario

    public AccessoryItem(Properties rarity) {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            NetworkHooks.openScreen((ServerPlayer) pPlayer, new AccessoryMenuProvider(), buffer -> {});
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

}
