package com.xiaoyue.celestial_core.register;

import com.mojang.serialization.MapCodec;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.loot.*;
import com.xiaoyue.celestial_invoker.content.common.registrar.NeoForgeRegister;
import dev.xkmc.l2serial.serialization.codec.MapCodecAdaptor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class CCLootModifier {


    public static final NeoForgeRegister<MapCodec<? extends IGlobalLootModifier>> LOOT = CelestialCore.EXTRA.neoforgeRegister(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS);
    public static final NeoForgeRegister<LootItemConditionType> CONDITION = CelestialCore.EXTRA.neoforgeRegister(BuiltInRegistries.LOOT_CONDITION_TYPE);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM;
    public static final Supplier<MapCodec<ExtraDropModifier>> EXTRA_DROP;
    public static final Supplier<MapCodec<AddLootTableModifier>> ADD_LOOT_TABLE;
    public static final Supplier<MapCodec<FishingCooldownModifier>> FISHING_ITEM;

    public static final Supplier<LootItemConditionType> PLAYER_FLAG, ENTITY_HEALTH, PLAYER_EFFECT,
            CHARGED_CREEPER, EQUIP_ENCH;

    static {
        ADD_ITEM = LOOT.object("add_item", () -> AddItemModifier.CODEC);
        EXTRA_DROP = LOOT.object("extra_drop", () -> ExtraDropModifier.CODEC);
        ADD_LOOT_TABLE = LOOT.object("add_loot_table", () -> AddLootTableModifier.CODEC);
        FISHING_ITEM = LOOT.object("fishing_cooldown", () -> FishingCooldownModifier.CODEC);

        PLAYER_FLAG = condition("player_flag", PlayerFlagCondition.class);
        ENTITY_HEALTH = condition("entity_health", EntityHealthCondition.class);
        PLAYER_EFFECT = condition("player_effect", PlayerEffectCondition.class);
        CHARGED_CREEPER = condition("charged_creeper_kill", ChargedCreeperKillCondition.class);
        EQUIP_ENCH = condition("equip_ench", EquipEnchCondition.class);
    }

    private static <T extends LootItemCondition> Supplier<LootItemConditionType> condition(String id, Class<T> codec) {
        return CONDITION.object(id, rl -> new LootItemConditionType(MapCodecAdaptor.of(codec)));
    }

    public static void register() {

    }

}
