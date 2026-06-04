package com.xiaoyue.celestial_core.content.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ExtraDropModifier extends LootModifier {

    public static final MapCodec<ExtraDropModifier> CODEC = RecordCodecBuilder.mapCodec(i -> codecStart(i).and(i.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item")
                    .forGetter(m -> m.item),
            Codec.STRING.optionalFieldOf("chance")
                    .forGetter(m -> Optional.ofNullable(m.chance).map(DoubleConfigValue::toData))
    )).apply(i, ExtraDropModifier::new));

    public final Item item;

    @Nullable
    public final DoubleConfigValue chance;

    protected ExtraDropModifier(LootItemCondition[] conditionsIn, Item item, Optional<String> chance) {
        super(conditionsIn);
        this.item = item;
        this.chance = chance.map(DoubleConfigValue::of).orElse(null);
    }

    public ExtraDropModifier(Item item, @Nullable DoubleConfigValue chance, LootItemCondition... conditionsIn) {
        super(conditionsIn);
        this.item = item;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!generatedLoot.isEmpty()) return generatedLoot;
        if (chance == null || context.getRandom().nextDouble() <= chance.get()) {
            generatedLoot.add(new ItemStack(item));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
