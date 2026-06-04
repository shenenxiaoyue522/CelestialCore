package com.xiaoyue.celestial_core.events.api;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neoforged.bus.api.Event;

public class RenderScreenEffectEvent extends Event {

    private final PoseStack poseStack;

    public RenderScreenEffectEvent(PoseStack poseStack) {
        this.poseStack = poseStack;
    }

    public PoseStack getPoseStack() {
        return poseStack;
    }
}
