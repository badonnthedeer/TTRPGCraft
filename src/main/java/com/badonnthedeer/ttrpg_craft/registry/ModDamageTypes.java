package com.badonnthedeer.ttrpg_craft.registry;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;


public class ModDamageTypes {

    public static final ResourceKey<DamageType> FIRE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "fire_damage"));

    public static final ResourceKey<DamageType> COLD_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "cold_damage"));

    public static final ResourceKey<DamageType> ACID_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "acid_damage"));

    public static final ResourceKey<DamageType> LIGHTNING_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "lightning_damage"));

    public static final ResourceKey<DamageType> THUNDER_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "thunder_damage"));

    public static final ResourceKey<DamageType> RADIANT_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "radiant_damage"));

    public static final ResourceKey<DamageType> NECROTIC_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "necrotic_damage"));

    public static final ResourceKey<DamageType> NONMAGICAL_PHYSICAL_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "nonmagical_damage"));



}