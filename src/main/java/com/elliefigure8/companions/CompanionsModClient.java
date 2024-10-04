package com.elliefigure8.companions;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CompanionsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CompanionsModClient
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        KeyBindRegistry.register();
    }
}
