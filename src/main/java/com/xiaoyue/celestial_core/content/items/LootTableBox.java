package com.xiaoyue.celestial_core.content.items;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public class LootTableBox extends Item {
    public final ResourceKey<LootTable> lootTable;

    public LootTableBox(Properties pProperties, ResourceKey<LootTable> lootTable) {
        super(pProperties);
        this.lootTable = lootTable;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        String id = ItemUtils.getPathAfterSlash(lootTable.location().getPath());
        MutableComponent info = ItemUtils.getUSNameInfo(id).withStyle(ChatFormatting.AQUA);
        list.add(CCLangData.LOOT_BOX_INFO.get(info).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack box = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer instanceof ServerPlayer serverPlayer) {
            List<ItemStack> stacks = ItemUtils.getDefaultLootStacks(lootTable, serverPlayer);
            for (ItemStack stack : stacks) {
                if (!serverPlayer.addItem(stack)) {
                    EntityUtils.spawnItem(pLevel, pPlayer.getOnPos(), stack);
                }
            }
            ItemUtils.toShrink(box, serverPlayer);
            return InteractionResultHolder.consume(box);
        }
        return InteractionResultHolder.fail(box);
    }
}
