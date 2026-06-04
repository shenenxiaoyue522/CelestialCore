package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Hidden extends CelestialEffect {
    public Hidden() {
        super(MobEffectCategory.BENEFICIAL, 0xff373737);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, CelestialCore.loc("hidden"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return false;
    }
}
