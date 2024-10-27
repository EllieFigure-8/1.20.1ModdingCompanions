package com.elliefigure8.companions.item;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.item.custom.DataTabletItem;
import com.elliefigure8.companions.item.custom.FuelItem;
//import com.elliefigure8.companions.item.custom.dodges.BeltWithParryItem;
import com.elliefigure8.companions.item.custom.dodges.BlackBeltItem;
import com.elliefigure8.companions.item.custom.dodges.ExampleBeltItem;
import com.elliefigure8.companions.item.custom.dodges.RedBeltItem;
import com.elliefigure8.companions.item.custom.parries.ExampleParryItem;
import com.elliefigure8.companions.item.custom.tools.MetalDetectorItem;
import com.elliefigure8.companions.item.custom.dodges.BeltItem;
import com.elliefigure8.companions.item.custom.paxel.PaxelItem;
import com.elliefigure8.companions.item.custom.weapons.AuroritaSwordItem;
import com.elliefigure8.companions.slots.AccessoryItem;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> AURORITA_FRAGMENT = ITEMS.register( "aurorita_fragment",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.COMMON)));
    //Weapons and Tools
    public static final RegistryObject<Item> AURORITA_SWORD = ITEMS.register("aurorita_sword",
            () -> new AuroritaSwordItem(
                    ModToolTiers.AURORITA,
                    7,
                    -2.4f,
                    new Item.Properties()
            ));

    public static final RegistryObject<Item> AURORITA_AXE = ITEMS.register("aurorita_axe",
            () -> new AxeItem(
                    ModToolTiers.AURORITA,
                    9,
                    -3.0f,
                    new Item.Properties()
            ));

    public static final RegistryObject<Item> AURORITA_PICKAXE = ITEMS.register("aurorita_pickaxe",
            () -> new PickaxeItem(
                    ModToolTiers.AURORITA,
                    4,
                    -2.4f,
                    new Item.Properties()
            ));

    public static final RegistryObject<Item> AURORITA_SHOVEL = ITEMS.register("aurorita_shovel",
            () -> new ShovelItem(
                    ModToolTiers.AURORITA,
                    5,
                    -2.4f,
                    new Item.Properties()
            ));
    public static final RegistryObject<Item> AURORITA_PAXEL = ITEMS.register("aurorita_paxel",
            () -> new PaxelItem(
                    ModToolTiers.AURORITA,
                    5,
                    -2.4f,
                    new Item.Properties()
            ));

    //Armors
    public static final RegistryObject<Item> AURORITA_HELMET = ITEMS.register("aurorita_helmet",
            () -> new ArmorItem(ModArmorMaterials.AURORITA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> AURORITA_CHESTPLATE = ITEMS.register("aurorita_chestplate",
            () -> new ArmorItem(ModArmorMaterials.AURORITA, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> AURORITA_LEGGINGS = ITEMS.register("aurorita_leggings",
            () -> new ArmorItem(ModArmorMaterials.AURORITA, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> AURORITA_BOOTS = ITEMS.register("aurorita_boots",
            () -> new ArmorItem(ModArmorMaterials.AURORITA, ArmorItem.Type.BOOTS, new Item.Properties()));

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
            () -> new RedBeltItem(new Item.Properties() //BeltWithParryItem
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> BLACK_BELT = ITEMS.register( "black_belt",
            () -> new BlackBeltItem(new Item.Properties() //
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> GOLDEN_BELT = ITEMS.register( "golden_belt",
            () -> new ExampleBeltItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));


    public static final RegistryObject<Item> ACCESSORY_ITEM = ITEMS.register( "accessory_item",
            () -> new AccessoryItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    //Parries
    public static final RegistryObject<Item> PARRY_ITEM = ITEMS.register( "parry_item",
            () -> new ExampleParryItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

    //NBT DATA
    public static final RegistryObject<Item> DATA_TABLET = ITEMS.register( "data_tablet",
            () -> new DataTabletItem(new Item.Properties().stacksTo(1)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
