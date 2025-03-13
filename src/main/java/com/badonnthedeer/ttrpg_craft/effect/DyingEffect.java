package com.badonnthedeer.ttrpg_craft.effect;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DyingEffect extends MobEffect{
    protected DyingEffect(MobEffectCategory category, int color) {
        super(category, color);

    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier)
    {
        if (entity instanceof Player player)
        {
            player.getFoodData().setFoodLevel(17);
            player.getFoodData().setSaturation(0);
        }
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.hasEffect(ModEffects.UNCONSCIOUS_EFFECT))
        {
            entity.addEffect(new MobEffectInstance(ModEffects.UNCONSCIOUS_EFFECT, Integer.MAX_VALUE, 0, false, false,true));
        }
        if (entity.getHealth() >= 0.9f)
        {
            entity.removeEffect(ModEffects.DYING_EFFECT);
            entity.removeEffect(ModEffects.UNCONSCIOUS_EFFECT);
        }
        return true;
    }
}
