package com.badonnthedeer.ttrpg_craft.enchantment.custom;

import com.badonnthedeer.ttrpg_craft.registry.ModDamageTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record FirebrandEnchantmentEffect(LevelBasedValue damage) implements EnchantmentEntityEffect {
    public static final MapCodec<FirebrandEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(LevelBasedValue.CODEC.fieldOf("damage").forGetter(FirebrandEnchantmentEffect::damage))
                    .apply(instance, FirebrandEnchantmentEffect::new));

    public FirebrandEnchantmentEffect(LevelBasedValue damage){
        this.damage = damage;
    }

    @Override
    public void apply(ServerLevel serverLevel, int level, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        entity.hurt(new DamageSource(serverLevel.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.FIRE_DAMAGE), entity, enchantedItemInUse.owner()), this.damage.calculate(level));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

    public LevelBasedValue damage() {
        return this.damage;
    }
}
