package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2core.serial.loot.LootTableTemplate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class CCLootTableGen {

    public static final ResourceLocation MINESHAFT = CelestialCore.loc("abandoned_mineshaft");
    public static final ResourceLocation END_CITY = CelestialCore.loc("end_city_treasure");
    public static final ResourceLocation IGLOO_CHEST = CelestialCore.loc("igloo_chest");

    public static void onLootGen(RegistrateLootTableProvider pvd) {
        pvd.addLootAction(LootContextParamSets.EMPTY, cons -> {
            cons.accept(ResourceKey.create(BuiltInLootTables.ABANDONED_MINESHAFT.registryKey(), MINESHAFT), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(LootTableTemplate.getItem(CCItems.TREASURE_FRAGMENT.get(), 1).setWeight(1))
                    .add(LootTableTemplate.getItem(Items.AIR, 0).setWeight(2))
            ));
            cons.accept(ResourceKey.create(BuiltInLootTables.END_CITY_TREASURE.registryKey(), END_CITY), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(LootTableTemplate.getItem(CCItems.VOID_ESSENCE.get(), 1).setWeight(1))
                    .add(LootTableTemplate.getItem(Items.AIR, 0).setWeight(4))
            ));
            cons.accept(ResourceKey.create(BuiltInLootTables.IGLOO_CHEST.registryKey(), IGLOO_CHEST), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(LootTableTemplate.getItem(CCItems.PERMAFROST_CRYSTAL.get(), 1).setWeight(1))
                    .add(LootTableTemplate.getItem(Items.AIR, 0).setWeight(2))
            ));
        });
    }

}
