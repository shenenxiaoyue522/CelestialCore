package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@EventBusSubscriber(modid = MODID)
public class StartFallEventHandler {

    private static int timer = 0;
    private static boolean wasDay = false;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        for (ServerLevel level : server.getAllLevels()) {
            if (level.isDay()) {
                if (!wasDay) {
                    destroyFallStars(level);
                    timer = 0;
                    wasDay = true;
                }
                continue;
            }
            wasDay = false;
            timer++;
            if (timer < CCModConfig.SERVER.celestialFragmentInterval.get()) continue;
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.level() != level) continue;
                if (level.random.nextDouble() <= CCModConfig.SERVER.celestialFragmentChance.get()) {
                    spawnFallStart(player);
                    timer = 0;
                    break;
                }
            }
        }
    }

    private static void destroyFallStars(ServerLevel level) {
        List<? extends ItemEntity> items = level.getEntities(EntityType.ITEM, e -> e.getItem().is(CCItems.CELESTIAL_FRAGMENT.get()) && e.getDeltaMovement().y < -0.5);
        for (ItemEntity item : items) {
            if (item.getItem().getItem() != CCItems.CELESTIAL_FRAGMENT.get()) continue;
            if (item.getDeltaMovement().y >= -0.5) continue;
            item.discard();
        }
    }

    private static void spawnFallStart(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        var random = level.random;
        double angle = random.nextDouble() * 2 * Math.PI;
        double distance = 50 + random.nextDouble() * (100 - 50);
        double x = player.getX() + distance * Math.cos(angle);
        double z = player.getZ() + distance * Math.sin(angle);
        double y = 150 + random.nextDouble() * (220 - 150);
        ItemEntity itemEntity = new ItemEntity(level, x, y, z, CCItems.CELESTIAL_FRAGMENT.asStack());
        itemEntity.setDeltaMovement(0, -2.5f, 0);
        itemEntity.setPickUpDelay(20);
        itemEntity.setInvulnerable(true);
        itemEntity.lifespan = Integer.MAX_VALUE;
        level.addFreshEntity(itemEntity);
    }
}
