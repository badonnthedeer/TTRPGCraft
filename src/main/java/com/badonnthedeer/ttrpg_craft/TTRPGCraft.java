package com.badonnthedeer.ttrpg_craft;

import com.badonnthedeer.ttrpg_craft.client.gui.CharacterSheetScreen;
import com.badonnthedeer.ttrpg_craft.client.gui.GuiEventHandler;
import com.badonnthedeer.ttrpg_craft.client.gui.ModMenuTypes;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.badonnthedeer.ttrpg_craft.util.ModTags;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes.STRENGTH;
import static net.minecraft.tags.EntityTypeTags.SKELETONS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TTRPGCraft.MOD_ID)
public class TTRPGCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "ttrpg_craft";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TTRPGCraft(IEventBus modEventBus)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        TTRPGAttributes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            System.out.println("FMLClientSetupEvent reached.");
            NeoForge.EVENT_BUS.register(new GuiEventHandler());
        }

        @SubscribeEvent
        public static void menusSetup(RegisterMenuScreensEvent event)
        {
            System.out.println("RegisterMenuScreensEvent reached.");
            event.register(ModMenuTypes.CHARACTER_SHEET_MENU.get(), CharacterSheetScreen::new);
        }

        @SubscribeEvent
        public static void addAttributes(EntityAttributeModificationEvent event){
            BuiltInRegistries.ENTITY_TYPE.forEach((type) -> {
                try
                {
                    if(type.getCategory() != EntityType.FIREBALL.getCategory())
                    {
                        LOGGER.debug("Successfully added TTRPGAttributes to " + type.getDescriptionId());
                        event.add((EntityType<? extends LivingEntity>) type, STRENGTH);
                    }
                    else if(type.getDescriptionId().equals("entity.minecraft.player") || type.getDescriptionId().equals("entity.minecraft.villager") || type.getDescriptionId().equals("entity.minecraft.snow_golem") || type.getDescriptionId().equals("entity.minecraft.iron_golem"))
                    {
                        LOGGER.debug("Successfully added TTRPGAttributes to " + type.getDescriptionId());
                        event.add((EntityType<? extends LivingEntity>) type, STRENGTH);
                    }
                }
                catch (Exception ignored)
                {
                    LOGGER.warn("Failed to add STR to " + type.getDescriptionId());
                }
            });
        }
    }
}
