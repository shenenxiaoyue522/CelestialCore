package com.xiaoyue.celestial_core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xiaoyue.celestial_core.events.api.LivingJumpEvent;
import com.xiaoyue.celestial_core.register.CCEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(at = @At("HEAD"), method = "canBeSeenAsEnemy", cancellable = true)
    public void celestial_core$canBeSeenAsEnemy$hidden(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.hasEffect(CCEffects.HIDDEN.holder())) {
            cir.setReturnValue(false);
        }
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "getJumpPower*")
    public float celestial_core$getJumpPower(float original) {
        LivingEntity self = (LivingEntity) (Object) this;
        var event = new LivingJumpEvent(self, original);
        return NeoForge.EVENT_BUS.post(event).isCanceled() ? 0 : event.getJumpPower();
    }

}
