package com.badonnthedeer.ttrpg_craft.datagen;


import com.badonnthedeer.ttrpg_craft.registry.ModDamageTypeTags;
import com.badonnthedeer.ttrpg_craft.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends DamageTypeTagsProvider
{

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModDamageTypeTags.IS_FIRE)
                .add(ModDamageTypes.FIRE_DAMAGE);
        this.tag(ModDamageTypeTags.IS_COLD)
                .add(ModDamageTypes.COLD_DAMAGE);
        this.tag(ModDamageTypeTags.IS_ACID)
                .add(ModDamageTypes.ACID_DAMAGE);
        this.tag(ModDamageTypeTags.IS_POISON)
                .add(ModDamageTypes.POISON_DAMAGE);
        this.tag(ModDamageTypeTags.IS_LIGHTNING)
                .add(ModDamageTypes.LIGHTNING_DAMAGE)
                .addTag(DamageTypeTags.IS_LIGHTNING);
        this.tag(ModDamageTypeTags.IS_THUNDER)
                .add(ModDamageTypes.THUNDER_DAMAGE,
                DamageTypes.SONIC_BOOM);
        this.tag(ModDamageTypeTags.IS_FORCE)
                .add(ModDamageTypes.FORCE_DAMAGE);
        this.tag(ModDamageTypeTags.IS_PSYCHIC)
                .add(ModDamageTypes.PSYCHIC_DAMAGE);
        this.tag(ModDamageTypeTags.IS_RADIANT)
                .add(ModDamageTypes.RADIANT_DAMAGE);
        this.tag(ModDamageTypeTags.IS_NECROTIC)
                .add(ModDamageTypes.NECROTIC_DAMAGE,
                DamageTypes.WITHER);
        this.tag(ModDamageTypeTags.IS_POISON)
                .add(ModDamageTypes.POISON_DAMAGE);

        this.tag(ModDamageTypeTags.IS_PHYSICAL)
                .add(DamageTypes.ARROW,
                DamageTypes.CACTUS,
                DamageTypes.FALL,
                DamageTypes.BAD_RESPAWN_POINT,
                DamageTypes.FALLING_ANVIL,
                DamageTypes.FALLING_BLOCK,
                DamageTypes.FALLING_STALACTITE,
                DamageTypes.FIREWORKS,
                DamageTypes.FLY_INTO_WALL,
                DamageTypes.MOB_ATTACK,
                DamageTypes.PLAYER_ATTACK,
                DamageTypes.MOB_PROJECTILE,
                DamageTypes.SPIT,
                DamageTypes.STALAGMITE,
                DamageTypes.STING,
                DamageTypes.SWEET_BERRY_BUSH,
                DamageTypes.THROWN,
                DamageTypes.TRIDENT);

        this.tag(DamageTypeTags.WITHER_IMMUNE_TO).add(ModDamageTypes.NECROTIC_DAMAGE);
        this.tag(DamageTypeTags.PANIC_CAUSES).addTags(ModDamageTypeTags.IS_ACID, ModDamageTypeTags.IS_THUNDER, ModDamageTypeTags.IS_RADIANT, ModDamageTypeTags.IS_NECROTIC);

        //minecraft compat

        this.tag(DamageTypeTags.IS_FIRE)
                .addTag(ModDamageTypeTags.IS_FIRE);
        this.tag(DamageTypeTags.IS_FREEZING)
                .addTag(ModDamageTypeTags.IS_COLD);
        this.tag(DamageTypeTags.IS_LIGHTNING)
                .addTag(ModDamageTypeTags.IS_LIGHTNING);

        //neoforge compat

        this.tag(Tags.DamageTypes.IS_WITHER).addTag(ModDamageTypeTags.IS_NECROTIC);
        this.tag(ModDamageTypeTags.IS_NECROTIC).addTag(Tags.DamageTypes.IS_WITHER);

        this.tag(Tags.DamageTypes.IS_POISON).addTag(ModDamageTypeTags.IS_POISON);
        this.tag(ModDamageTypeTags.IS_POISON).addTag(Tags.DamageTypes.IS_POISON);

        this.tag(Tags.DamageTypes.IS_PHYSICAL).addTag(ModDamageTypeTags.IS_PHYSICAL);
        this.tag(ModDamageTypeTags.IS_PHYSICAL).addTag(Tags.DamageTypes.IS_PHYSICAL);

    }

    public ModDamageTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, modId, existingFileHelper);
    }
}
