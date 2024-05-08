package com.badonnthedeer.ttrpg_craft.client.gui;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.client.gui.components.ImageButton;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.neoforged.neoforge.network.PacketDistributor;
import com.badonnthedeer.ttrpg_craft.common.network.client.CPacketOpenCharSheet;
//import top.theillusivec4.curios.api.client.ICuriosScreen;

//import top.theillusivec4.curios.common.network.client.CPacketOpenCurios;
//import top.theillusivec4.curios.common.network.client.CPacketOpenVanilla;

public class CharacterSheetButton extends ImageButton {

    public static final WidgetSprites CharacterSheetSprite =
            new WidgetSprites(new ResourceLocation(TTRPGCraft.MOD_ID, "character_creation_button"),
                    new ResourceLocation(TTRPGCraft.MOD_ID, "character_creation_button_highlighted"));
    private final AbstractContainerScreen<?> parentGui;

    CharacterSheetButton(AbstractContainerScreen<?> parentGui, int xIn, int yIn, int widthIn, int heightIn,
                 WidgetSprites sprites) {
        super(xIn, yIn, widthIn, heightIn, sprites,
                (button) -> {
                    Minecraft mc = Minecraft.getInstance();
                    System.out.println("Button created.");
                    if (mc.player != null)
                    {
                        PacketDistributor.sendToServer(new CPacketOpenCharSheet());
                    }
                });
        this.parentGui = parentGui;
    }

    @Override
    public void renderWidget(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY,
                             float partialTicks) {
        Tuple<Integer, Integer> offsets = new Tuple<Integer, Integer>(10,10);
                //CuriosScreen.getButtonOffset(parentGui instanceof CreativeModeInventoryScreen);
        this.setX(parentGui.getGuiLeft() + offsets.getA() + 2);
        int yOffset = parentGui instanceof CreativeModeInventoryScreen ? 70 : 85;
        this.setY(parentGui.getGuiTop() + offsets.getB() + yOffset);

        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
    }
}
