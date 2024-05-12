package com.badonnthedeer.ttrpg_craft.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class CharacterSheetMenuProvider implements MenuProvider {

    @Override
    public Component getDisplayName() {
        return Component.literal("Character Sheet");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CharacterSheetMenu(pContainerId, pPlayerInventory, pPlayer);
    }
}
