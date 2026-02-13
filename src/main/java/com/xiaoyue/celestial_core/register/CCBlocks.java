package com.xiaoyue.celestial_core.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CCBlocks {

    public static final BlockEntry<Block> SAKURA_STEEL_BLOCK, GUARDIAN_OCEAN_BLOCK, VIRTUAL_GOLD_BLOCK;

    static {
        SAKURA_STEEL_BLOCK = CelestialCore.REGISTRATE.block("sakura_steel_block", p -> new Block(p.strength(5f)))
                .item((b, p) -> new BlockItem(b, p.rarity(IRarityUtils.PINK))).build().defaultLoot().defaultLang().register();
        GUARDIAN_OCEAN_BLOCK = CelestialCore.REGISTRATE.block("guardian_ocean_block", p -> new Block(p.strength(6f)))
                .item((b, p) -> new BlockItem(b, p.rarity(IRarityUtils.BLUE))).build().defaultLoot().defaultLang().register();
        VIRTUAL_GOLD_BLOCK = CelestialCore.REGISTRATE.block("virtual_gold_block", p -> new Block(p.strength(7f)))
                .item((b, p) -> new BlockItem(b, p.rarity(IRarityUtils.DARK_PURPLE))).build().defaultLoot().defaultLang().register();
    }

    public static void register() {

    }
}
