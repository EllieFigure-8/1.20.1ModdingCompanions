package com.elliefigure8.companions.block.custom;

import com.elliefigure8.companions.block.entity.GravestoneBlockEntity;
import com.elliefigure8.companions.sound.ModSounds;
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
        Player pPlayer = null;
        if (blockEntity instanceof GravestoneBlockEntity gravestoneEntity) {
            String storedUUID = gravestoneEntity.getPlayerUUID();

            // Mostrar mensaje en el juego
            pPlayer = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 5.0, false);
            if (storedUUID != null && !storedUUID.isEmpty()) {
                UUID playerUUID = UUID.fromString(storedUUID);

                Player targetPlayer = pLevel.getPlayerByUUID(playerUUID);
                if (targetPlayer instanceof ServerPlayer serverPlayer) {
                    // Revivir al jugador
                    if (serverPlayer.isCreative()) {
                        serverPlayer.setGameMode(GameType.SURVIVAL);
                        serverPlayer.teleportTo(pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5);

                        serverPlayer.setHealth(10.0F);
                        serverPlayer.getFoodData().setFoodLevel(10);

                        pPlayer.getCommandSenderWorld().playSeededSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                                ModSounds.RESURRECTED.get(), SoundSource.PLAYERS, 0.5f, 1f, 0);

                        pLevel.removeBlock(pPos, false);

                        return true;
                    } else {
                        pPlayer.displayClientMessage(Component.literal("Player is Alive."), true);
                    }
                } else {
                    pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: No se encontró un jugador con el UUID especificado."), true);
                }
            } else {
                pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: UUID vacío o nulo en la lápida."), true);
            }
        } else {
            pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: La entidad del bloque no es una instancia de GravestoneBlockEntity."), true);
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
