package com.badonnthedeer.ttrpg_craft.client.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

import static com.badonnthedeer.ttrpg_craft.client.gui.ModMenuTypes.MENUS;

public class CharacterSheetMenu extends AbstractContainerMenu {

    /*public CharacterSheetMenu(int containerId, Inventory playerInv, FriendlyByteBuf extraData) {
        super(ModMenuTypes.CHARACTER_SHEET_MENU.get(), containerId);

    }*/

    public CharacterSheetMenu(int containerId, Inventory playerInv, Player playerRef) {
        super(ModMenuTypes.CHARACTER_SHEET_MENU.get(), containerId);

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
