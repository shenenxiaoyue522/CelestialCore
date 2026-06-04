package com.xiaoyue.celestial_core.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttributeEffect extends CelestialEffect {
    public AttributeEffect(MobEffectCategory pCategory, int pColor, Holder<Attribute> attr, ResourceLocation name, double amount, AttributeModifier.Operation op) {
        super(pCategory, pColor);
        this.addAttributeModifier(attr, name, amount, op);
    }

    public AttributeEffect(int i, int pColor, Holder<Attribute> attr, ResourceLocation name, double amount, AttributeModifier.Operation op) {
        super(CelestialEffect.getCategory(i), pColor);
        this.addAttributeModifier(attr, name, amount, op);
    }
}
