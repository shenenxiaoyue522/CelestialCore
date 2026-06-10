package com.xiaoyue.celestial_core.compat;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.recipes.FluidTransformationRecipe;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2core.compat.jei.BaseRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class FluidTransformationRecipeCategory extends BaseRecipeCategory<FluidTransformationRecipe, FluidTransformationRecipeCategory> {

    public IDrawable arrow;
    public IDrawable slotBackground;
    public IJeiHelpers jeiHelpers;

    public FluidTransformationRecipeCategory() {
        super(CelestialCore.loc("fluid_transformation"), FluidTransformationRecipe.class);
    }

    public FluidTransformationRecipeCategory init(IJeiHelpers jeiHelpers) {
        this.jeiHelpers = jeiHelpers;
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        background = guiHelper.createBlankDrawable(166, 66);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, CCItems.EARTH_CORE.asStack());
        arrow = guiHelper.createDrawable(CCJeiPlugin.TEXTURE, 0, 17, 24, 17);
        slotBackground = guiHelper.createDrawable(CCJeiPlugin.TEXTURE, 0, 34, 18, 18);
        return this;
    }

    @Override
    public Component getTitle() {
        return CCLangData.TRANSFORMATION_RECIPE_TITLE.get();
    }

    @Override
    public void draw(FluidTransformationRecipe recipe, IRecipeSlotsView view, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 61, 23);
        arrow.draw(guiGraphics, 112, 23);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidTransformationRecipe recipe, IFocusGroup group) {
        var y = 5;
        var x = 5;
        for (int i = 0; i < 9; i++) {
            if (recipe.inputs.size() > i) {
                builder.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                        .setSlotName("input" + i)
                        .addIngredients(recipe.inputs.get(i));
            }
            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, x + 1, y + 1)
                    .setSlotName("background" + i)
                    .setBackground(slotBackground, -1, -1);
            y += 18;
            if (y >= 54) {
                y -= 54;
                x += 18;
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 27)
                .setSlotName("output")
                .setBackground(slotBackground, -1, -1)
                .addItemStack(recipe.output);
        builder.addSlot(RecipeIngredientRole.CATALYST, 92, 27)
                .addFluidStack(recipe.fluid.defaultBlockState().getFluidState().getType(), 1000)
                .setSlotName("fluid")
                .setBackground(slotBackground, -1, -1);
    }
}
