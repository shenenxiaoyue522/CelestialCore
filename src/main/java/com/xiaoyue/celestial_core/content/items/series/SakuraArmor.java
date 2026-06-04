package com.xiaoyue.celestial_core.content.items.series;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_invoker.content.common.Bindings;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraArmorConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SakuraArmor extends ExtraArmorConfig {

    private int time() {
        return CCModConfig.SERVER.sakuraToolRecoveryTime.get();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (Bindings.isArmorSlotIndex(slot)) {
            if (entity.tickCount % (time() * 20) == 0) {
                ItemUtils.repairStack(stack);
            }
        }
    }

    @Override
    public void addTooltip(ItemStack stack, List<Component> list) {
        list.add(CCLangData.SAKURA_SERIES.get(CCLangData.num(time())).withStyle(ChatFormatting.GRAY));
        super.addTooltip(stack, list);
    }
}
