package com.xiaoyue.celestial_core.content.generic;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomNameColorItem extends Item {
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
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(color);
    }
}
