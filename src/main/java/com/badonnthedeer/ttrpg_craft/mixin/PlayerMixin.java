package com.badonnthedeer.ttrpg_craft.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public class PlayerMixin {
        @ModifyVariable(method = "attack", at = @At(value = "STORE"), name = "flag1")
        private boolean changeCanCrit(boolean originalCanCrit) {
            return false;
        }
        //@ModifyVariable(method = "attack", at = @At(value = "STORE"), name = "f1")
        //private float changeCanMagCrit(boolean originalCanMagCrit) {return 0.0f; }
}
