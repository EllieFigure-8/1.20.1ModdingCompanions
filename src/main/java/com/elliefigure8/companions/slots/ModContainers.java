package com.elliefigure8.companions.slots;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    //La clase ModContainers se encarga de registrar todos los tipos de menús (contenedores) personalizados para el mod.

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "companionsmod");

    public static final RegistryObject<MenuType<AccessoryContainer>> ACCESSORY_CONTAINER = MENUS.register("accessory_container",
            () -> IForgeMenuType.create((windowId, inv, data) -> new AccessoryContainer(windowId, inv))
    );

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
