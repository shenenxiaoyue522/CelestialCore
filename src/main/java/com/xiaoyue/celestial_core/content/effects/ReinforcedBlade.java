package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ReinforcedBlade extends CelestialEffect {

    public ReinforcedBlade() {
        super(MobEffectCategory.BENEFICIAL, 0xffc6712b);
        ResourceLocation name = CelestialCore.loc("reinforced_blade");
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, name, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(L2DamageTracker.CRIT_RATE.holder(), name, 0.25f, AttributeModifier.Operation.ADD_VALUE);
    }
}
