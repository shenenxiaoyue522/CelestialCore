package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.register.CCObjects;
import dev.xkmc.l2serial.serialization.codec.TagCodec;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.TreeSet;

@SerialClass
public class PlayerFlagData implements IAttachmentSerializer<CompoundTag, PlayerFlagData> {

    public static void addFlag(LivingEntity entity, String str) {
        if (entity instanceof Player player) {
            player.getData(CCObjects.FLAG_DATA).addFlag(str);
        } else {
            entity.addTag(str);
        }
    }

    public static boolean hasFlag(LivingEntity entity, String str) {
        if (entity instanceof Player player) {
            player.getData(CCObjects.FLAG_DATA).hasFlag(str);
        } else {
            return entity.getTags().contains(str);
        }
        return false;
    }

    @SerialField
    private final TreeSet<String> flags = new TreeSet<>();

    public void addFlag(String str) {
        flags.add(str);
    }

    public boolean hasFlag(String flag) {
        return flags.contains(flag);
    }

    @Override
    public PlayerFlagData read(IAttachmentHolder iAttachmentHolder, CompoundTag tag, HolderLookup.Provider provider) {
        return Objects.requireNonNull(Wrappers.get(() -> new TagCodec(provider).fromTag(tag, PlayerFlagData.class, null)));
    }

    @Override
    public @Nullable CompoundTag write(PlayerFlagData playerFlagData, HolderLookup.Provider provider) {
        return Objects.requireNonNull(new TagCodec(provider).toTag(new CompoundTag(), playerFlagData));
    }
}
