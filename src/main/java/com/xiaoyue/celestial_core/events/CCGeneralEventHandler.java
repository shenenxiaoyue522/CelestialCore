package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.data.CCModConfig;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

@EventBusSubscriber(modid = MODID)
public class CCGeneralEventHandler {

    @SubscribeEvent
    public static void onUseTotem(LivingUseTotemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.getRandom().nextDouble() <= CCModConfig.SERVER.brokenTotemChance.get()) {
            player.addItem(CCItems.BROKEN_TOTEM.asStack());
        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) return;
        if (event.getEffect() instanceof CelestialEffect effect) {
            if (!effect.beRemove(instance, event.getEntity())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        Entity e = event.getEntity();
        if (e instanceof LivingEntity entity) {
            if (entity.level().isClientSide() || entity.tickCount % 10 != 0) return;
            if (EntityUtils.onBlackFlame(entity)) {
                int time = EntityUtils.getBlackFlameTime(entity);
                EntityUtils.setBlackFlameTime(entity, Math.max(0, time - 1));
                entity.hurt(CCDamageTypes.abyss(entity.level()), entity.getMaxHealth() * 0.01f);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            double re = player.getAttributeValue(CCAttributes.REGEN_RATE.holder());
            event.setAmount((float) (event.getAmount() * re));
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow && arrow.getOwner() instanceof Player player) {
            double as = player.getAttributeValue(CCAttributes.ARROW_SPEED.holder());
            if (!arrow.getTags().contains(CelestialFlags.ARROW_SPEED)) {
                arrow.setDeltaMovement(arrow.getDeltaMovement().scale(as));
                arrow.addTag(CelestialFlags.ARROW_SPEED);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTeleport(EntityTeleportEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity le && le.hasEffect(CCEffects.ROTTEN_CURSE.holder())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        if (entity.hasEffect(CCEffects.UNYIELDING.holder())) {
            event.setCanceled(true);
            entity.setHealth(1f);
        }
        if (attacker instanceof Player player) {
            if (entity instanceof WitherBoss) {
                if (!PlayerFlagData.hasFlag(player, CelestialFlags.NETHER_STAGE)) {
                    PlayerFlagData.addFlag(player, CelestialFlags.NETHER_STAGE);
                }
            }
        }
    }
}
