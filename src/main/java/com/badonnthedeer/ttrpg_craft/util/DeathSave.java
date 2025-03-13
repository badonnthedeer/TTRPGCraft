package com.badonnthedeer.ttrpg_craft.util;

import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.Mod;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.RAND;
import static com.badonnthedeer.ttrpg_craft.attachment.TTRPGAttachments.DEATH_SAVE_FAILURES;
import static com.badonnthedeer.ttrpg_craft.attachment.TTRPGAttachments.DEATH_SAVE_SUCCESSES;

public class DeathSave {
    public static void RollDeathSave(Player player)
    {
        int save = RAND.nextInt(1, 21);
        int successes = player.getData(DEATH_SAVE_SUCCESSES);
        int fails = player.getData(DEATH_SAVE_FAILURES);
        //based on SRD's 1d4 hours of unconsciousness after stabilization. minecraft day is 20 real minutes.
        //a real day has  1440 minutes. 20 / 1440 minutes = a reduction to 0.01389% normal time
        //1 hr = 3600 seconds, * 0.01289 = 50 seconds. ~50 seconds =  1 minecraft hour. Therefore,
        //1d4 hours of unconsciousness = 50-200 seconds of unconsciousness in minecraft.
        int unconscious_duration = RAND.nextInt(500, 2010);
        boolean isSuccess = ((float) save / 10) > 1f;

        if(save <= 1)
        {
            player.sendSystemMessage(Component.literal("CRITICAL Death Save failure!"));
            player.setData(DEATH_SAVE_FAILURES, fails + 2);
            player.die(player.getLastDamageSource() != null ? player.getLastDamageSource() : player.level().damageSources().generic());
        }
        else if(save >= 20)
        {
            player.setData(DEATH_SAVE_SUCCESSES, 0);
            player.setData(DEATH_SAVE_FAILURES, 0);
            player.setHealth(1);
            if(player.hasEffect(ModEffects.DYING_EFFECT))
            {
                player.removeEffect(ModEffects.DYING_EFFECT);
            }
            if(player.hasEffect(ModEffects.UNCONSCIOUS_EFFECT))
            {
                player.removeEffect(ModEffects.UNCONSCIOUS_EFFECT);
            }
            player.sendSystemMessage(Component.literal("CRITICAL Death Save success!"));
        }
        else if(!isSuccess)
        {
            player.setData(DEATH_SAVE_FAILURES, fails + 1);
            player.die(player.getLastDamageSource() != null ? player.getLastDamageSource() : player.level().damageSources().generic());
        }
        else if(isSuccess)
        {
            if(successes < 2)
            {
                player.setData(DEATH_SAVE_SUCCESSES, successes + 1);
                player.sendSystemMessage(Component.literal(String.format("Death Save successes: %s", successes + 1)));
            }
            else if(successes >= 2)
            {
                player.setData(DEATH_SAVE_SUCCESSES, 0);
                player.setData(DEATH_SAVE_FAILURES, 0);
                player.setHealth(1);
                player.sendSystemMessage(Component.literal(String.format("Death Save successes: %s. Stabilized!", successes + 1)));
                if (player.hasEffect(ModEffects.DYING_EFFECT))
                {
                    player.removeEffect(ModEffects.DYING_EFFECT);
                }
                if (player.hasEffect(ModEffects.UNCONSCIOUS_EFFECT))
                {
                    player.removeEffect(ModEffects.UNCONSCIOUS_EFFECT);
                }
                player.addEffect(new MobEffectInstance(ModEffects.UNCONSCIOUS_EFFECT, unconscious_duration, 0, false, false, true));
            }
        }
        //in case of drowning. GetLastDamageSource becomes null, so we can't check it.
        player.setAirSupply(player.getMaxAirSupply());
    }
}
