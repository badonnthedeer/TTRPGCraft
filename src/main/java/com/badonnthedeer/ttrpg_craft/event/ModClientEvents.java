package com.badonnthedeer.ttrpg_craft.event;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.LOGGER;
import static com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes.STRENGTH;

@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModClientEvents {

    @SubscribeEvent
    public static void OnSoundPlay(PlaySoundEvent event){
        //do i need to check if it's a local player here?
        Player player = Minecraft.getInstance().player;
        if(player != null && player.hasEffect(ModEffects.DEAFENED_EFFECT))
        {
            //event.setSound(null);
            event.getEngine().updateCategoryVolume(SoundSource.MASTER, 0.001f);
        }
    }
}
