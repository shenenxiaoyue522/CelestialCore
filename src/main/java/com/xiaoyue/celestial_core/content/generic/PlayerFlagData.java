package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCObjects;
import dev.xkmc.l2core.capability.player.PlayerCapabilityHolder;
import dev.xkmc.l2core.capability.player.PlayerCapabilityNetworkHandler;
import dev.xkmc.l2core.capability.player.PlayerCapabilityTemplate;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.TreeSet;

@SerialClass
public class PlayerFlagData extends PlayerCapabilityTemplate<PlayerFlagData> {

    public static final PlayerCapabilityHolder<PlayerFlagData> HOLDER = new PlayerCapabilityHolder<>(CelestialCore.loc("flag_data"),
            PlayerFlagData.class, PlayerFlagData::new, PlayerCapabilityNetworkHandler::new);

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
}
