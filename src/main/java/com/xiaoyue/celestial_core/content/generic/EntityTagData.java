package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCObjects;
import dev.xkmc.l2serial.serialization.codec.TagCodec;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SerialClass
public class EntityTagData implements IAttachmentSerializer<CompoundTag, EntityTagData> {

    public static void syncData(LivingEntity entity, String flag, int data) {
        if (entity.level().isClientSide()) return;
        CelestialCore.HANDLER.toTrackingPlayers(new EntityTagDataSyncPacket(entity.getId(), flag, data), entity);
    }

    public static void addData(LivingEntity entity, String key, int value) {
        if (!entity.isDeadOrDying()) {
            EntityTagData cap = entity.getData(CCObjects.TAG_DATA);
            cap.addData(key, value);
        }
    }

    public static int getData(LivingEntity entity, String key) {
        if (!entity.isDeadOrDying()) {
            EntityTagData cap = entity.getData(CCObjects.TAG_DATA);
            return cap.getData(key);
        }
        return 0;
    }

    public static void removeData(LivingEntity entity, String key) {
        EntityTagData cap = entity.getData(CCObjects.TAG_DATA);
        cap.removeData(key);
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

    @Override
    public EntityTagData read(IAttachmentHolder iAttachmentHolder, CompoundTag tag, HolderLookup.Provider provider) {
        return Objects.requireNonNull(Wrappers.get(() -> new TagCodec(provider).fromTag(tag, EntityTagData.class, null)));
    }

    @Override
    public @Nullable CompoundTag write(EntityTagData entityIntData, HolderLookup.Provider provider) {
        return Objects.requireNonNull(new TagCodec(provider).toTag(new CompoundTag(), entityIntData));
    }
}
