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
                .add(ModDamageTypes.FIRE_DAMAGE,
                        DamageTypes.IN_FIRE,
                        DamageTypes.ON_FIRE,
                        DamageTypes.FIREBALL,
                        DamageTypes.HOT_FLOOR,
                        DamageTypes.LAVA,
                        DamageTypes.CAMPFIRE,
                        DamageTypes.UNATTRIBUTED_FIREBALL);
        this.tag(ModDamageTypeTags.IS_COLD)
                .add(ModDamageTypes.COLD_DAMAGE,
                        DamageTypes.FREEZE);
        this.tag(ModDamageTypeTags.IS_ACID)
                .add(ModDamageTypes.ACID_DAMAGE);
        this.tag(ModDamageTypeTags.IS_POISON)
                .add(ModDamageTypes.POISON_DAMAGE);
        this.tag(ModDamageTypeTags.IS_LIGHTNING)
                .add(ModDamageTypes.LIGHTNING_DAMAGE);
        this.tag(ModDamageTypeTags.IS_THUNDER)
                .add(ModDamageTypes.THUNDER_DAMAGE,
                        DamageTypes.PLAYER_EXPLOSION,
                        DamageTypes.EXPLOSION,
                        DamageTypes.SONIC_BOOM,
                        DamageTypes.BAD_RESPAWN_POINT);
        this.tag(ModDamageTypeTags.IS_FORCE)
                .add(ModDamageTypes.FORCE_DAMAGE);
        this.tag(ModDamageTypeTags.IS_PSYCHIC)
                .add(ModDamageTypes.PSYCHIC_DAMAGE);
        this.tag(ModDamageTypeTags.IS_RADIANT)
                .add(ModDamageTypes.RADIANT_DAMAGE);
        this.tag(ModDamageTypeTags.IS_NECROTIC)
                .add(ModDamageTypes.NECROTIC_DAMAGE,
                        DamageTypes.WITHER);

        this.tag(ModDamageTypeTags.IS_PHYSICAL)
                .add(DamageTypes.ARROW,
                        DamageTypes.CACTUS,
                        DamageTypes.FALL,
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
        this.tag(DamageTypeTags.PANIC_CAUSES)
                .addTags(ModDamageTypeTags.IS_FIRE,
                        ModDamageTypeTags.IS_COLD,
                        ModDamageTypeTags.IS_ACID,
                        ModDamageTypeTags.IS_POISON,
                        ModDamageTypeTags.IS_LIGHTNING,
                        ModDamageTypeTags.IS_THUNDER,
                        ModDamageTypeTags.IS_FORCE,
                        ModDamageTypeTags.IS_PSYCHIC,
                        ModDamageTypeTags.IS_RADIANT,
                        ModDamageTypeTags.IS_NECROTIC);

        //neoforge compat

        this.tag(ModDamageTypeTags.IS_NECROTIC).addTag(Tags.DamageTypes.IS_WITHER);

        this.tag(ModDamageTypeTags.IS_POISON).addTag(Tags.DamageTypes.IS_POISON);

        this.tag(ModDamageTypeTags.IS_PHYSICAL).addTag(Tags.DamageTypes.IS_PHYSICAL);

    }

    public ModDamageTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, modId, existingFileHelper);
    }
}
