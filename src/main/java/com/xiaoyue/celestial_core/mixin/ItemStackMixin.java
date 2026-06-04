package com.xiaoyue.celestial_core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.xiaoyue.celestial_core.events.api.DamageItemEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;setDamageValue(I)V"), method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V")
    public void celestial_core$hurtAndBroken(ItemStack instance, int damage, Operation<Void> original, @Local(argsOnly = true) LivingEntity entity) {
        var event = new DamageItemEvent(entity, instance, damage);
        original.call(instance, NeoForge.EVENT_BUS.post(event).getAmount());
    }
}