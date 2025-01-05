package com.badonnthedeer.ttrpg_craft.datagen;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DeathMessageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraft.network.codec.IdDispatchCodec;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.badonnthedeer.ttrpg_craft.registry.ModDamageTypes.*;

public class CombinedBuiltInProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, bootstrap -> {
                // Use new DamageType() to create an in-code representation of a damage type.
                // The parameters map to the values of the JSON file, in the order seen above.
                // All parameters except for the message id and the exhaustion value are optional.
                bootstrap.register(FIRE_DAMAGE, new DamageType(FIRE_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.BURNING,
                        DeathMessageType.DEFAULT));
                bootstrap.register(COLD_DAMAGE, new DamageType(COLD_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.FREEZING,
                        DeathMessageType.DEFAULT));
                bootstrap.register(ACID_DAMAGE, new DamageType(ACID_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.BURNING,
                        DeathMessageType.DEFAULT));
                bootstrap.register(POISON_DAMAGE, new DamageType(POISON_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(LIGHTNING_DAMAGE, new DamageType(LIGHTNING_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(THUNDER_DAMAGE, new DamageType(THUNDER_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(FORCE_DAMAGE, new DamageType(FORCE_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(PSYCHIC_DAMAGE, new DamageType(PSYCHIC_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(RADIANT_DAMAGE, new DamageType(RADIANT_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(NECROTIC_DAMAGE, new DamageType(NECROTIC_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
                bootstrap.register(PHYSICAL_DAMAGE, new DamageType(PHYSICAL_DAMAGE.location().toString(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.HURT,
                        DeathMessageType.DEFAULT));
            })
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public CombinedBuiltInProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TTRPGCraft.MOD_ID));
    }

}
