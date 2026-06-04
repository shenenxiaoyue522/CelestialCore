package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractArrow.class)
public class AbstractArrowInvoker {

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;modifyKnockback(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;F)F"), method = "doKnockback", index = 4)
    public float celestial_core$setArrowKnock(float knockback) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.getOwner() instanceof Player player && !arrow.getTags().contains(CelestialFlags.ARROW_KNOCK)) {
            double ak = player.getAttributeValue(CCAttributes.ARROW_KNOCK.holder());
            knockback += (float) ak;
            arrow.addTag(CelestialFlags.ARROW_KNOCK);
        }
        return knockback;
    }
}
