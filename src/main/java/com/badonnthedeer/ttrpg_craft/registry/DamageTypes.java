package com.badonnthedeer.ttrpg_craft.registry;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DamageTypes {
    public static final ResourceKey<DamageType> FIRE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "fire_damage"));


}
