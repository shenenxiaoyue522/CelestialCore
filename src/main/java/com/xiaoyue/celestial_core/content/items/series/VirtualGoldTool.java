package com.xiaoyue.celestial_core.content.items.series;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class VirtualGoldTool extends ExtraToolConfig {

    private double bonus() {
        return CCModConfig.SERVER.virtualGoldToolPerBonus.get();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state, float old) {
        int totalEnch = ItemUtils.getEnchSize(stack);
        return (float) (old * (1 + bonus() * totalEnch));
    }

    @Override
    public void onDamage(DamageData.Offence cache, ItemStack stack) {
        int totalEnch = ItemUtils.getEnchSize(stack);
        cache.addHurtModifier(DamageModifier.multBase((float) (bonus() * totalEnch), CelestialCore.loc("virtual_tool_damage_bonus")));
        super.onDamage(cache, stack);
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.VIRTUAL_GOLD_GENERAL.get().withStyle(ChatFormatting.GRAY));
        list.add(CCLangData.VIRTUAL_GOLD_TOOL.get(CCLangData.chance(bonus())).withStyle(ChatFormatting.GRAY));
    }
}
