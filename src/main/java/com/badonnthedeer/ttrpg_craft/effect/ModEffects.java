package com.badonnthedeer.ttrpg_craft.effect;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TTRPGCraft.MOD_ID);

    public static final Holder<MobEffect> INCAPACITATED_EFFECT = MOB_EFFECTS.register("incapacitated",
            () -> new IncapacitatedEffect(MobEffectCategory.HARMFUL, 0x36ebab));

    public static final Holder<MobEffect> PRONE_EFFECT = MOB_EFFECTS.register("prone",
            () -> new ProneEffect(MobEffectCategory.HARMFUL, 0x36ebab));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
