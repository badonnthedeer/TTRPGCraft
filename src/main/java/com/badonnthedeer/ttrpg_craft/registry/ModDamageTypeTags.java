package com.badonnthedeer.ttrpg_craft.registry;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;


public class ModDamageTypeTags {

    public static final TagKey<DamageType> IS_FIRE = create("is_fire");
    public static final TagKey<DamageType> IS_COLD = create("is_cold");
    public static final TagKey<DamageType> IS_ACID = create("is_acid");
    public static final TagKey<DamageType> IS_POISON = create("is_poison");
    public static final TagKey<DamageType> IS_LIGHTNING = create("is_lightning");
    public static final TagKey<DamageType> IS_THUNDER = create("is_thunder");
    public static final TagKey<DamageType> IS_FORCE = create("is_force");
    public static final TagKey<DamageType> IS_PSYCHIC = create("is_psychic");
    public static final TagKey<DamageType> IS_RADIANT = create("is_radiant");
    public static final TagKey<DamageType> IS_NECROTIC = create("is_necrotic");

    public static final TagKey<DamageType> IS_NONMAGICAL = create("is_nonmagical");

    private static TagKey<DamageType> create(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, name));
    }
}
