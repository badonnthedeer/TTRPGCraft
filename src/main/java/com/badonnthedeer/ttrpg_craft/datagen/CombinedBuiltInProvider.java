package com.badonnthedeer.ttrpg_craft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DeathMessageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.badonnthedeer.ttrpg_craft.registry.DamageTypes.FIRE_DAMAGE;

public class CombinedBuiltInProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, bootstrap -> {
                // Use new DamageType() to create an in-code representation of a damage type.
                // The parameters map to the values of the JSON file, in the order seen above.
                // All parameters except for the message id and the exhaustion value are optional.
                bootstrap.register(FIRE_DAMAGE, new DamageType(FIRE_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
            });

    public CombinedBuiltInProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, RegistrySetBuilder datapackEntriesBuilder, Set<String> modIds) {
        super(output, registries, datapackEntriesBuilder, modIds);
    }
}
