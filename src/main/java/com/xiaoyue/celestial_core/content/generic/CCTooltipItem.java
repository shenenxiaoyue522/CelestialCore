package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.data.CCLangData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Supplier;

public class CCTooltipItem extends CustomNameColorItem {

    public boolean canHurt;
    public boolean forgeInAnvil;
    public Supplier<MutableComponent> sup;

    public CCTooltipItem(Properties pProperties, boolean canHurt, ChatFormatting color, Supplier<MutableComponent> sup) {
        super(pProperties, color);
        this.canHurt = canHurt;
        this.forgeInAnvil = false;
        this.sup = sup;
    }

    public CCTooltipItem(Properties pProperties, boolean canHurt, ChatFormatting color, boolean forgeInAnvil, Supplier<MutableComponent> sup) {
        super(pProperties, color);
        this.canHurt = canHurt;
        this.forgeInAnvil = forgeInAnvil;
        this.sup = sup;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        if (this.forgeInAnvil) {
            list.add(CCLangData.ANVIL_FORGE_INFO.get().withStyle(ChatFormatting.GRAY));
        }
        list.add(sup.get().withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean canBeHurtBy(ItemStack stack, DamageSource source) {
        return canHurt;
    }
}
