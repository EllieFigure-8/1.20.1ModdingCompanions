package com.elliefigure8.companions.block;

import com.elliefigure8.companions.block.entity.GravestoneBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "companionsmod");

    public static final RegistryObject<BlockEntityType<GravestoneBlockEntity>> GRAVESTONE =
            BLOCK_ENTITIES.register("gravestone",
                    () -> BlockEntityType.Builder.of(GravestoneBlockEntity::new, ModBlocks.GRAVESTONE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
