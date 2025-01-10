package com.badonnthedeer.ttrpg_craft.network;

import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Handles IncapacitatedData on the server side.
 */
public class ServerPayloadHandler {

    public static void handleIsImmobileOnMain(IsImmobileData data, IPayloadContext context) {
        // This is called on the server main thread by default.
        // For example, the client might have requested "make me incapacitated."

        if (context.player() instanceof ServerPlayer sp) {
            ServerLevel serverLevel = sp.serverLevel(); // or sp.level()
            Entity entity = serverLevel.getEntity(data.entityId());
            if (entity instanceof LivingEntity living) {
                if (!data.isImmobile()) {
                    living.removeEffect(ModEffects.INCAPACITATED_EFFECT);
                } else {
                    living.addEffect(new MobEffectInstance(ModEffects.INCAPACITATED_EFFECT, 1, 0));
                }
            }
        }
    }
    public static void handleClearForcedPoseOnMain(ClearForcedPoseData data, IPayloadContext context) {
        // This is called on the server main thread by default.
        if (context.player() instanceof ServerPlayer sp) {
            ServerLevel serverLevel = sp.serverLevel(); // or sp.level()
            Entity entity = serverLevel.getEntity(data.entityId());
            if (entity instanceof Player player) {
                if (data.removePose()) {
                    player.setForcedPose(null);
                }
            }
        }
    }
}
