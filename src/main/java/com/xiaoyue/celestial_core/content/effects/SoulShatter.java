package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SoulShatter extends CelestialEffect {
    public SoulShatter() {
        super(MobEffectCategory.HARMFUL, 0xff1d6b84);
        ResourceLocation name = CelestialCore.loc("soul_shatter");
        this.addAttributeModifier(Attributes.MAX_HEALTH, name, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, name, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}
