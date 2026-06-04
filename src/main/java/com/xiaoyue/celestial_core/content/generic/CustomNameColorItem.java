package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_invoker.content.generic.items.api.ICustomName;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomNameColorItem extends Item implements ICustomName {
    public final ChatFormatting color;

    public CustomNameColorItem(ChatFormatting color) {
        super(new Properties());
        this.color = color;
    }

    public CustomNameColorItem(Properties properties, ChatFormatting color) {
        super(properties);
        this.color = color;
    }

    @Override
    public Component getCustomName(ItemStack stack, Component origin) {
        return origin.copy().withStyle(color);
    }
}
