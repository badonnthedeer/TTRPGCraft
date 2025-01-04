package com.badonnthedeer.ttrpg_craft.event;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import com.badonnthedeer.ttrpg_craft.registry.ModDamageTypeTags;
import com.badonnthedeer.ttrpg_craft.util.TTRPGAttribute;
import com.llamalad7.mixinextras.sugar.Cancellable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Objects;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.RAND;


@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents
{

    @SubscribeEvent
    public static void livingIncomingDamage(LivingIncomingDamageEvent event)
    {
        DamageContainer dmg = event.getContainer();
        Entity target = event.getEntity();
        int minDmg = 0;
        double maxDmg = dmg.getOriginalDamage();
        float finalDmg = (float) maxDmg;

        //see player.attack
        if (event.getSource().getEntity() instanceof LivingEntity source) {

            ItemStack weapon = source.getWeaponItem();
            AttributeInstance dmgAttr = source.getAttribute(TTRPGAttributes.STRENGTH);
            AttributeInstance critAttr = source.getAttribute(TTRPGAttributes.CRIT_CHANCE);
            double ttMod = TTRPGAttribute.getTTModifier(dmgAttr);
            float strScale = 1.0f;
            if (source instanceof Player player) {
                strScale = player.getAttackStrengthScale(0.5f);
            }

            maxDmg = ((maxDmg * strScale) + ttMod);
            finalDmg = RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));
            float critRoll = RAND.nextInt(1, (100 + 1));
            boolean isCrit = false;

            if (target instanceof LivingEntity targetEntity)
            {
                if(targetEntity.getAttribute(TTRPGAttributes.CRIT_VULNERABLE) != null && targetEntity.getAttribute(TTRPGAttributes.CRIT_VULNERABLE).getValue() == 1)
                {
                    isCrit = true;
                }
                else if(targetEntity.getAttribute(TTRPGAttributes.CRIT_VULNERABLE) != null && targetEntity.getAttribute(TTRPGAttributes.CRIT_VULNERABLE).getValue() == -1)
                {
                    isCrit = false;
                }
                else
                {
                    isCrit = critRoll <= critAttr.getValue() && strScale == 1.0f;
                }
            };

            if (isCrit) {
                finalDmg += RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));

                target.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, target.getSoundSource(), 1.0F, 1.0F);
                if (target.level() instanceof ServerLevel) {
                    ((ServerLevel) target.level())
                            .sendParticles(ParticleTypes.CRIT, target.getX(), target.getY(0.5), target.getZ(), (int) finalDmg, 0.3, 0.1, 0.3, 0.8);
                }
            }

            if (target instanceof LivingEntity targetEntity) {
                if (dmg.getSource().is(ModDamageTypeTags.IS_FIRE) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.FIRE_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.FIRE_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_COLD) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.COLD_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.COLD_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_LIGHTNING) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.LIGHTNING_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.LIGHTNING_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_THUNDER) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.THUNDER_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.THUNDER_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_ACID) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.ACID_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.ACID_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_POISON) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.POISON_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.POISON_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_FORCE) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.FORCE_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.FORCE_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_PSYCHIC) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.PSYCHIC_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.PSYCHIC_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_RADIANT) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.RADIANT_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.RADIANT_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_NECROTIC) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.NECROTIC_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.NECROTIC_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_PHYSICAL)
                        && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() != 0
                        && !weapon.isEnchanted()
                        && !(weapon == ItemStack.EMPTY && Objects.requireNonNull(source.getAttribute(TTRPGAttributes.MAGICAL_UNARMED_ATTACKS)).getValue() == 1)) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() * 0.01f));
                }
            }
            if(source instanceof Player player)
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
        //receiving environmental damage only
        else
        {
            minDmg = (int) Math.round(maxDmg / 6);
            finalDmg = RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));

            if (target instanceof LivingEntity targetEntity)
            {
                if (dmg.getSource().is(ModDamageTypeTags.IS_FIRE) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.FIRE_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.FIRE_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_COLD) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.COLD_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.COLD_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_LIGHTNING) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.LIGHTNING_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.LIGHTNING_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_THUNDER) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.THUNDER_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.THUNDER_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_ACID) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.ACID_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.ACID_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_POISON) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.POISON_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.POISON_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_FORCE) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.FORCE_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.FORCE_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_PSYCHIC) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.PSYCHIC_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.PSYCHIC_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_RADIANT) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.RADIANT_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.RADIANT_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_NECROTIC) && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.NECROTIC_RESIST)).getValue() != 0) {
                    finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.NECROTIC_RESIST)).getValue() * 0.01f));
                } else if (dmg.getSource().is(ModDamageTypeTags.IS_PHYSICAL)
                        && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() != 0)
                         finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() * 0.01f));
            }
        }
        event.setAmount(finalDmg);

    }

    @SubscribeEvent
    public static void PostDamage(LivingDamageEvent.Post event)
    {
        if (event.getSource().getEntity() instanceof Player player)
        {
            player.sendSystemMessage(Component.literal(String.format("%.2f -> %s %.2f", event.getOriginalDamage(), event.getSource().type().msgId(), event.getNewDamage())));
        }
    }

    @SubscribeEvent
    public static void PlayerEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if(event.getEntity().hasEffect(ModEffects.INCAPACITATED_EFFECT))
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void LeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        if(event.getEntity().hasEffect(ModEffects.INCAPACITATED_EFFECT))
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void RightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if(event.getEntity().hasEffect(ModEffects.INCAPACITATED_EFFECT))
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void PlayerAttack(AttackEntityEvent event)
    {
        if(event.getEntity().hasEffect(ModEffects.INCAPACITATED_EFFECT))
        {
            event.setCanceled(true);
        }
    }
}
