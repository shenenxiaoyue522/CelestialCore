package com.xiaoyue.celestial_core;

import com.xiaoyue.celestial_core.content.client.armor.CCModelLayers;
import com.xiaoyue.celestial_core.content.client.armor.VGArmorModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = CelestialCore.MODID, value = Dist.CLIENT)
public class CCoreClient {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CCModelLayers.VIRTUAL_GOLD_ARMOR_INNER_LAYER, VGArmorModel::createBodyLayer);
        event.registerLayerDefinition(CCModelLayers.VIRTUAL_GOLD_ARMOR_OUTER_LAYER, VGArmorModel::createBodyLayer);
    }

}
