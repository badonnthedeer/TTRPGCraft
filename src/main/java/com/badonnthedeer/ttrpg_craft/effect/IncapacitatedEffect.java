package com.badonnthedeer.ttrpg_craft.effect;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

public class IncapacitatedEffect extends MobEffect {
    protected IncapacitatedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        return true;
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(attributeMap, amplifier);

        AttributeModifier critVulnerableModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_crit_vulnerable_modifier"),
                1,
                AttributeModifier.Operation.ADD_VALUE
        );

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(TTRPGAttributes.MELEE_CRIT_VULNERABLE, critVulnerableModifier);

        attributeMap.addTransientAttributeModifiers(builder.build());
    }


    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);

        AttributeModifier critVulnerableModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_crit_vulnerable_modifier"),
                1,
                AttributeModifier.Operation.ADD_VALUE
        );

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(TTRPGAttributes.MELEE_CRIT_VULNERABLE, critVulnerableModifier);

        attributeMap.removeAttributeModifiers(builder.build());

    }



    public static boolean isIncapacitated(LivingEntity livingEntity) {
        if(livingEntity instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return false;
        }

        return livingEntity.hasEffect(ModEffects.INCAPACITATED_EFFECT);
    }

}
