package com.xiaoyue.celestial_core.mixin;

import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComposterBlock.class)
public abstract class ComposterMixin {

    @Shadow
    private static void add(float p_51921_, ItemLike p_51922_) {}

    @Inject(at = @At("HEAD"), method = "bootStrap")
    private static void celestial_core$injectComposter(CallbackInfo ci) {
        add(0.4f, CCItems.SAKURA_FRAGMENT.get());
    }
}
