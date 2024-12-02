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
            () -> new RangedAttribute(MOD_ID + ".strength", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> DEXTERITY = TTRPGATTRIBUTES.register(
            "dexterity", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".dexterity", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> CONSTITUTION = TTRPGATTRIBUTES.register(
            "constitution", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".constitution", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> INTELLIGENCE = TTRPGATTRIBUTES.register(
            "intelligence", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".intelligence", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> WISDOM = TTRPGATTRIBUTES.register(
            "wisdom", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".wisdom", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> CHARISMA = TTRPGATTRIBUTES.register(
            "charisma", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".charisma", 10, 0,30)
    );
    public static final DeferredHolder<Attribute, Attribute> CRIT_CHANCE = TTRPGATTRIBUTES.register(
            "crit_chance", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".crit_chance", 5, 5,100)
    );
    public static final DeferredHolder<Attribute, Attribute> MAGICAL_UNARMED_ATTACKS = TTRPGATTRIBUTES.register(
            "magical_unarmed_attacks", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".magical_unarmed_attacks", 0, 0, 1)
    );

    public static final DeferredHolder<Attribute, Attribute> FIRE_RESIST = TTRPGATTRIBUTES.register(
            "fire_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".fire_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> COLD_RESIST = TTRPGATTRIBUTES.register(
            "cold_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".cold_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> ACID_RESIST = TTRPGATTRIBUTES.register(
            "acid_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".acid_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> POISON_RESIST = TTRPGATTRIBUTES.register(
            "poison_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".poison_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> LIGHTNING_RESIST = TTRPGATTRIBUTES.register(
            "lightning_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".lightning_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> THUNDER_RESIST = TTRPGATTRIBUTES.register(
            "thunder_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".thunder_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> FORCE_RESIST = TTRPGATTRIBUTES.register(
            "force_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".force_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> PSYCHIC_RESIST = TTRPGATTRIBUTES.register(
            "psychic_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".psychic_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> NECROTIC_RESIST = TTRPGATTRIBUTES.register(
            "necrotic_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".necrotic_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> RADIANT_RESIST = TTRPGATTRIBUTES.register(
            "radiant_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".radiant_resist", 0, -100,100)
    );
    public static final DeferredHolder<Attribute, Attribute> PHYSICAL_RESIST = TTRPGATTRIBUTES.register(
            "physical_resist", // Our registry name
            () -> new RangedAttribute(MOD_ID + ".physical_resist", 0, -100,100)
    );

    @SubscribeEvent
    public static void register(IEventBus eventBus)
    {
        TTRPGATTRIBUTES.register(eventBus);
    }
}
