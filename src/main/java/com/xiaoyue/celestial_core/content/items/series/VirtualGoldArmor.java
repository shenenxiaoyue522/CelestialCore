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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class VirtualGoldArmor extends ExtraArmorConfig {

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (Bindings.isArmorSlotIndex(slot) && entity instanceof LivingEntity player) {
            if (EntityUtils.getSetArmorAmount(player, CCMaterials.VIRTUAL_GOLD) == 4) {
                EntityUtils.addEct(player, MobEffects.FIRE_RESISTANCE, 40);
            }
        }
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.VIRTUAL_GOLD_GENERAL.get().withStyle(ChatFormatting.GRAY));
        list.add(CCLangData.FULL_SET.get(TooltipEntry.eff(MobEffects.FIRE_RESISTANCE.value())).withStyle(ChatFormatting.GRAY));
        super.addTooltip(stack, list);
    }

}
