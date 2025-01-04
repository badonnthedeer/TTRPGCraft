package com.badonnthedeer.ttrpg_craft.mixin;

import com.badonnthedeer.ttrpg_craft.client.LocalPlayerImmobileHandFix;
import com.badonnthedeer.ttrpg_craft.effect.IncapacitatedEffect;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 1200)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> mobEffect);

    @Shadow
    public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow
    public abstract AttributeMap getAttributes();

    @Shadow
    protected abstract boolean isImmobile();

    @Shadow
    public float yHeadRot;

    @Shadow
    @Final
    private AttributeMap attributes;

   @ModifyReturnValue(method = "isImmobile()Z",
            at = @At(value = "RETURN"))
    private boolean ttrpgcraft$isIncapacitated(boolean isImmobile) {
        return isImmobile || IncapacitatedEffect.isIncapacitated((LivingEntity) (Object) this);
    }

    @Inject(method = "aiStep",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isImmobile()Z"),
            require = 0)
    private void ttrpgcraft$fixHeadHandImmobileRot(CallbackInfo ci) {
        if (this.isImmobile() && IncapacitatedEffect.isIncapacitated((LivingEntity)(Object)this)) {
            this.yHeadRot = this.getYRot();
            if (this.level().isClientSide) {
                LocalPlayerImmobileHandFix.handleArms((LivingEntity) (Object) this);
            }
        }
    }
}