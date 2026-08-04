package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2core.capability.player.PlayerCapabilityHolder;
import dev.xkmc.l2core.capability.player.PlayerCapabilityNetworkHandler;
import dev.xkmc.l2core.capability.player.PlayerCapabilityTemplate;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.TreeSet;

@SerialClass
public class PlayerFlagData extends PlayerCapabilityTemplate<PlayerFlagData> {

    public static final PlayerCapabilityHolder<PlayerFlagData> HOLDER = new PlayerCapabilityHolder<>(CelestialCore.loc("flag_data"),
            PlayerFlagData.class, PlayerFlagData::new, PlayerCapabilityNetworkHandler::new);

    public static void addFlag(LivingEntity entity, String str) {
        if (entity instanceof Player player) {
            HOLDER.getOrCreate(player).addFlag(str);
            if (player instanceof ServerPlayer sp) {
                HOLDER.network.toClient(sp);
            }
        } else {
            entity.addTag(str);
        }
    }

    public static boolean hasFlag(LivingEntity entity, String str) {
        if (entity instanceof Player player) {
            return HOLDER.getOrCreate(player).hasFlag(str);
        } else {
            return entity.getTags().contains(str);
        }
    }

    @SerialField
    private final TreeSet<String> flags = new TreeSet<>();

    public void addFlag(String str) {
        flags.add(str);
    }

    public boolean hasFlag(String flag) {
        return flags.contains(flag);
    }
}
