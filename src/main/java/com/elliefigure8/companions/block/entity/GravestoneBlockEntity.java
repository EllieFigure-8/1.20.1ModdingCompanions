package com.elliefigure8.companions.block.entity;

import com.elliefigure8.companions.block.ModBlockEntities;
import com.elliefigure8.companions.events.gameevents.GravestoneOnDeath;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class GravestoneBlockEntity extends BlockEntity {
    private String playerUUID = "";

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

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.playerUUID = tag.getString("PlayerUUID");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("PlayerUUID", this.playerUUID);
    }
}