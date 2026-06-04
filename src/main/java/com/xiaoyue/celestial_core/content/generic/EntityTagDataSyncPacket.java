package com.xiaoyue.celestial_core.content.generic;

import com.xiaoyue.celestial_core.register.CCObjects;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@SerialClass
public record EntityTagDataSyncPacket(int entityId, String flag, int data) implements SerialPacketBase<EntityTagDataSyncPacket> {

    @Override
    public void handle(Player player) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        Entity e = level.getEntity(entityId);
        if (!(e instanceof LivingEntity le)) return;
        EntityTagData capability = le.getData(CCObjects.TAG_DATA);
        capability.addData(flag, this.data);
    }
}
