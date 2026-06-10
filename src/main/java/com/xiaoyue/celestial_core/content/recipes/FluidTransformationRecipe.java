package com.xiaoyue.celestial_core.content.recipes;

import com.xiaoyue.celestial_core.register.CCRecipes;
import dev.xkmc.l2core.serial.recipe.BaseRecipe;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class FluidTransformationRecipe extends BaseRecipe<FluidTransformationRecipe, FluidTransformationRecipe, FluidTransformationRecipe.Inv> {

    @SerialField
    public List<Ingredient> inputs = new ArrayList<>();

    @SerialField
    public Block fluid;

    @SerialField
    public ItemStack output;

    public FluidTransformationRecipe() {
        super(CCRecipes.RS_TRANSFORMATION.get());
    }

    @Override
    public boolean matches(Inv inv, Level level) {
        return true;
    }

    @Override
    public ItemStack assemble(Inv inv, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    public static class Inv extends SimpleContainer implements RecipeInput {
        @Override
        public int size() {
            return 9;
        }
    }
}
