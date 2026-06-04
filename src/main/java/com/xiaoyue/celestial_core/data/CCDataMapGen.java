package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateDataMapProvider;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCMaterials;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2damagetracker.init.data.ArmorImmunity;
import dev.xkmc.l2damagetracker.init.data.DTAttributeConfigGen;
import dev.xkmc.l2tabs.init.L2Tabs;
import dev.xkmc.l2tabs.init.data.AttrDispEntry;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;

public class CCDataMapGen {

    public static final TagKey<Item> IS_POTION = ItemTags.create(CelestialCore.loc("is_potion"));

    public static void onDataMapGen(RegistrateDataMapProvider pvd) {
        DataMapProvider.Builder<AttrDispEntry, Attribute> attr = pvd.builder(L2Tabs.ATTRIBUTE_ENTRY.reg());
        DTAttributeConfigGen.add(attr, CCAttributes.REGEN_RATE.key(), true, 25100);
        DTAttributeConfigGen.add(attr, CCAttributes.ARROW_SPEED.key(), true, 25200);
        DTAttributeConfigGen.add(attr, CCAttributes.ARROW_KNOCK.key(), false, 25300);
        DTAttributeConfigGen.add(attr, CCAttributes.ARMOR_PENETRATION.key(), true, 25400);
        DTAttributeConfigGen.add(attr, CCAttributes.TOUGHNESS_PENETRATION.key(), true, 25500);

        DataMapProvider.Builder<ArmorImmunity, ArmorMaterial> armor = pvd.builder(L2DamageTracker.ARMOR.reg());
        armor.add(CCMaterials.GUARDIAN_OCEAN.getArmorMaterial(), ArmorImmunity.of(false, MobEffects.DIG_SLOWDOWN.value()), false);
        armor.add(CCMaterials.SAKURA.getArmorMaterial(), ArmorImmunity.of(false, MobEffects.POISON.value()), false);
        armor.add(CCMaterials.VIRTUAL_GOLD.getArmorMaterial(), ArmorImmunity.of(false, MobEffects.WITHER.value()), false);
    }
}
