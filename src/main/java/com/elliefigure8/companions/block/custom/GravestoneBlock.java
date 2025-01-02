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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

            pPlayer = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 5.0, false);
            if (storedUUID != null && !storedUUID.isEmpty()) {
                UUID playerUUID = UUID.fromString(storedUUID);

                Player targetPlayer = pLevel.getPlayerByUUID(playerUUID);
                if (targetPlayer instanceof ServerPlayer serverPlayer) {
                    pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: Intentando revivir jugador con UUID " + storedUUID), true);

                    if (serverPlayer.isCreative()) {
                        serverPlayer.setGameMode(GameType.SURVIVAL);
                        serverPlayer.teleportTo(pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5);

                        serverPlayer.setHealth(10.0F);
                        serverPlayer.getFoodData().setFoodLevel(10);

                        pPlayer.getCommandSenderWorld().playSeededSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                                ModSounds.RESURRECTED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);

                        for (ItemStack itemStack : gravestoneEntity.getPlayerItems()) {
                            if (!itemStack.isEmpty()) {
                                serverPlayer.getInventory().add(itemStack);
                            }
                        }

                        List<ItemStack> armorItems = gravestoneEntity.getPlayerArmorItems();
                        for (int i = 0; i < armorItems.size(); i++) {
                            if (!armorItems.get(i).isEmpty()) {
                                serverPlayer.getInventory().armor.set(i, armorItems.get(i));
                            }
                        }

                        pLevel.playSound(null, pPos, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
                        pLevel.removeBlock(pPos, false);

                        pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: Jugador revivido y los ítems restaurados."), true); // Mensaje en el juego
                        return true;
                    } else {
                        pPlayer.displayClientMessage(Component.literal("GravestoneBlock Debug: El jugador no está en modo espectador."), true);
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
                return InteractionResult.SUCCESS;
            }
            pPlayer.displayClientMessage(Component.literal("No hay jugador muerto asociado o está desconectado."), true);
            return InteractionResult.CONSUME;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void neighborChanged(BlockState state, Level pLevel, BlockPos pPos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, pLevel, pPos, neighborBlock, neighborPos, isMoving);
        if (!pLevel.isClientSide() && pLevel.hasNeighborSignal(pPos)) {
            revivePlayer(pLevel, pPos);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GravestoneBlockEntity(pPos, pState);
    }
}
