package com.badonnthedeer.ttrpg_craft.effect;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UnconsciousEffect extends MobEffect {
    protected UnconsciousEffect(MobEffectCategory category, int color) {
        super(category, color);

    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.hasEffect(ModEffects.INCAPACITATED_EFFECT))
        {
            entity.addEffect(new MobEffectInstance(ModEffects.INCAPACITATED_EFFECT, Integer.MAX_VALUE, 1, false, false,true));
        }
        if (!entity.hasEffect(ModEffects.PRONE_EFFECT))
        {
            entity.addEffect(new MobEffectInstance(ModEffects.PRONE_EFFECT, Integer.MAX_VALUE, 1, false, false,false));
        }
        if (!entity.hasEffect(ModEffects.DEAFENED_EFFECT))
        {
            entity.addEffect(new MobEffectInstance(ModEffects.DEAFENED_EFFECT, Integer.MAX_VALUE, 1, false, false,false));
        }
        if (!entity.hasEffect(MobEffects.BLINDNESS))
        {
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, Integer.MAX_VALUE, 1, false, false,false));
        }
        if(entity.getMainHandItem() != ItemStack.EMPTY || entity.getOffhandItem() != ItemStack.EMPTY){
            if(entity instanceof Player player){
                if (player.getMainHandItem() != ItemStack.EMPTY)
                {
                    player.drop(player.getMainHandItem(), true);
                }
                if (player.getOffhandItem() != ItemStack.EMPTY) {
                    player.drop(player.getOffhandItem(), true);
                }
            }
            //if you let other entities drop their items, it seems ripe for exploitation. See skeleton arrows not being pickupable.
        }
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
