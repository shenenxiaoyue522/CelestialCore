package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.effects.*;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2core.init.reg.registrate.PotionBuilder;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CCEffects {

    public static final SimpleEntry<MobEffect> CRIT_RATE = genEffect("crit_rate",
            () -> new AttributeEffect(0, 0xffd15720, L2DamageTracker.CRIT_RATE.holder(),
                    CelestialCore.loc("crit_rate"), 0.1f, AttributeModifier.Operation.ADD_VALUE), "Increase crit rate");
    public static final SimpleEntry<MobEffect> CRIT_DAMAGE = genEffect("crit_damage",
            () -> new AttributeEffect(0, 0xffe1a76c, L2DamageTracker.CRIT_DMG.holder(),
                    CelestialCore.loc("crit_damage"), 0.1f, AttributeModifier.Operation.ADD_VALUE), "Increase crit damage");
    public static final SimpleEntry<MobEffect> ARROW_DAMAGE = genEffect("arrow_damage",
            () -> new AttributeEffect(0, 0xffd18920, L2DamageTracker.BOW_STRENGTH.holder(),
                    CelestialCore.loc("arrow_damage"), 0.2f, AttributeModifier.Operation.ADD_VALUE), "Increase arrow damage");

    public static final SimpleEntry<MobEffect> VIOLENT = genEffect("violent",
            () -> new CelestialEffect(MobEffectCategory.NEUTRAL, 0xff841d1d), "Melee damage bypass armor");
    public static final SimpleEntry<MobEffect> SILENT = genEffect("silent",
            () -> new CelestialEffect(MobEffectCategory.NEUTRAL, 0xffffffff), "You won't make a vibration");
    public static final SimpleEntry<MobEffect> UNYIELDING = genEffect("unyielding",
            () -> new CelestialEffect(MobEffectCategory.BENEFICIAL, 0xfffff39b), "Stop you from dying");

    public static final SimpleEntry<MobEffect> HIDDEN = genEffect("hidden", Hidden::new, "Cannot be seen as enemy. Removed on attack");
    public static final SimpleEntry<MobEffect> ROTTEN_CURSE = genEffect("rotten_curse", RottenCurse::new, "Inflict damage after being attacked, prevents teleport");
    public static final SimpleEntry<MobEffect> BLADE_MODIFIER = genEffect("reinforced_blade", ReinforcedBlade::new, "Increase attack damage and crit rate");
    public static final SimpleEntry<MobEffect> SOUL_SHATTER = genEffect("soul_shatter", SoulShatter::new, "Reduce max health and movement speed");
    public static final SimpleEntry<MobEffect> FEAR_CURSE = genEffect("fear_curse", FearCurse::new, "You will get hurt when you attack");

    private static <T extends MobEffect> SimpleEntry<MobEffect> genEffect(String name, NonNullSupplier<T> sup, String desc) {
        RegistryEntry<MobEffect, T> entry = CelestialCore.REGISTRATE.effect(name, sup, desc).lang(MobEffect::getDescriptionId).register();
        return new SimpleEntry<>(entry);
    }

    private static final PotionBuilder POTION = new PotionBuilder(CelestialCore.REGISTRATE);

    static {
        POTION.regPotion2("crit_rate", CRIT_RATE.holder(), CCItems.OCEAN_ESSENCE::get, 6000, 9600);
        POTION.regPotion2("crit_damage", CRIT_DAMAGE.holder(), CCItems.FIRE_ESSENCE::get, 6000, 9600);
        POTION.regPotion2("arrow_damage", ARROW_DAMAGE.holder(), CCItems.LIGHT_FRAGMENT::get, 6000, 9600);
    }

    public static void register() {

    }

}