package com.elliefigure8.companions.item;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.block.ModBlocks;
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

                        //Dodges
                        pOutput.accept(ModItems.YELLOW_BELT.get());

                        //Blocks
                        pOutput.accept(ModBlocks.AURORITA_ORE.get());


                    }).build());

    public static void register (IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
