package com.badonnthedeer.ttrpg_craft.client.gui;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.util.Tuple;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
// top.theillusivec4.curios.client.CuriosClientConfig;


public class GuiEventHandler
{

    @SubscribeEvent
    public void onInventoryGuiInit(ScreenEvent.Init.Post evt)
    {
        Screen screen = evt.getScreen();

        System.out.println("custom code reached.");
        if (screen instanceof InventoryScreen)
        {
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            boolean isCreative = screen instanceof CreativeModeInventoryScreen;
            Tuple<Integer, Integer> offsets = new Tuple<Integer, Integer>(10,10);
            int x = offsets.getA();
            int y = offsets.getB();
            int size = isCreative ? 8 : 10;
            int yOffset = isCreative ? 67 : 81;
            evt.addListener(
                    new CharacterSheetButton(gui, gui.getGuiLeft() + x - 2, gui.getGuiTop() + y + yOffset, size, size, CharacterSheetButton.CharacterSheetSprite));
        }
    }

    /*@SubscribeEvent
    public void onInventoryGuiDrawBackground(ScreenEvent.Render.Pre evt) {

        if (!(evt.getScreen() instanceof InventoryScreen gui)) {
            return;
        }
        gui.xMouse = evt.getMouseX();
        gui.yMouse = evt.getMouseY();
    }*/

    @SubscribeEvent
    public void onMouseClick(ScreenEvent.MouseButtonPressed.Pre evt)
    {
        long handle = Minecraft.getInstance().getWindow().getWindow();
        boolean isLeftShiftDown = InputConstants.isKeyDown(handle, GLFW.GLFW_KEY_LEFT_SHIFT);
        boolean isRightShiftDown = InputConstants.isKeyDown(handle, GLFW.GLFW_KEY_RIGHT_SHIFT);
        boolean isShiftDown = isLeftShiftDown || isRightShiftDown;

        if (!(evt.getScreen() instanceof CreativeModeInventoryScreen gui) || !isShiftDown)
        {
            return;
        }

        if (!gui.isInventoryOpen())
        {
            return;
        }
    }
}