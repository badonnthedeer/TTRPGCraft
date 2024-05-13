package com.badonnthedeer.ttrpg_craft.client.gui;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class CharacterSheetMenu extends AbstractContainerMenu {


    public CharacterSheetMenu(int pContainerId, Inventory pPlayerInventory, RegistryFriendlyByteBuf data) {
        super(ModMenuTypes.CHARACTER_SHEET_MENU.get(), pContainerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        return null;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }
}
