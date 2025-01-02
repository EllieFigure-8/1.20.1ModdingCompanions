package com.elliefigure8.companions.block.entity;

import com.elliefigure8.companions.block.ModBlockEntities;
import com.elliefigure8.companions.events.gameevents.GravestoneOnDeath;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GravestoneBlockEntity extends BlockEntity {
    private String playerUUID = "";
    private List<ItemStack> playerItems = new ArrayList<>();

    public GravestoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GRAVESTONE.get(), pos, state);
    }

    public void setPlayer(Player player) {
        if (player != null) {
            this.playerUUID = player.getUUID().toString();

            // Debugging para verificar el UUID guardado.
            System.out.println("Entity Debug: UUID guardado = " + this.playerUUID);
        } else {
            System.out.println("Entity Debug: No se pudo guardar el UUID porque el jugador es null.");
        }
    }

    public String getPlayerUUID() {
        // Debugging para verificar el UUID recuperado.
        System.out.println("Entity Debug: UUID recuperado = " + this.playerUUID);
        return this.playerUUID;
    }

    // Save Items
    public List<ItemStack> getPlayerItems() {
        return playerItems;
    }
    public void setPlayerItems(List<ItemStack> playerItems) {
        this.playerItems = playerItems;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.playerUUID = tag.getString("playerUUID");

        // Cargar ítems desde NBT
        CompoundTag itemsTag = tag.getCompound("playerItems");
        for (String key : itemsTag.getAllKeys()) {
            CompoundTag itemTag = itemsTag.getCompound(key);
            ItemStack itemStack = ItemStack.of(itemTag);
            if (!itemStack.isEmpty()) {
                playerItems.add(itemStack);  // Solo agregar ítems no vacíos
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("playerUUID", this.playerUUID);  // Guardar UUID del jugador

        // Guardar ítems en NBT
        CompoundTag itemsTag = new CompoundTag();
        for (int i = 0; i < playerItems.size(); i++) {
            ItemStack itemStack = playerItems.get(i);
            CompoundTag itemTag = itemStack.save(new CompoundTag());
            itemsTag.put("Item" + i, itemTag);  // Usar un nombre único para cada ítem
        }
        tag.put("playerItems", itemsTag);  // Guardar todos los ítems bajo la clave "playerItems"
    }
}