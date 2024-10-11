package com.elliefigure8.companions.item.custom.tools;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.util.InventoryUtil;
import com.elliefigure8.companions.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide())
        {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++)
            {
                BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockState))
                {
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;


                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get()))
                    {
                        addDataToDataTablet(player, positionClicked.below(i), blockState.getBlock());
                    }

                    //pContext.getLevel().playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                           // ModSounds.DAMAGE_PARRIED.get(), SoundSource.PLAYERS, 1f, 1f, 0);

                    break;
                }
            }

            if(!foundBlock)
            {
                outputNoValuableFound(player);
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void addDataToDataTablet (Player player, BlockPos below, Block block)
    {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

        CompoundTag data = new CompoundTag();
        data.putString("companionsmod.found_ore", "Valuable Found: " + I18n.get(block.getDescriptionId())
                + " at (" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")" );

        dataTablet.setTag(data);
    }

    private void outputNoValuableFound(Player player)
    {
        player.sendSystemMessage(Component.translatable("item.companionsmod.metal_detector.no_valuables"));
    }

    private void outputValuableCoordinates(BlockPos below, Player player, Block block)
    {
        player.sendSystemMessage(Component.literal("Valuable Found: " + I18n.get(block.getDescriptionId())
                + " at (" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState blockState)
    {
        return blockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
