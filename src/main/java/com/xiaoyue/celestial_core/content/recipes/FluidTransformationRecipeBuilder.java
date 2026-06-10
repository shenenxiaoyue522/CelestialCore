package com.xiaoyue.celestial_core.content.recipes;

import com.xiaoyue.celestial_core.register.CCRecipes;
import dev.xkmc.l2core.serial.recipe.BaseRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class FluidTransformationRecipeBuilder extends BaseRecipeBuilder<FluidTransformationRecipeBuilder, FluidTransformationRecipe, FluidTransformationRecipe, FluidTransformationRecipe.Inv> {


    public FluidTransformationRecipeBuilder(List<Ingredient> inputs, Block fluid, ItemStack output) {
        super(CCRecipes.RS_TRANSFORMATION.get(), output.getItem());
        this.recipe.inputs = inputs;
        this.recipe.fluid = fluid;
        this.recipe.output = output;
    }
}
