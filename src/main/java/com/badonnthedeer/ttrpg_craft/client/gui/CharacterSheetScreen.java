package com.badonnthedeer.ttrpg_craft.client.gui;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CharacterSheetScreen extends AbstractContainerScreen<CharacterSheetMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TTRPGCraft.MOD_ID, "textures/gui/character_sheet_gui.png");

    public CharacterSheetScreen(CharacterSheetMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f,1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) /2;
        int y = (height = imageHeight) /2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }
}
