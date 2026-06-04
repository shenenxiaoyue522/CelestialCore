package com.xiaoyue.celestial_core.events.api;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class LivingJumpEvent extends Event implements ICancellableEvent {

    private final LivingEntity entity;
    private float jumpPower;

    public LivingJumpEvent(LivingEntity entity, float jumpPower) {
        this.entity = entity;
        this.jumpPower = jumpPower;
    }

    public LivingEvent.LivingJumpEvent getForgeEvent() {
        return new LivingEvent.LivingJumpEvent(entity);
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public float getJumpPower() {
        return jumpPower;
    }

    public void setJumpPower(float jumpPower) {
        this.jumpPower = jumpPower;
    }
}
