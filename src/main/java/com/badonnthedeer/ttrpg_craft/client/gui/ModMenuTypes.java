package com.badonnthedeer.ttrpg_craft.client.gui;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS=
            DeferredRegister.create(BuiltInRegistries.MENU, TTRPGCraft.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<CharacterSheetMenu>> CHARACTER_SHEET_MENU =
            registerMenuType(CharacterSheetMenu::new, "character_sheet_menu");


    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name)
    {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }


}
