package com.badonnthedeer.ttrpg_craft.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;

public class LocalPlayerImmobileHandFix {
//thanks to the bumblezone mod!
    public static void handleArms(LivingEntity livingEntity) {
        if (livingEntity instanceof LocalPlayer localPlayer) {
            localPlayer.yBobO = localPlayer.yBob;
            localPlayer.xBobO = localPlayer.xBob;
            localPlayer.xBob += (localPlayer.getXRot() - localPlayer.xBob) * 0.5f;
            localPlayer.yBob += (localPlayer.getYRot() - localPlayer.yBob) * 0.5f;
        }
    }
}
