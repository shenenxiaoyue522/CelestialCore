package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_invoker.invoker.config.wrapper.ConfigWrapper;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class CCModConfig {

    public static class Server extends ConfigWrapper {

        public final ModConfigSpec.DoubleValue fireEssenceChance;
        public final ModConfigSpec.DoubleValue oceanEssenceChance;
        public final ModConfigSpec.DoubleValue deathEssenceChance;
        public final ModConfigSpec.IntValue deathEssenceMinHealth;
        public final ModConfigSpec.DoubleValue wardenScleriteChance;
        public final ModConfigSpec.DoubleValue shulkerScrapChance;
        public final ModConfigSpec.DoubleValue lightFragmentChance;
        public final ModConfigSpec.DoubleValue midnightFragmentChance;
        public final ModConfigSpec.DoubleValue sakuraFragmentChance;
        public final ModConfigSpec.DoubleValue virtualGoldNuggetChance;
        public final ModConfigSpec.IntValue pureNetherStarEffectCount;
        public final ModConfigSpec.DoubleValue guardianSpikeChance;
        public final ModConfigSpec.DoubleValue brokenTotemChance;
        public final ModConfigSpec.DoubleValue cursedEssenceChance;
        public final ModConfigSpec.DoubleValue celestialFragmentChance;
        public final ModConfigSpec.IntValue celestialFragmentInterval;

        public final ModConfigSpec.IntValue sakuraToolRecoveryTime;
        public final ModConfigSpec.DoubleValue virtualGoldToolPerBonus;

        Server(Builder builder) {
            setCelestial();
            builder.push("materials", "Materials");
            fireEssenceChance = builder
                    .defineInRange("fireEssenceChance", 0.08, 0, 1);
            oceanEssenceChance = builder
                    .defineInRange("oceanEssenceChance", 0.08, 0, 1);
            deathEssenceChance = builder
                    .defineInRange("deathEssenceChance", 0.6, 0, 1);
            deathEssenceMinHealth = builder
                    .defineInRange("deathEssenceMinHealth", 100, 0, 1000000);
            wardenScleriteChance = builder
                    .defineInRange("wardenScleriteChance", 0.5, 0, 1);
            shulkerScrapChance = builder
                    .defineInRange("shulkerScrapChance", 0.5, 0, 1);
            lightFragmentChance = builder
                    .defineInRange("lightFragmentChance", 0.05, 0, 1);
            midnightFragmentChance = builder
                    .defineInRange("midnightFragmentChance", 0.05, 0, 1);
            sakuraFragmentChance = builder
                    .defineInRange("sakuraFragmentChance", 0.1, 0, 1);
            virtualGoldNuggetChance = builder
                    .defineInRange("virtualGoldNuggetChance", 0.5, 0, 1);
            pureNetherStarEffectCount = builder
                    .defineInRange("pureNetherStarEffectCount", 12, 0, 100);
            guardianSpikeChance = builder
                    .defineInRange("guardianSpikeChance", 0.2, 0, 1);
            brokenTotemChance = builder
                    .defineInRange("brokenTotemChance", 0.5, 0, 1);
            cursedEssenceChance = builder
                    .defineInRange("cursedEssenceChance", 0.05, 0, 1);
            celestialFragmentChance = builder
                    .defineInRange("celestialFragmentChance", 0.5, 0, 1);
            celestialFragmentInterval = builder
                    .defineInRange("celestialFragmentInterval", 6000, 0, Integer.MAX_VALUE);
            builder.pop();

            builder.push("items", "Items");
            sakuraToolRecoveryTime = builder
                    .comment("Time required to restore durability each time")
                    .defineInRange("sakuraToolRecoveryTime", 5, 1, 100);
            virtualGoldToolPerBonus = builder
                    .comment("Percentage bonus provided by each enchantment")
                    .defineInRange("virtualGoldToolPerAdd", 0.05, 0.01, 1);
            builder.pop();
        }
    }

    public static final Server SERVER = CelestialCore.EXTRA.initConfig(ModConfig.Type.SERVER, Server::new);

    public static void init() {
    }
}
