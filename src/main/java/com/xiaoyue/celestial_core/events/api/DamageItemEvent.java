package com.xiaoyue.celestial_core.events.api;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;

import javax.annotation.Nullable;

public class DamageItemEvent extends Event {

    private final @Nullable LivingEntity entity;
    private final ItemStack stack;
    private int amount;

    public DamageItemEvent(@Nullable LivingEntity entity, ItemStack stack, int amount) {
        this.entity = entity;
        this.stack = stack;
        this.amount = amount;
    }

    @Nullable
    public LivingEntity getEntity() {
        return entity;
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
