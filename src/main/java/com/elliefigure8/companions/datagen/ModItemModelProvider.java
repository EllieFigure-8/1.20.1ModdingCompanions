package com.elliefigure8.companions.datagen;

import com.elliefigure8.companions.CompanionsMod;
import com.elliefigure8.companions.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CompanionsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        simpleItem(ModItems.AURORITA_FRAGMENT);
        simpleItem(ModItems.PARRY_ITEM);
        simpleItem(ModItems.BRONZE_COIN);
        simpleItem(ModItems.SILVER_COIN);
        simpleItem(ModItems.SUSPICIOUS_LOOKING_FUNGI);

        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PEAT_BRICK);

        handheldItem(ModItems.AURORITA_AXE);
        handheldItem(ModItems.AURORITA_PICKAXE);
        handheldItem(ModItems.AURORITA_SWORD);
        handheldItem(ModItems.AURORITA_SHOVEL);
        handheldItem(ModItems.AURORITA_PAXEL);

        //simpleItem(ModItems.AURORITA_HELMET);
        //simpleItem(ModItems.AURORITA_CHESTPLATE);
        //simpleItem(ModItems.AURORITA_LEGGINGS);
        //simpleItem(ModItems.AURORITA_BOOTS);

        simpleItem(ModItems.WHITE_BELT);
        simpleItem(ModItems.YELLOW_BELT);
        simpleItem(ModItems.GREEN_BELT);
        simpleItem(ModItems.BLUE_BELT);
        simpleItem(ModItems.RED_BELT);
        simpleItem(ModItems.BLACK_BELT);
        simpleItem(ModItems.GOLDEN_BELT);

        simpleItem(ModItems.VEIL);
        simpleItem(ModItems.VEIL_OF_DARKNESS);
        simpleItem(ModItems.VEIL_OF_VOID);
        simpleItem(ModItems.VEIL_OF_NULLITY);

        simpleItem(ModItems.LEAF_ELYTRAS);
        simpleItem(ModItems.AURORITA_BOOSTERS);
        simpleItem(ModItems.AQUARITA_FINS);
        simpleItem(ModItems.NETHERITE_WINGS);
        simpleItem(ModItems.STEAM_PUNKER_WINGS);

        simpleItem(ModItems.DATA_TABLET);

    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CompanionsMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CompanionsMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}
