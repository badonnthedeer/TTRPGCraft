package com.badonnthedeer.ttrpg_craft.event;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.LOGGER;
import static com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes.STRENGTH;


@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents
{

    @SubscribeEvent
    public static void livingIncomingDamage(LivingIncomingDamageEvent event)
    {
        if (event.getSource().getEntity() instanceof Player entity)
        {
            DamageContainer dmg = event.getContainer();
            ItemStack weapon = entity.getWeaponItem();
            float minDmg = (float) Math.max((((entity.getAttribute(TTRPGAttributes.STRENGTH).getValue() / 2) - 5)), 0);
            float maxDmg = dmg.getOriginalDamage() + (float) ((entity.getAttribute(TTRPGAttributes.STRENGTH).getValue() / 2) - 5);
            if(weapon != null)
            {
                event.setAmount((float) ThreadLocalRandom.current().nextFloat(minDmg, maxDmg));
            }
            else
            {
                event.setAmount((float) ThreadLocalRandom.current().nextFloat(minDmg, maxDmg));
            }
            if(entity instanceof Player player){
                player.sendSystemMessage(Component.literal(event.getAmount() + " damage."));
            }
        }

    }
}
