package com.elliefigure8.companions.item.custom;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CompanionsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COMPANIONS_TAB = CREATIVE_MODE_TAB.register("companions_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.BRONZE_COIN.get()))
                    .title(Component.translatable("creativetab.companions_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BRONZE_COIN.get());
                        pOutput.accept(ModItems.SILVER_COIN.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.AURORITA_FRAGMENT.get());

                        //Weapons and Tools
                        pOutput.accept(ModItems.AURORITA_AXE.get());
                        pOutput.accept(ModItems.AURORITA_PICKAXE.get());
                        pOutput.accept(ModItems.AURORITA_SHOVEL.get());
                        pOutput.accept(ModItems.AURORITA_SWORD.get());

                        //Foods
                        pOutput.accept(ModItems.SUSPICIOUS_LOOKING_FUNGI.get());

                        //Fuel
                        pOutput.accept(ModItems.PEAT_BRICK.get());

                        //Dodges
                        pOutput.accept(ModItems.WHITE_BELT.get());
                        pOutput.accept(ModItems.YELLOW_BELT.get());
                        pOutput.accept(ModItems.GREEN_BELT.get());
                        pOutput.accept(ModItems.BLUE_BELT.get());
                        pOutput.accept(ModItems.RED_BELT.get());
                        pOutput.accept(ModItems.BLACK_BELT.get());
                        pOutput.accept(ModItems.GOLDEN_BELT.get());

                        //Parries
                        pOutput.accept(ModItems.PARRY_ITEM.get());

                        //Blocks
                        pOutput.accept(ModBlocks.AURORITA_ORE.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());


                    }).build());

    public static void register (IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
