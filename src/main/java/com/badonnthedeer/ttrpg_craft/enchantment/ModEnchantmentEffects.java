package com.badonnthedeer.ttrpg_craft.enchantment;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.enchantment.custom.FlametongueEnchantmentEffect;
import com.badonnthedeer.ttrpg_craft.enchantment.custom.FrostbrandEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, TTRPGCraft.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FLAMETONGUE =
            registerEnchantmentEffect("flametongue", FlametongueEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FROSTBRAND =
            registerEnchantmentEffect("frostbrand", FrostbrandEnchantmentEffect.CODEC);

    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> registerEnchantmentEffect(String name,
                                                                                                   MapCodec<? extends EnchantmentEntityEffect> codec) {
        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
