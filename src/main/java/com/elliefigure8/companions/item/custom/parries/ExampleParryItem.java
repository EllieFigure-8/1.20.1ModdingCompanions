package com.elliefigure8.companions.item.custom.parries;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ExampleParryItem extends Item
{
    public ExampleParryItem(Properties pProperties) {super(pProperties);}

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide)
        {
            CompoundTag ExampleParryNBT = getCompoundTag(stack);

            final int getMaxParryCooldown = ExampleParryNBT.getInt("getMaxParryCooldown");
            final int maxParryDuration = ExampleParryNBT.getInt("maxParryDuration");
            int parryDuration = ExampleParryNBT.getInt("parryDuration");
            int Parrycooldown = ExampleParryNBT.getInt("Parrycooldown");
            boolean hasParry = ExampleParryNBT.getBoolean("hasParry");
            boolean hasPressedParry = ExampleParryNBT.getBoolean("hasPressedParry");
            boolean hasParriedAttack = ExampleParryNBT.getBoolean("hasParriedAttack");

            
            if (KeyBindRegistry.ParryAbilityKey.consumeClick() && hasParry && !hasPressedParry)
            {
                player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_ACTIVATED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                hasPressedParry = true;
                System.out.println("Parry Activado.");
            }

            if (hasPressedParry)
            {
                parryDuration--;
                if (hasParriedAttack)
                {
                    parryDuration = 0;
                    hasParriedAttack = false;
                    System.out.println("Hiciste Parry. Cancelando Parry Duration.");
                }
                if (parryDuration <= 0)
                {
                    Parrycooldown = getMaxParryCooldown;
                    parryDuration = maxParryDuration;
                    hasParry = false;
                    hasPressedParry = false;
                    System.out.println("Parry Desactivado. Iniciando Cooldown.");
                }
            }

            if (!hasParry) {
                Parrycooldown--;
                if (Parrycooldown <= 0) {
                    hasParry = true;
                    parryDuration = maxParryDuration;
                    player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_RECHARGED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                    System.out.println("Cooldown Terminado.");
                }
            }

            ExampleParryNBT.putInt("parryDuration", parryDuration);
            ExampleParryNBT.putInt("Parrycooldown", Parrycooldown);
            ExampleParryNBT.putBoolean("hasParry", hasParry);
            ExampleParryNBT.putBoolean("hasPressedParry", hasPressedParry);
            ExampleParryNBT.putBoolean("hasParriedAttack", hasParriedAttack);
            stack.setTag(ExampleParryNBT);
        }
    }

    private static @NotNull CompoundTag getCompoundTag(ItemStack stack) {
        CompoundTag ExampleParryNBT = stack.getOrCreateTag();

        Map<String, Object> defaultValues = Map.of(
                "getMaxParryCooldown", 300,
                "maxParryDuration", 20,
                "parryDuration", 20,
                "Parrycooldown", 0,
                "hasParry", true,
                "hasPressedParry", false,
                "hasParriedAttack", false
        );

        defaultValues.forEach((key, value) -> {
            if (!ExampleParryNBT.contains(key)) {
                if (value instanceof Integer) {
                    ExampleParryNBT.putInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    ExampleParryNBT.putBoolean(key, (Boolean) value);
                }
            }
        });

        return ExampleParryNBT;
    }

    /*
    private static @NotNull CompoundTag getCompoundTag(ItemStack stack) {
        CompoundTag ExampleParryNBT = stack.getOrCreateTag();

        if (!ExampleParryNBT.contains("getMaxParryCooldown")) {
            ExampleParryNBT.putInt("getMaxParryCooldown", 300);
        }
        if (!ExampleParryNBT.contains("maxParryDuration")) {
            ExampleParryNBT.putInt("maxParryDuration", 20);
        }
        if (!ExampleParryNBT.contains("parryDuration")) {
            ExampleParryNBT.putInt("parryDuration", 20);
        }
        if (!ExampleParryNBT.contains("Parrycooldown")) {
            ExampleParryNBT.putInt("Parrycooldown", 0);
        }
        if (!ExampleParryNBT.contains("hasParry")) {
            ExampleParryNBT.putBoolean("hasParry", true);
        }
        if (!ExampleParryNBT.contains("hasPressedParry")) {
            ExampleParryNBT.putBoolean("hasPressedParry", false);
        }
        if (!ExampleParryNBT.contains("hasParriedAttack")) {
            ExampleParryNBT.putBoolean("hasParriedAttack", false);
        }

        return ExampleParryNBT;
    } */
}
