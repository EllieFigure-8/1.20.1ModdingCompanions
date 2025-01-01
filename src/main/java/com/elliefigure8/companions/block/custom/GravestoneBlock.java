package com.elliefigure8.companions.block.custom;

import com.elliefigure8.companions.block.entity.GravestoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GravestoneBlock extends Block implements EntityBlock {
    public GravestoneBlock(Properties pProperties) {
        super(pProperties);
    }

    private boolean revivePlayer(Level pLevel, BlockPos pPos) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof GravestoneBlockEntity gravestoneEntity) {
            String storedUUID = gravestoneEntity.getPlayerUUID();
            System.out.println("GravestoneBlock Debug: Intentando revivir jugador con UUID " + storedUUID);

            if (storedUUID != null && !storedUUID.isEmpty()) {
                UUID playerUUID = UUID.fromString(storedUUID);

                Player targetPlayer = pLevel.getPlayerByUUID(playerUUID);
                if (targetPlayer instanceof ServerPlayer serverPlayer) {
                    System.out.println("GravestoneBlock Debug: Jugador encontrado: " + serverPlayer.getName().getString());

                    // Revivir al jugador.
                    if (serverPlayer.isCreative()) {
                        serverPlayer.setGameMode(GameType.SURVIVAL);
                        serverPlayer.teleportTo(pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5);

                        serverPlayer.setHealth(10.0F);
                        serverPlayer.getFoodData().setFoodLevel(10);

                        pLevel.playSound(null, pPos, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
                        pLevel.removeBlock(pPos, false);

                        System.out.println("GravestoneBlock Debug: Jugador revivido exitosamente.");
                        return true;
                    } else {
                        System.out.println("GravestoneBlock Debug: El jugador no está en modo espectador.");
                    }
                } else {
                    System.out.println("GravestoneBlock Debug: No se encontró un jugador con el UUID especificado.");
                }
            } else {
                System.out.println("GravestoneBlock Debug: UUID vacío o nulo en la lápida.");
            }
        } else {
            System.out.println("GravestoneBlock Debug: La entidad del bloque no es una instancia de GravestoneBlockEntity.");
        }
        return false;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            if (revivePlayer(pLevel, pPos)) {
                return InteractionResult.SUCCESS; // Resurrección exitosa.
            }
            pPlayer.displayClientMessage(Component.literal("No hay jugador muerto asociado o está desconectado."), true);
            return InteractionResult.CONSUME;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void neighborChanged(BlockState state, Level pLevel, BlockPos pPos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, pLevel, pPos, neighborBlock, neighborPos, isMoving); // Llama primero a la implementación base.
        if (!pLevel.isClientSide() && pLevel.hasNeighborSignal(pPos)) {
            revivePlayer(pLevel, pPos); // Resucitar al jugador si hay una señal de redstone.
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GravestoneBlockEntity(pPos, pState);
    }
}
