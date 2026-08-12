package com.xiaoyue.celestial_core.content.generic;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class CelestialEffect extends MobEffect {
    public CelestialEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return true;
    }

    public void onExpired(MobEffectInstance instance, LivingEntity entity) {
    }

    public static MobEffectCategory getCategory(int i) {
        return switch (i) {
            case 0 -> MobEffectCategory.BENEFICIAL;
            case 1 -> MobEffectCategory.NEUTRAL;
            case 2 -> MobEffectCategory.HARMFUL;
            default -> throw new IllegalStateException("unknown mobEffect category value: " + i);
        };
    }
}
