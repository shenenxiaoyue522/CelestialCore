package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.items.VirtualGoldArmorItem;
import com.xiaoyue.celestial_core.content.items.series.*;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2damagetracker.contents.materials.api.*;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.GenItemVanillaType;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.ToolStats;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.List;
import java.util.function.Supplier;

public enum CCMaterials implements IMatVanillaType {
    OCEAN("ocean", 3, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(1200, 8, 6, 1, 12),
            new ArmorStats(30, new int[]{3, 6, 8, 3}, 2, 0, 12),
            GenItemVanillaType.TOOL_GEN, GenItemVanillaType.ARMOR_GEN,
            new OceanTool().setStick(e -> CCItems.GUARDIAN_SPIKE.get(), false), new OceanArmor(), ChatFormatting.AQUA),
    SAKURA("sakura", 2, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(260, 8, 5, 1, 15),
            new ArmorStats(15, new int[]{2, 5, 6, 2}, 0, 0, 15),
            GenItemVanillaType.TOOL_GEN, GenItemVanillaType.ARMOR_GEN,
            new SakuraTool().setStick(e -> Items.CHERRY_LOG, false), new SakuraArmor(), ChatFormatting.LIGHT_PURPLE),
    VIRTUAL_GOLD("virtual_gold", 3, SoundEvents.ARMOR_EQUIP_GOLD,
            new ToolStats(1200, 12, 7, 1, 30),
            new ArmorStats(22, new int[]{3, 6, 8, 3}, 2, 0, 30),
            GenItemVanillaType.TOOL_GEN, VirtualGoldArmorItem.ARMOR_GEN,
            new VirtualGoldTool().setStick(e -> CCItems.FIRE_ESSENCE.get(), false), new VirtualGoldArmor(), ChatFormatting.DARK_PURPLE),
    ;

    final String id;
    final Tier tier;
    final SimpleEntry<ArmorMaterial> mat;
    final ToolConfig tool_config;
    final ArmorConfig armor_config;
    final IToolStats tool_stats;
    final ExtraToolConfig tool_extra;
    final ExtraArmorConfig armor_extra;
    public final ChatFormatting trim_color;
    final int durability;

    CCMaterials(String name, int level, Holder<SoundEvent> equip_sound, IToolStats tool, ArmorStats armor, ToolConfig tool_config, ArmorConfig armor_config, ExtraToolConfig tool_extra, ExtraArmorConfig armor_extra, ChatFormatting trim_color) {
        Supplier<Ingredient> ing = () -> Ingredient.of(INGOTS[ordinal()]);
        this.id = name;
        this.tier = new SimpleTier(tool_extra.getTier(level), tool.durability(), tool.speed(), 0, tool.enchant(), ing);
        this.mat = new SimpleEntry<>(CelestialCore.REGISTRATE.simple(name, Registries.ARMOR_MATERIAL, () -> new ArmorMaterial(armor.defense(), armor.enchant(), equip_sound, ing,
                List.of(new ArmorMaterial.Layer(CelestialCore.loc(name))), armor.tough(), armor.kb())));
        this.tool_config = tool_config;
        this.armor_config = armor_config;
        this.tool_stats = tool;
        this.tool_extra = tool_extra;
        this.armor_extra = armor_extra;
        this.trim_color = trim_color;
        this.durability = armor.durability();
    }

    final ItemLike[] INGOTS = {CCItems.REINFORCED_OCEAN_INGOT, CCItems.SAKURA_STEEL, CCItems.VIRTUAL_GOLD_INGOT};
    final ItemLike[] NUGGETS = {CCItems.REINFORCED_OCEAN_NUGGET, CCItems.SAKURA_STEEL_NUGGET, CCItems.VIRTUAL_GOLD_NUGGET};
    final BlockEntry<?>[] BLOCKS = {CCItems.REINFORCED_OCEAN_BLOCK, CCItems.SAKURA_STEEL_BLOCK, CCItems.VIRTUAL_GOLD_BLOCK};

    @Override
    public Item getIngot() {
        return INGOTS[ordinal()].asItem();
    }

    @Override
    public Item getNugget() {
        return NUGGETS[ordinal()].asItem();
    }

    @Override
    public Block getBlock() {
        return BLOCKS[ordinal()].get();
    }

    public String getID() {
        return id;
    }

    @Override
    public ArmorConfig getArmorConfig() {
        return armor_config;
    }

    @Override
    public ToolConfig getToolConfig() {
        return tool_config;
    }

    @Override
    public IToolStats getToolStats() {
        return tool_stats;
    }

    @Override
    public Tier getTier() {
        return tier;
    }

    @Override
    public ExtraArmorConfig getExtraArmorConfig() {
        return armor_extra;
    }

    @Override
    public int armorDurability() {
        return durability;
    }

    @Override
    public Holder<ArmorMaterial> getArmorMaterial() {
        return mat.holder();
    }

    @Override
    public ExtraToolConfig getExtraToolConfig() {
        return tool_extra;
    }

    @Override
    public ItemEntry<Item>[][] getGenerated() {
        return CCItems.GEN_ITEM;
    }

    @Override
    public ResourceLocation id() {
        return CelestialCore.loc(id);
    }
}
