package com.badonnthedeer.ttrpg_craft.event;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.badonnthedeer.ttrpg_craft.util.TTRPGAttribute;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.RAND;
import static net.neoforged.neoforge.common.CommonHooks.fireCriticalHit;


@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents
{

    @SubscribeEvent
    public static void livingIncomingDamage(LivingIncomingDamageEvent event)
    {
        //see player.attack
        if (event.getSource().getEntity() instanceof Player playerEntity)
        {
            //event.
            DamageContainer dmg = event.getContainer();
            ItemStack weapon = playerEntity.getWeaponItem();
            Entity target = event.getEntity();
            AttributeInstance dmgAttr = playerEntity.getAttribute(TTRPGAttributes.STRENGTH);
            AttributeInstance critAttr = playerEntity.getAttribute(TTRPGAttributes.CRIT_CHANCE);
            double ttMod = TTRPGAttribute.getTTModifier(dmgAttr);
            float strScale = playerEntity.getAttackStrengthScale(0.5F);
            int minDmg =  0;
            double maxDmg = ((dmg.getOriginalDamage() * strScale) + ttMod);
            float finalDmg = RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));
            float critRoll = RAND.nextInt(1, (100 + 1));
            boolean isCrit = critRoll <= critAttr.getValue() && strScale == 1.0f;

            var critEvent = fireCriticalHit(playerEntity, event.getEntity(), false, 1.0f);
            critEvent.setCriticalHit(false);
            if(isCrit)
            {
                critEvent.setCriticalHit(true);
                finalDmg += RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));
                event.setAmount(finalDmg);
                target.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, target.getSoundSource(), 1.0F, 1.0F);
                if (target.level() instanceof ServerLevel) {
                    ((ServerLevel)target.level())
                            .sendParticles(ParticleTypes.CRIT, target.getX(), target.getY(0.5), target.getZ(), (int) finalDmg, 0.3, 0.0, 0.3, 0.4);
                }
            }
            else
            {
                event.setAmount(finalDmg);
            }
            if(playerEntity instanceof Player player)
            {
                if(isCrit)
                {
                    player.sendSystemMessage(Component.literal(String.format("CRIT: %s: %.2f damage.", event.getEntity().getDisplayName().getString(), event.getAmount())));
                }
                else
                {
                    player.sendSystemMessage(Component.literal(String.format("%s: %.2f damage.", event.getEntity().getDisplayName().getString(), event.getAmount())));
                }
            }
        }

    }

    @SubscribeEvent
    public static void PostDamage(LivingDamageEvent.Post event)
    {
        if (event.getSource().getEntity() instanceof Player player)
        {
            player.sendSystemMessage(Component.literal(String.format("%.2f -> %.2f", event.getOriginalDamage(), event.getNewDamage())));
        }
    }
}
