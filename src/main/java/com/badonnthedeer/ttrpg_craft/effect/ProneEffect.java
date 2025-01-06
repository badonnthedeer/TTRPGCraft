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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;


public class ProneEffect extends MobEffect {

    public ProneEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // poses cleared in ModEvents.OnEffectExpired and ModEvents.OnEffectRemoved
            // swimming for entities expires on their own, but removePose anyway
            if (player.hasEffect(ModEffects.PRONE_EFFECT))
            {
                if (player.getForcedPose() == null)
                {
                    player.setForcedPose(Pose.SWIMMING);
                }
            }
        }
        else
        {
            entity.setPose(Pose.SWIMMING);
        }
        return true;
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(attributeMap, amplifier);

        AttributeModifier speedModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_movement_modifier"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        AttributeModifier damageModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_damage_modifier"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        AttributeModifier critVulnerableModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_crit_vulnerable_modifier"),
                1,
                AttributeModifier.Operation.ADD_VALUE
        );

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MOVEMENT_SPEED, speedModifier);
        builder.put(Attributes.ATTACK_DAMAGE, damageModifier);
        builder.put(TTRPGAttributes.MELEE_CRIT_VULNERABLE, critVulnerableModifier);

        attributeMap.addTransientAttributeModifiers(builder.build());
    }


    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);

        AttributeModifier speedModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_movement_modifier"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        AttributeModifier damageModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_damage_modifier"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        AttributeModifier critVulnerableModifier = new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "prone_crit_vulnerable_modifier"),
                1,
                AttributeModifier.Operation.ADD_VALUE
        );

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MOVEMENT_SPEED, speedModifier);
        builder.put(Attributes.ATTACK_DAMAGE, damageModifier);
        builder.put(TTRPGAttributes.MELEE_CRIT_VULNERABLE, critVulnerableModifier);

        attributeMap.removeAttributeModifiers(builder.build());
    }
}
