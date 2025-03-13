package com.badonnthedeer.ttrpg_craft.attachment;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.effect.DeafenedEffect;
import com.badonnthedeer.ttrpg_craft.effect.IncapacitatedEffect;
import com.badonnthedeer.ttrpg_craft.effect.ProneEffect;
import com.badonnthedeer.ttrpg_craft.effect.UnconsciousEffect;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class TTRPGAttachments {
    public static final DeferredRegister<AttachmentType<?>> TTRPG_ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TTRPGCraft.MOD_ID);

    public static final Supplier<AttachmentType<Integer>> DEATH_SAVE_SUCCESSES = TTRPG_ATTACHMENTS.register(
            "death_save_successes", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());

    public static final Supplier<AttachmentType<Integer>> DEATH_SAVE_FAILURES = TTRPG_ATTACHMENTS.register(
            "death_save_failures", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());


    public static void register(IEventBus eventBus) {
        TTRPG_ATTACHMENTS.register(eventBus);
    }
}
