package com.elliefigure8.companions.slots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static net.minecraft.data.models.model.TextureSlot.TEXTURE;
//import net.minecraftforge.*.client.gui.screens.inventory.AbstractContainerScreen;

@EventBusSubscriber(modid = "companionsmod", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AccessoryScreen extends AbstractContainerScreen<AccessoryContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("companionsmod", "textures/gui/accessories_screen.png");

    public AccessoryScreen(AccessoryContainer screenContainer, Inventory inv, Component title) {
        super(screenContainer, inv, title);
        // Define el tamaño de la pantalla
        this.imageWidth = 176; // Ancho de la pantalla
        this.imageHeight = 166; // Alto de la pantalla
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        // Dibuja el fondo de la pantalla
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        super.renderBackground(pGuiGraphics);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        // Aquí puedes agregar lógica para renderizar tooltips u otros elementos gráficos si es necesario
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY); // Renderiza tooltips
    }
}