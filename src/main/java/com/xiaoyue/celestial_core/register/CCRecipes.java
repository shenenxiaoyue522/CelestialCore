package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.recipes.FluidTransformationRecipe;
import com.xiaoyue.celestial_invoker.content.common.registrar.NeoForgeRegister;
import dev.xkmc.l2core.serial.recipe.BaseRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class CCRecipes {

    public static final NeoForgeRegister<RecipeType<?>> TYPE = CelestialCore.EXTRA.neoforgeRegister(BuiltInRegistries.RECIPE_TYPE);
    public static final NeoForgeRegister<RecipeSerializer<?>> SERIALIZER = CelestialCore.EXTRA.neoforgeRegister(BuiltInRegistries.RECIPE_SERIALIZER);

    public static final Supplier<RecipeType<FluidTransformationRecipe>> RT_TRANSFORMATION = TYPE.object("fluid_transformation", RecipeType::simple);

    public static final Supplier<BaseRecipe.RecType<FluidTransformationRecipe, FluidTransformationRecipe, FluidTransformationRecipe.Inv>> RS_TRANSFORMATION =
            SERIALIZER.object("fluid_transformation", () -> new BaseRecipe.RecType<>(FluidTransformationRecipe.class, RT_TRANSFORMATION));

    public static void register() {
    }
}
