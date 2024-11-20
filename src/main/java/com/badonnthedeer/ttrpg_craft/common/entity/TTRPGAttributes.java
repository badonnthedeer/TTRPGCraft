package com.badonnthedeer.ttrpg_craft.common.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.MOD_ID;
public class TTRPGAttributes {
    public static final DeferredRegister<Attribute> TTRPGATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, String.format("%s", MOD_ID));
    public static final DeferredHolder<Attribute, Attribute> STRENGTH = TTRPGATTRIBUTES.register(
            "strength", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Strength", 10, 0,30)
    );

    public static final DeferredHolder<Attribute, Attribute> DEXTERITY = TTRPGATTRIBUTES.register(
            "dexterity", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Dexterity", 10, 0,30)
    );

    public static final DeferredHolder<Attribute, Attribute> CONSTITUTION = TTRPGATTRIBUTES.register(
            "constitution", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Constitution", 10, 0,30)
    );

    public static final DeferredHolder<Attribute, Attribute> INTELLIGENCE = TTRPGATTRIBUTES.register(
            "intelligence", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Intelligence", 10, 0,30)
    );

    public static final DeferredHolder<Attribute, Attribute> WISDOM = TTRPGATTRIBUTES.register(
            "wisdom", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Wisdom", 10, 0,30)
    );

    public static final DeferredHolder<Attribute, Attribute> CHARISMA = TTRPGATTRIBUTES.register(
            "charisma", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Charisma", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> CRIT_CHANCE = TTRPGATTRIBUTES.register(
            "crit_chance", // Our registry name
            () -> new RangedAttribute("ttrpgCraft.Crit_Chance", 5, 5,100)
    );


    @SubscribeEvent
    public static void register(IEventBus eventBus)
    {
        TTRPGATTRIBUTES.register(eventBus);
    }
}
