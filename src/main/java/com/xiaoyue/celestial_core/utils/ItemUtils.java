package com.xiaoyue.celestial_core.utils;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUtils {

    public static final int MAX_USE_TIME = 72000;

    public static int getEnchSize(ItemStack stack) {
        return stack.getTagEnchantments().size();
    }

    public static int getCurseEnchSize(ItemStack stack) {
        int totalCurses = 0;
        ItemEnchantments enchantments = stack.getTagEnchantments();
        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            if (!enchantment.is(EnchantmentTags.CURSE) || enchantments.getLevel(enchantment) <= 0) continue;
            totalCurses += 1;
        }
        return totalCurses;
    }

    public static int getAllSlotCurseEnchSize(Player player) {
        int total = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            int curseEnch = ItemUtils.getCurseEnchSize(player.getItemBySlot(slot));
            total += curseEnch;
        }
        return total;
    }

    public static int getTotalEnchLevelOfArmors(Player player) {
        int level = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.isEnchanted()) continue;
            ItemEnchantments enchantments = stack.getTagEnchantments();
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
                level += entry.getIntValue();
            }
        }
        return level;
    }

    public static boolean hasInHand(Player player, ItemStack stack) {
        for (InteractionHand hand : InteractionHand.values()) {
            return player.getItemInHand(hand).equals(stack);
        }
        return false;
    }

    public static String getPathAfterSlash(String path) {
        int index = path.lastIndexOf("/");
        if (index != -1 && index < path.length() - 1) {
            return path.substring(index + 1);
        }
        return path;
    }

    public static void defaultAnvilOutput(AnvilUpdateEvent event, ItemStack output) {
        ItemUtils.defaultAnvilOutput(event, output, 10);
    }

    public static void defaultAnvilOutput(AnvilUpdateEvent event, ItemStack output, int expCost) {
        event.setMaterialCost(1);
        event.setCost(expCost);
        event.setOutput(output);
    }

    public static int calculateRef(RandomSource random, int origin, double chance) {
        return calculateRef(random, origin, chance, 1);
    }

    public static int calculateRef(RandomSource random, int origin, double chance, int value) {
        int newInt = 0;
        for (int i = 0; i < origin; i++) {
            if (random.nextDouble() <= chance) newInt += value;
        }
        return newInt;
    }

    public static void toShrink(ItemStack stack, Player player) {
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }

    public static ResourceLocation getKey(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static List<ItemStack> getDefaultLootStacks(ResourceKey<LootTable> lootTable, ServerPlayer player) {
        LootParams lootParams = (new LootParams.Builder(player.serverLevel()))
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.ORIGIN, player.position())
                .withLuck(player.getLuck())
                .create(LootContextParamSets.GIFT);
        return ItemUtils.getLootStacks(lootTable, player, lootParams);
    }

    public static List<ItemStack> getLootStacks(ResourceKey<LootTable> lootTable, ServerPlayer player, LootParams lootParams) {
        MinecraftServer server = player.level().getServer();
        if (server == null || lootTable.equals(BuiltInLootTables.EMPTY)) {
            return List.of();
        }
        return server.reloadableRegistries().getLootTable(lootTable).getRandomItems(lootParams);
    }

    public static void repairStack(ItemStack stack) {
        repairStack(stack, 1);
    }

    public static void repairStack(ItemStack stack, int value) {
        if (stack.isDamaged()) {
            stack.setDamageValue(stack.getDamageValue() - value);
        }
    }

    public static int getDurability(ItemStack stack) {
        return stack.getMaxDamage() - stack.getDamageValue();
    }

    public static void directDamage(ItemStack stack, int value) {
        int toDamage = stack.getDamageValue() + value;
        if (stack.getMaxDamage() > toDamage) {
            stack.setDamageValue(toDamage);
        } else {
            stack.shrink(1);
        }
    }

    public static MutableComponent getUSNameInfo(String info) {
        return Component.literal(RegistrateLangProvider.toEnglishName(info));
    }

    @Deprecated
    public static Component addTranslatable(String info, @Nullable ChatFormatting color) {
        MutableComponent tip = Component.translatable(info);
        if (color != null) return tip.withStyle(color);
        return tip;
    }

    @Deprecated
    public static Component addTranslatable(String info, @Nullable ChatFormatting color, Object... objects) {
        MutableComponent tip = Component.translatable(info, objects);
        if (color != null) return tip.withStyle(color);
        return tip;
    }
}
