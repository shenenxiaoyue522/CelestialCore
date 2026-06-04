package com.xiaoyue.celestial_core.content.items.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.register.CCMaterials;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_invoker.content.common.Bindings;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class OceanArmor extends ExtraArmorConfig {

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (Bindings.isArmorSlotIndex(slot) && entity instanceof Player player) {
            if (EntityUtils.getSeriesArmorAmount(player, CCMaterials.GUARDIAN_OCEAN) == 4) {
                EntityUtils.addEct(player, MobEffects.WATER_BREATHING, 40);
            }
        }
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.FULL_SET.get(TooltipEntry.eff(MobEffects.WATER_BREATHING.value())).withStyle(ChatFormatting.GRAY));
        super.addTooltip(stack, list);
    }

}
