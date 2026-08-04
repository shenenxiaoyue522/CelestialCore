package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2core.capability.attachment.GeneralCapabilityHolder;
import dev.xkmc.l2core.capability.attachment.GeneralCapabilityTemplate;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

@SerialClass
public class EntityTagData extends GeneralCapabilityTemplate<LivingEntity, EntityTagData> {

    public static final GeneralCapabilityHolder<LivingEntity, EntityTagData> HOLDER = new GeneralCapabilityHolder<>(CelestialCore.loc("tag_data"),
            EntityTagData.class, EntityTagData::new, LivingEntity.class, e -> true);

    public static void syncData(LivingEntity entity, String flag, int data) {
        if (entity.level().isClientSide()) return;
        CelestialCore.HANDLER.toTrackingPlayers(new EntityTagDataSyncPacket(entity.getId(), flag, data), entity);
    }

    public static void addData(LivingEntity entity, String key, int value) {
        if (!entity.isDeadOrDying()) {
            HOLDER.getOrCreate(entity).addData(key, value);
        }
    }

    public static int getData(LivingEntity entity, String key) {
        if (!entity.isDeadOrDying()) {
            return HOLDER.getOrCreate(entity).getData(key);
        }
        return 0;
    }

    public static void removeData(LivingEntity entity, String key) {
        HOLDER.getOrCreate(entity).removeData(key);
        syncData(entity, key, 0);
    }

    @SerialField
    private final Map<String, Integer> data = new HashMap<>();

    public void addData(String key, int value) {
        data.put(key, value);
    }

    public int getData(String key) {
        return data.get(key) == null ? 0 : data.get(key);
    }

    public void removeData(String key) {
        data.remove(key);
    }
}
