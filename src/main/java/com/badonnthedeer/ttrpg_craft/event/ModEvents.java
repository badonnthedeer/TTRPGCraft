package com.badonnthedeer.ttrpg_craft.event;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.common.entity.TTRPGAttributes;
import com.badonnthedeer.ttrpg_craft.effect.ModEffects;
import com.badonnthedeer.ttrpg_craft.network.ClearForcedPoseData;
import com.badonnthedeer.ttrpg_craft.registry.ModDamageTypeTags;
import com.badonnthedeer.ttrpg_craft.util.TTRPGAttribute;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.RAND;


@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents
{

    @SubscribeEvent
    public static void livingIncomingDamage(LivingIncomingDamageEvent event)
    {
        if(!event.getContainer().getSource().is(DamageTypes.GENERIC_KILL)) {
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
                double ttMod = TTRPGAttribute.getModifier(source, dmgAttr);
                float strScale = 1.0f;
                if (source instanceof Player player) {
                    strScale = player.getAttackStrengthScale(0.5f);
                }

                maxDmg = ((maxDmg * strScale) + ttMod);
                finalDmg = RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));
                float critRoll = RAND.nextInt(1, (100 + 1));
                boolean isCrit = false;

                if (target instanceof LivingEntity targetEntity) {
                    if (targetEntity.getAttribute(TTRPGAttributes.MELEE_CRIT_VULNERABLE) != null && targetEntity.getAttribute(TTRPGAttributes.MELEE_CRIT_VULNERABLE).getValue() == 1 && source.distanceTo(targetEntity) < 1.5) {
                        isCrit = true;
                    } else if (targetEntity.getAttribute(TTRPGAttributes.MELEE_CRIT_VULNERABLE) != null && targetEntity.getAttribute(TTRPGAttributes.MELEE_CRIT_VULNERABLE).getValue() == -1) {
                        isCrit = false;
                    } else {
                        isCrit = critRoll <= critAttr.getValue() && strScale == 1.0f;
                    }
                }
                ;

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
                if (source instanceof Player player) {
                    if (isCrit) {
                        player.sendSystemMessage(Component.literal(String.format("CRIT: %s: %.2f damage.", event.getEntity().getDisplayName().getString(), event.getAmount())));
                    } else {
                        player.sendSystemMessage(Component.literal(String.format("%s: %.2f damage.", event.getEntity().getDisplayName().getString(), event.getAmount())));
                    }
                }
            }
            //receiving environmental damage only
            else {
                minDmg = (int) Math.round(maxDmg / 6);
                finalDmg = RAND.nextInt(minDmg, (int) (Math.round(maxDmg) + 1));

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
                            && Objects.requireNonNull(targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() != 0)
                        finalDmg = (float) (finalDmg * (1 - (targetEntity.getAttribute(TTRPGAttributes.PHYSICAL_RESIST)).getValue() * 0.01f));
                }
            }
            event.setAmount(finalDmg);
        }
    }

    @SubscribeEvent
    public static void PostDamage(LivingDamageEvent.Post event)
    {
        if (event.getEntity() instanceof LivingEntity livingEntity)
        {
            if (livingEntity.hasEffect(ModEffects.UNCONSCIOUS_EFFECT) && livingEntity.getHealth() > .01f)
            {
                //in the case of a sleep spell, for example. Dying creatures will not be picked up by damage.
                livingEntity.removeEffect(ModEffects.UNCONSCIOUS_EFFECT);
            }
        }
        if (event.getSource().getEntity() instanceof Player player)
        {
            player.sendSystemMessage(Component.literal(String.format("%.2f -> %s %.2f", event.getOriginalDamage(), event.getSource().type().msgId(), event.getNewDamage())));
        }
    }

    @SubscribeEvent
    public static void onSoundPlay(PlayLevelSoundEvent event)
    {
        //do silence effect here, eventually
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event)
    {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        HandleEffectRemoval(event.getEntity(), instance);
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event)
    {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        HandleEffectRemoval(event.getEntity(), instance);
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

    public static void HandleEffectRemoval(LivingEntity entity, MobEffectInstance mobEffectInstance)
    {
        if (mobEffectInstance.getEffect().value() == ModEffects.PRONE_EFFECT.value()) {
            if (entity instanceof ServerPlayer player)
            {
                player.setForcedPose(null);
                if (!player.level().isClientSide) {
                    // On the SERVER, send packet telling clients “It’s gone now”
                    // Suppose the server code wants to tell everyone that "entity X is now up."
                    // We create the payload with the entity's ID and the new boolean
                    ClearForcedPoseData payload = new ClearForcedPoseData(player.getId(), true);

                    // Then send to all players (and self) tracking that entity
                    PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, payload);
                }
            }
            else
            {
                entity.setPose(Pose.STANDING);
            }
        }
        if (mobEffectInstance.getEffect().value() == ModEffects.UNCONSCIOUS_EFFECT.value()) {
            if(entity.hasEffect(ModEffects.INCAPACITATED_EFFECT)){
                entity.removeEffect(ModEffects.INCAPACITATED_EFFECT);
            }
            if(entity.hasEffect(ModEffects.PRONE_EFFECT)){
                entity.removeEffect(ModEffects.PRONE_EFFECT);
            }
            if(entity.hasEffect(ModEffects.DEAFENED_EFFECT)){
                entity.removeEffect(ModEffects.DEAFENED_EFFECT);
            }
            if(entity.hasEffect(MobEffects.BLINDNESS)){
                entity.removeEffect(MobEffects.BLINDNESS);
            }
        }
        if (mobEffectInstance.getEffect().value() == ModEffects.DEAFENED_EFFECT.value())
        {
            float masterVolumeInOptions = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.MASTER);
            if (entity instanceof Player player)
            {
                Minecraft.getInstance().getSoundManager().updateSourceVolume(SoundSource.MASTER, masterVolumeInOptions);
            }
        }
    }
}
