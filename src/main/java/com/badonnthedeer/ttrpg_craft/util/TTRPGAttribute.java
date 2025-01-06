package com.badonnthedeer.ttrpg_craft.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;

public class TTRPGAttribute {

    public static double getModifier(LivingEntity entity, AttributeInstance instance)
    {
        return ((instance.getValue() / 2) -5) / 4;
    }

}
