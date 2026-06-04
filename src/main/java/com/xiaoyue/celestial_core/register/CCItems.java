package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_core.content.generic.CustomNameColorItem;
import com.xiaoyue.celestial_core.content.items.LootTableBox;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class CCItems {

    public static final BlockEntry<Block> SAKURA_STEEL_BLOCK, GUARDIAN_OCEAN_BLOCK, VIRTUAL_GOLD_BLOCK;

    public static final ItemEntry<CCTooltipItem> TREASURE_FRAGMENT, VOID_ESSENCE, FIRE_ESSENCE, OCEAN_ESSENCE,
            WARDEN_SCLERITE, LIGHT_FRAGMENT, MIDNIGHT_FRAGMENT, DEATH_ESSENCE, PURE_NETHER_STAR, SHULKER_SCRAP,
            SOARING_WINGS, HEART_FRAGMENT, SAKURA_FRAGMENT, GUARDIAN_SPIKE, BROKEN_TOTEM;

    public static final ItemEntry<Item> EARTH_CORE, SAKURA_STEEL, SAKURA_STEEL_NUGGET, OCEAN_INGOT, GUARDIAN_OCEAN_INGOT,
            GUARDIAN_OCEAN_NUGGET, VIRTUAL_GOLD_INGOT, VIRTUAL_GOLD_NUGGET;

    public static final ItemEntry<LootTableBox> JUNGLE_PYRAMID_LOOT_BOX, DESERT_PYRAMID_LOOT_BOX,
            IGLOO_LOOT_BOX, MANSON_LOOT_BOX, PILLAGER_OUTPOST_LOOT_BOX;

    public static final ItemEntry<Item>[][] GEN_ITEM;

    static {
        CelestialCore.REGISTRATE.defaultCreativeTab(CelestialCore.TAB.getKey());
        SAKURA_STEEL_BLOCK = CelestialCore.REGISTRATE.block("sakura_steel_block", p -> new Block(p.strength(5f)))
                .item((b, p) -> new BlockItem(b, p.rarity(Rarity.EPIC))).build().defaultLoot().defaultLang().register();
        GUARDIAN_OCEAN_BLOCK = CelestialCore.REGISTRATE.block("guardian_ocean_block", p -> new Block(p.strength(6f)))
                .item((b, p) -> new BlockItem(b, p.rarity(Rarity.RARE))).build().defaultLoot().defaultLang().register();
        VIRTUAL_GOLD_BLOCK = CelestialCore.REGISTRATE.block("virtual_gold_block", p -> new Block(p.strength(7f)))
                .item((b, p) -> new BlockItem(b, p.rarity(Rarity.EPIC))).build().defaultLoot().defaultLang().register();
        GUARDIAN_OCEAN_INGOT = material("guardian_ocean_ingot", p -> new CustomNameColorItem(ChatFormatting.BLUE));
        OCEAN_INGOT = material("ocean_ingot", p -> new CustomNameColorItem(ChatFormatting.BLUE));
        SAKURA_STEEL = material("sakura_steel", p -> new Item(p.rarity(Rarity.EPIC)));
        SAKURA_STEEL_NUGGET = material("sakura_steel_nugget", p -> new Item(p.rarity(Rarity.EPIC)));
        VIRTUAL_GOLD_INGOT = material("virtual_gold_ingot", p -> new CustomNameColorItem(ChatFormatting.DARK_PURPLE));
        EARTH_CORE = material("earth_core", p -> new CustomNameColorItem(ChatFormatting.DARK_PURPLE));
        GUARDIAN_OCEAN_NUGGET = material("guardian_ocean_nugget", p -> new CustomNameColorItem(ChatFormatting.BLUE));
        VIRTUAL_GOLD_NUGGET = material("virtual_gold_nugget", p -> new CCTooltipItem(p, false, ChatFormatting.DARK_PURPLE,
                () -> CCLangData.VIRTUAL_GOLD_NUGGET.get(CCLangData.chance(CCModConfig.SERVER.virtualGoldNuggetChance.get()))));
        FIRE_ESSENCE = material("fire_essence", p -> new CCTooltipItem(p, false, ChatFormatting.YELLOW,
                () -> CCLangData.simpleDrop(EntityType.BLAZE, CCModConfig.SERVER.fireEssenceChance.get())));
        OCEAN_ESSENCE = material("ocean_essence", p -> new CCTooltipItem(p, false, ChatFormatting.AQUA,
                () -> CCLangData.simpleDrop(EntityType.GUARDIAN, CCModConfig.SERVER.oceanEssenceChance.get())));
        WARDEN_SCLERITE = material("warden_sclerite", p -> new CCTooltipItem(p, false, ChatFormatting.LIGHT_PURPLE,
                () -> CCLangData.simpleDrop(EntityType.WARDEN, CCModConfig.SERVER.wardenScleriteChance.get())));
        LIGHT_FRAGMENT = material("light_fragment", p -> new CCTooltipItem(p, false, ChatFormatting.YELLOW,
                () -> CCLangData.witherDrop(EntityType.HUSK, CCModConfig.SERVER.lightFragmentChance.get())));
        MIDNIGHT_FRAGMENT = material("midnight_fragment", p -> new CCTooltipItem(p, false, ChatFormatting.LIGHT_PURPLE,
                () -> CCLangData.witherDrop(EntityType.STRAY, CCModConfig.SERVER.midnightFragmentChance.get())));
        DEATH_ESSENCE = material("death_essence", p -> new CCTooltipItem(p, false, ChatFormatting.LIGHT_PURPLE,
                () -> CCLangData.deathEssence(CCModConfig.SERVER.deathEssenceChance.get(), CCModConfig.SERVER.deathEssenceMinHealth.get())));
        PURE_NETHER_STAR = material("pure_nether_star", p -> new CCTooltipItem(p, false, ChatFormatting.YELLOW,
                () -> CCLangData.PURE_NETHER_STAR.get(CCLangData.entity(EntityType.WITHER), CCLangData.num(CCModConfig.SERVER.pureNetherStarEffectCount.get()))));
        SHULKER_SCRAP = material("shulker_scrap", p -> new CCTooltipItem(p, false, ChatFormatting.LIGHT_PURPLE,
                () -> CCLangData.SHULKER_SCRAP.get(CCLangData.chance(CCModConfig.SERVER.shulkerScrapChance.get()), CCLangData.entity(EntityType.SHULKER))));
        SOARING_WINGS = material("soaring_wings", p -> new CCTooltipItem(p, false, ChatFormatting.GOLD,
                () -> CCLangData.SOARING_WINGS.get(CCLangData.entity(EntityType.PHANTOM), CCLangData.num(620))));
        HEART_FRAGMENT = material("heart_fragment", p -> new CCTooltipItem(p, false, ChatFormatting.AQUA,
                () -> CCLangData.HEART_FRAGMENT.get(CCLangData.entity(EntityType.PILLAGER))));
        SAKURA_FRAGMENT = material("sakura_fragment", p -> new CCTooltipItem(p, true, ChatFormatting.LIGHT_PURPLE,
                () -> CCLangData.SAKURA_FRAGMENT.get(CCLangData.chance(CCModConfig.SERVER.sakuraFragmentChance.get()))));
        GUARDIAN_SPIKE = material("guardian_spike", p -> new CCTooltipItem(p, false, ChatFormatting.BLUE,
                () -> CCLangData.simpleDrop(EntityType.GUARDIAN, CCModConfig.SERVER.guardianSpikeChance.get())));
        BROKEN_TOTEM = material("broken_totem", p -> new CCTooltipItem(p, false, ChatFormatting.YELLOW,
                () -> CCLangData.BROKEN_TOTEM.get(CCLangData.chance(CCModConfig.SERVER.brokenTotemChance.get()))));
        TREASURE_FRAGMENT = material("treasure_fragment", p -> new CCTooltipItem(p, false, ChatFormatting.AQUA, CCLangData.TREASURE_FRAGMENT::get));
        VOID_ESSENCE = material("void_essence", p -> new CCTooltipItem(p, false, ChatFormatting.AQUA, CCLangData.VOID_ESSENCE::get));
        JUNGLE_PYRAMID_LOOT_BOX = item("jungle_pyramid_loot_box", p -> new LootTableBox(p.rarity(Rarity.EPIC), BuiltInLootTables.JUNGLE_TEMPLE));
        DESERT_PYRAMID_LOOT_BOX = item("desert_pyramid_loot_box", p -> new LootTableBox(p.rarity(Rarity.UNCOMMON), BuiltInLootTables.DESERT_PYRAMID));
        IGLOO_LOOT_BOX = item("igloo_loot_box", p -> new LootTableBox(p.rarity(Rarity.RARE), BuiltInLootTables.IGLOO_CHEST));
        MANSON_LOOT_BOX = item("manson_loot_box", p -> new LootTableBox(p.rarity(Rarity.EPIC), BuiltInLootTables.WOODLAND_MANSION));
        PILLAGER_OUTPOST_LOOT_BOX = item("pillager_outpost_loot_box", p -> new LootTableBox(p.rarity(Rarity.EPIC), BuiltInLootTables.PILLAGER_OUTPOST));
        CelestialCore.REGISTRATE.defaultCreativeTab(CelestialCore.TOOL_TAB.getKey());
        GEN_ITEM = CelestialCore.MATS.genItem(CCMaterials.values());
    }

    public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialCore.REGISTRATE.item(id, factory)
                .model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
                .register();
    }

    public static <T extends Item> ItemEntry<T> item(String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialCore.REGISTRATE.item(id, factory)
                .model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/items/" + ctx.getName())))
                .register();
    }

    public static void register() {

    }
}