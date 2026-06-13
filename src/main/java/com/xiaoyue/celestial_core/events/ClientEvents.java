package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.client.screen.FlameScreens;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderLiving(RenderLivingEvent.Post<?, ?> event) {
        LivingEntity entity = event.getEntity();
        if (EntityUtils.onAbyssalFlame(entity)) {
            var buffer = event.getMultiBufferSource();
            var matrixStack = event.getPoseStack();
            FlameScreens.renderEntityFlame(FlameScreens.ABYSS_FIRE_LAYER_0, FlameScreens.BLACK_FIRE_LAYER_1, matrixStack, buffer, entity);
        }
    }
}
