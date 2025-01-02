package com.elliefigure8.companions.events.gameevents;

import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.block.entity.GravestoneBlockEntity;
import net.minecraft.commands.arguments.coordinates.WorldCoordinate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.entity.EquipmentSlot.*;

public class GravestoneOnDeath {
    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        Level world = player.getCommandSenderWorld();
        if (world.isClientSide) return;

        event.setCanceled(true); // Evitar que los ítems caigan al suelo.

        BlockPos deathPos = player.blockPosition();

        // Crear la lápida en la posición de la muerte
        if (world.getBlockState(deathPos).isAir()) {
            world.setBlock(deathPos, ModBlocks.GRAVESTONE.get().defaultBlockState(), 3);
        } else {
            BlockPos alternatePos = deathPos.above();
            if (world.getBlockState(alternatePos).isAir()) {
                world.setBlock(alternatePos, ModBlocks.GRAVESTONE.get().defaultBlockState(), 3);
            }
        }

        // Acceder al BlockEntity de la lápida
        if (world.getBlockEntity(deathPos) instanceof GravestoneBlockEntity gravestoneEntity) {
            gravestoneEntity.setPlayer(player); // Guardar el UUID del jugador en la lápida

            // Guardar los ítems del inventario
            List<ItemStack> items = new ArrayList<>();
            for (ItemStack item : player.getInventory().items) {
                if (!item.isEmpty()) {
                    items.add(item.copy()); // Copiar ítem para evitar referencias directas
                }
            }
            gravestoneEntity.setPlayerItems(items);

            // Guardar los ítems de la armadura
            List<ItemStack> armorItems = new ArrayList<>(4); // Crear una lista de tamaño 4
            for (int i = 0; i < 4; i++) {
                armorItems.add(ItemStack.EMPTY); // Inicializar las ranuras con ItemStack.EMPTY
            }

            // Asignar las piezas de armadura a las ranuras correspondientes
            for (ItemStack armor : player.getArmorSlots()) {
                if (!armor.isEmpty()) {
                    if (armor.getItem() instanceof ArmorItem) {
                        ArmorItem armorItem = (ArmorItem) armor.getItem();
                        switch (armorItem.getEquipmentSlot()) {
                            case HEAD:
                                armorItems.set(3, armor.copy());
                                break;
                            case CHEST:
                                armorItems.set(2, armor.copy());
                                break;
                            case LEGS:
                                armorItems.set(1, armor.copy());
                                break;
                            case FEET:
                                armorItems.set(0, armor.copy());
                                break;
                        }
                    }
                }
            }

            gravestoneEntity.setPlayerArmorItems(armorItems); // Guardar los ítems de armadura en la lápida

            System.out.println("GravestoneOnDeath Debug: Lápida creada en " + deathPos + " con UUID del jugador " + player.getUUID());
        }
    }
}
