package com.badonnthedeer.ttrpg_craft.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.LOGGER;
import static com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes.STRENGTH;

public class ModClientEvents {

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event){
        BuiltInRegistries.ENTITY_TYPE.forEach((type) -> {
            try
            {
                LOGGER.debug("Successfully added STR to " + type.getDescriptionId());
                event.add((EntityType<? extends LivingEntity>) type, STRENGTH);
            }
            catch (Exception ignored)
            {
                LOGGER.warn("Failed to add STR to " + type.getDescriptionId());
            }
        });
    }
}
