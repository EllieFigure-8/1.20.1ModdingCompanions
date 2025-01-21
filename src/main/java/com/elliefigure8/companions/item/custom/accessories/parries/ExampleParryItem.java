package com.elliefigure8.companions.item.custom.accessories.parries;

import com.elliefigure8.companions.registry.KeyBindRegistry;
import com.elliefigure8.companions.sound.ModSounds;
import com.elliefigure8.companions.util.items.BeltItemUtil;
import com.elliefigure8.companions.util.items.ParryItemUtil;
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
            final int getMaxParryCooldown = 300;
            final int maxParryDuration = 60;
            int parryDuration = ParryItemUtil.getParryDuration(stack);
            int Parrycooldown = ParryItemUtil.getParryCooldown(stack);
            boolean hasParry = ParryItemUtil.getHasParry(stack);
            boolean hasPressedParry = ParryItemUtil.getHasPressedParry(stack);
            boolean hasParriedAttack = ParryItemUtil.getHasParriedAttack(stack);

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

            if (!hasParry)
            {
                Parrycooldown--;
                if (Parrycooldown <= 0) {
                    hasParry = true;
                    parryDuration = maxParryDuration;
                    player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY_RECHARGED.get(), SoundSource.PLAYERS, 0.75f, 1f, 0);
                    System.out.println("Cooldown Terminado.");
                }
            }
            ParryItemUtil.setParryDuration(stack, parryDuration);
            ParryItemUtil.setParryCooldown(stack, Parrycooldown);
            ParryItemUtil.setHasParry(stack, hasParry);
            ParryItemUtil.setHasPressedParry(stack, hasPressedParry);
            ParryItemUtil.setHasParriedAttack(stack, hasParriedAttack);
        }
    }
}
