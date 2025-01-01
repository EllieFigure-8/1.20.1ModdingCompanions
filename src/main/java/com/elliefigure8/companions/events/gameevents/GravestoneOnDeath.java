package com.elliefigure8.companions.events.gameevents;

import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.block.entity.GravestoneBlockEntity;
import net.minecraft.commands.arguments.coordinates.WorldCoordinate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GravestoneOnDeath {
    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        Level world = player.getCommandSenderWorld();

        if (world.isClientSide) return;

        BlockPos deathPos = player.blockPosition();

        if (world.getBlockState(deathPos).isAir()) {
            world.setBlock(deathPos, ModBlocks.GRAVESTONE.get().defaultBlockState(), 3);
        } else if (world.getBlockState(deathPos).isAir()) {
            BlockPos alternatePos = deathPos.above();
            if (world.getBlockState(alternatePos).isAir()) {
                world.setBlock(alternatePos, ModBlocks.GRAVESTONE.get().defaultBlockState(), 3);
            }
        }

        if (world.getBlockEntity(deathPos) instanceof GravestoneBlockEntity gravestoneEntity) {
            gravestoneEntity.setPlayer(player);

            System.out.println("GravestoneOnDeath Debug: Lápida creada en " + deathPos + " con UUID del jugador " + player.getUUID());

        }
    }

}
