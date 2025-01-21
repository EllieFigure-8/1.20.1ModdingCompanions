package com.elliefigure8.companions;

import com.elliefigure8.companions.block.ModBlockEntities;
import com.elliefigure8.companions.block.ModBlocks;
import com.elliefigure8.companions.enchantment.ModEnchantments;
import com.elliefigure8.companions.events.gameevents.GravestoneOnDeath;
import com.elliefigure8.companions.events.items.accessories.*;
import com.elliefigure8.companions.item.ModCreativeModeTabs;
import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.item.custom.accessories.dashes.VeilItem;
import com.elliefigure8.companions.item.custom.accessories.dashes.VeilOfDarknessItem;
import com.elliefigure8.companions.item.custom.accessories.dashes.VeilOfNullityItem;
import com.elliefigure8.companions.item.custom.accessories.dashes.VeilOfVoidItem;
import com.elliefigure8.companions.item.custom.accessories.wings.AquaritaFinsItem;
import com.elliefigure8.companions.item.custom.accessories.wings.AuroritaBoostersItem;
import com.elliefigure8.companions.item.custom.accessories.wings.LeafElytrasItem;
import com.elliefigure8.companions.item.custom.accessories.wings.NetheriteWingsItem;
import com.elliefigure8.companions.slots.ModContainers;
import com.elliefigure8.companions.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.elliefigure8.companions.slots.AccessoryScreen;

@Mod(CompanionsMod.MOD_ID)
public class CompanionsMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "companionsmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public CompanionsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);

        ModEnchantments.register(modEventBus);

        //Block Entities
        ModBlockEntities.register(modEventBus);

        //Game Events
        MinecraftForge.EVENT_BUS.register(GravestoneOnDeath.class);

        //Item Events
        MinecraftForge.EVENT_BUS.register(BeltDodgeEvent.class);
        MinecraftForge.EVENT_BUS.register(ExampleBeltDodgeEvent.class);
        MinecraftForge.EVENT_BUS.register(ExampleParryEvent.class);
        MinecraftForge.EVENT_BUS.register(BlackBeltEvent.class);
        MinecraftForge.EVENT_BUS.register(RedBeltEvent.class);

        MinecraftForge.EVENT_BUS.register(VeilItem.class);
        MinecraftForge.EVENT_BUS.register(VeilOfDarknessItem.class);
        MinecraftForge.EVENT_BUS.register(VeilOfVoidItem.class);
        MinecraftForge.EVENT_BUS.register(VeilOfNullityItem.class);

        MinecraftForge.EVENT_BUS.register(LeafElytrasItem.class);
        MinecraftForge.EVENT_BUS.register(AuroritaBoostersItem.class);
        MinecraftForge.EVENT_BUS.register(AquaritaFinsItem.class);
        MinecraftForge.EVENT_BUS.register(NetheriteWingsItem.class);
        //MinecraftForge.EVENT_BUS.register(ParryItemEvent.class);

        //MinecraftForge.EVENT_BUS.register(ModContainers.class);
        ModContainers.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        MenuScreens.register(ModContainers.ACCESSORY_CONTAINER.get(), AccessoryScreen::new);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(ModItems.BRONZE_COIN);
            event.accept(ModItems.SILVER_COIN);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(ModBlocks.AURORITA_ORE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
