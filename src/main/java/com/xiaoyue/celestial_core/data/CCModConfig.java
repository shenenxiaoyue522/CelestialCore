package com.xiaoyue.celestial_core.data;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_invoker.invoker.config.wrapper.ConfigWrapper;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import static com.xiaoyue.celestial_core.CelestialCore.MODID;

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

        public final ModConfigSpec.IntValue sakuraToolRecoveryTime;
        public final ModConfigSpec.DoubleValue virtualGoldToolPerBonus;

        Server(Builder builder) {
            setCelestial();
            builder.push("materials", "Materials");
            fireEssenceChance = builder
                    .name("Fire Essence Chance")
                    .defineInRange("fireEssenceChance", 0.08, 0, 1);
            oceanEssenceChance = builder
                    .name("Ocean Essence Chance")
                    .defineInRange("oceanEssenceChance", 0.08, 0, 1);
            deathEssenceChance = builder
                    .name("Death Essence Chance")
                    .defineInRange("deathEssenceChance", 0.6, 0, 1);
            deathEssenceMinHealth = builder
                    .name("Death Essence Min Health")
                    .defineInRange("deathEssenceMinHealth", 100, 0, 1000000);
            wardenScleriteChance = builder
                    .name("Warden Sclerite Chance")
                    .defineInRange("wardenScleriteChance", 0.5, 0, 1);
            shulkerScrapChance = builder
                    .name("Shulker Scrap Chance")
                    .defineInRange("shulkerScrapChance", 0.5, 0, 1);
            lightFragmentChance = builder
                    .name("Light Fragment Chance")
                    .defineInRange("lightFragmentChance", 0.05, 0, 1);
            midnightFragmentChance = builder
                    .name("Midnight Fragment Chance")
                    .defineInRange("midnightFragmentChance", 0.05, 0, 1);
            sakuraFragmentChance = builder
                    .name("Sakura Fragment Chance")
                    .defineInRange("sakuraFragmentChance", 0.1, 0, 1);
            virtualGoldNuggetChance = builder
                    .name("virtual Gold Nugget Chance")
                    .defineInRange("virtualGoldNuggetChance", 0.5, 0, 1);
            pureNetherStarEffectCount = builder
                    .name("Pure Nether Star Effect Count")
                    .defineInRange("pureNetherStarEffectCount", 12, 0, 100);
            guardianSpikeChance = builder
                    .name("Guardian Spike Chance")
                    .defineInRange("guardianSpikeChance", 0.2, 0, 1);
            brokenTotemChance = builder
                    .name("Broken Totem Chance")
                    .defineInRange("brokenTotemChance", 0.5, 0, 1);
            builder.pop();

            builder.push("items", "Items");
            sakuraToolRecoveryTime = builder
                    .name("Sakura Tool Recovery Time")
                    .comment("Time required to restore durability each time")
                    .defineInRange("sakuraToolRecoveryTime", 5, 1, 100);
            virtualGoldToolPerBonus = builder
                    .name("virtual Gold Tool Per Bonus")
                    .comment("Percentage bonus provided by each enchantment")
                    .defineInRange("virtualGoldToolPerAdd", 0.05, 0.01, 1);
            builder.pop();
        }
    }

    public static final Server SERVER = CelestialCore.EXTRA.initConfig(ModConfig.Type.SERVER, Server::new);
    public static final String SERVER_PATH = "celestial_configs/" + MODID + "-" + ModConfig.Type.SERVER.extension() + ".toml";;

    public static void init() {
    }
}
