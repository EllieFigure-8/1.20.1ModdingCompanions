package com.elliefigure8.companions.item;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.item.custom.FuelItem;
import com.elliefigure8.companions.item.custom.MetalDetectorItem;
import com.elliefigure8.companions.item.custom.ModFoodProperties;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.parries.ParryItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CompanionsMod.MOD_ID);

    public static final RegistryObject<Item> BRONZE_COIN = ITEMS.register("bronze_coin",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .durability(200)));

    //Foods
    public static final RegistryObject<Item> SUSPICIOUS_LOOKING_FUNGI = ITEMS.register( "suspicious_looking_fungi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUSPICIOUS_LOOKING_FUNGI)));

    //Fuel
    public static final RegistryObject<Item> PEAT_BRICK = ITEMS.register( "peat_brick",
            () -> new FuelItem(new Item.Properties(), 200));

    //Dodges
    public static final RegistryObject<Item> WHITE_BELT = ITEMS.register( "white_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> YELLOW_BELT = ITEMS.register( "yellow_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> GREEN_BELT = ITEMS.register( "green_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> BLUE_BELT = ITEMS.register( "blue_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> RED_BELT = ITEMS.register( "red_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> BLACK_BELT = ITEMS.register( "black_belt",
            () -> new BeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));


    //Parries
    public static final RegistryObject<Item> PARRY_ITEM = ITEMS.register( "parry_item",
            () -> new ParryItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
