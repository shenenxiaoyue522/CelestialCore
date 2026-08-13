package com.xiaoyue.celestial_core.content.loot;

import com.xiaoyue.celestial_core.register.CCLootModifier;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class CurseEnchCondition implements LootItemCondition {

    @SerialClass.SerialField
    public EquipmentSlot slot;

    @Deprecated
    public CurseEnchCondition() {
    }

    public CurseEnchCondition(EquipmentSlot slot) {
        this.slot = slot;
    }

    @Override
    public LootItemConditionType getType() {
        return CCLootModifier.CURSE_ENCH.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!ctx.hasParam(LootContextParams.LAST_DAMAGE_PLAYER)) return false;
        var player = ctx.getParam(LootContextParams.LAST_DAMAGE_PLAYER);
        ItemStack stack = player.getItemBySlot(slot);
        return ItemUtils.getCurseEnchSize(stack) > 0;
    }
}
