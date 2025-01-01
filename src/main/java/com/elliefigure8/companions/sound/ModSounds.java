package com.elliefigure8.companions.sound;

import com.elliefigure8.companions.CompanionsMod;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CompanionsMod.MOD_ID);

    public static final RegistryObject<SoundEvent> PARRY_ACTIVATED = registerSoundEvents ("parry_activated");
    public static final RegistryObject<SoundEvent> DAMAGE_PARRIED = registerSoundEvents ("damage_parried");
    public static final RegistryObject<SoundEvent> PARRY_RECHARGED = registerSoundEvents ("parry_recharged");
    public static final RegistryObject<SoundEvent> RESURRECTED = registerSoundEvents ("resurrected");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name)
    {
        ResourceLocation id = new ResourceLocation(CompanionsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}
