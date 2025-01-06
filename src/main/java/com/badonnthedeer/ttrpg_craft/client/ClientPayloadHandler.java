package com.badonnthedeer.ttrpg_craft.client;

import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import com.badonnthedeer.ttrpg_craft.network.ClearForcedPoseData;
import com.badonnthedeer.ttrpg_craft.network.IsImmobileData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;


/**
 * Handles IncapacitatedData when it arrives on the client side.
 */
public class ClientPayloadHandler {

    /**
     * Called on the main game thread by default (unless you configure the registrar otherwise).
     */
    public static void handleIsImmobileOnMain(IsImmobileData data, IPayloadContext context) {
        // We'll assume the server is telling us "Entity X is (or isn't) incapacitated."
        // On the client, we update the entity's effect or a custom capability.

        Level level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }

        Entity entity = level.getEntity(data.entityId());
        if (entity instanceof LivingEntity living) {
            if (!data.isImmobile()) {
                // Remove the incapacitated effect (or set capability to false)
                living.removeEffect(ModEffects.INCAPACITATED_EFFECT);
            } else {
                // Add or refresh the effect on the client side
                living.forceAddEffect(new MobEffectInstance(ModEffects.INCAPACITATED_EFFECT, 600, 0), null);
            }
        }
    }

    public static void handleClearForcedPoseOnMain(ClearForcedPoseData data, IPayloadContext context) {

        Level level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }

        Entity entity = level.getEntity(data.entityId());
        if (entity instanceof Player player) {
            if (data.removePose()) {
               player.setForcedPose(null);
            }
        }
    }
}