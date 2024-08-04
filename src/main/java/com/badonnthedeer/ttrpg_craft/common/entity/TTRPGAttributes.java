package com.badonnthedeer.ttrpg_craft.common.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.compress.harmony.pack200.NewAttribute;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.MOD_ID;
public class TTRPGAttributes {
    public static final DeferredRegister<Attribute> NEW_ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, String.format("%s.%s.%s", MOD_ID, "player", "strength"));
    public static final DeferredHolder<Attribute, Attribute> STRENGTH = NEW_ATTRIBUTES.register(
            "strength", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Strength", 10, 0,30)  // A supplier of the object we want to register.
    );

    @SubscribeEvent
    public static void register(IEventBus eventBus)
    {
        NEW_ATTRIBUTES.register(eventBus);
    }
}
