package com.badonnthedeer.ttrpg_craft.util;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public static class EntityTypes
    {
        //EntityTypeTags don't have a public way of adding tags. So I copied what it does and reformatted it for my purposes.
        //Let's hope this doesn't mess anything up.

        public static final TagKey<EntityType<?>> HAS_TTRPG_ATTRIBUTES = createTag("has_ttrpg_attributes");
        public static TagKey<EntityType<?>> createTag (String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, (ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, name)));
        }
    }
}
