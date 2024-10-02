package com.elliefigure8.companions.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoundBlock extends Block
{
    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(pLevel.isClientSide())
        {
            if (pHand == InteractionHand.MAIN_HAND) {
                System.out.println("Main Hand, Client");
            }
            else
            {
                System.out.println("Off Hand, Client");
            }
        }
        else
        {
            if (pHand == InteractionHand.MAIN_HAND)
            {
                System.out.println("Main Hand, Server");
            }
            else
            {
                System.out.println("Off Hand, Server");
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}

