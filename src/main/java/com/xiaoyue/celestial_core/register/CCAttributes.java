package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2damagetracker.contents.curios.FactorAttribute;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class CCAttributes {

    public static final SimpleEntry<Attribute> ARROW_SPEED = L2DamageTracker.reg(CelestialCore.REGISTRATE,
            "arrow_speed", s -> new FactorAttribute(s, 0f, 0f, 1000f), "Arrow Speed");
    public static final SimpleEntry<Attribute> ARROW_KNOCK = L2DamageTracker.reg(CelestialCore.REGISTRATE,
            "arrow_knock", s -> new RangedAttribute(s, 0, 0, 1000), "Arrow Knock Back");
    public static final SimpleEntry<Attribute> ARMOR_PENETRATION = L2DamageTracker.regPerc(CelestialCore.REGISTRATE,
            "armor_penetration", "Armor Penetration");
    public static final SimpleEntry<Attribute> TOUGHNESS_PENETRATION = L2DamageTracker.regPerc(CelestialCore.REGISTRATE,
            "toughness_penetration", "Toughness Penetration");

    public static void register() {

    }

}