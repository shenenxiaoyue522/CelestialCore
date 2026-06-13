package com.xiaoyue.celestial_core.content.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.xiaoyue.celestial_core.CelestialCore;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class FlameScreens {

    public static final Material ABYSS_FIRE_LAYER_0 = fireLayerOf(CelestialCore.loc("block/abyssal_fire_layer_0"));
    public static final Material BLACK_FIRE_LAYER_1 = fireLayerOf(CelestialCore.loc("block/abyssal_fire_layer_1"));

    public static Material fireLayerOf(ResourceLocation loc) {
        return new Material(InventoryMenu.BLOCK_ATLAS, loc);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderFlameScreen(Material fireLayer1, PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        TextureAtlasSprite textureatlassprite = fireLayer1.sprite();
        RenderSystem.setShaderTexture(0, textureatlassprite.atlasLocation());
        float u0 = textureatlassprite.getU0();
        float u1 = textureatlassprite.getU1();
        float f2 = (u0 + u1) / 2.0F;
        float v0 = textureatlassprite.getV0();
        float v1 = textureatlassprite.getV1();
        float f5 = (v0 + v1) / 2.0F;
        float ratio = textureatlassprite.uvShrinkRatio();
        float f7 = Mth.lerp(ratio, u0, f2);
        float f8 = Mth.lerp(ratio, u1, f2);
        float f9 = Mth.lerp(ratio, v0, f5);
        float f10 = Mth.lerp(ratio, v1, f5);
        for(int i = 0; i < 2; ++i) {
            poseStack.pushPose();
            poseStack.translate((float)(-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees((float)(i * 2 - 1) * 10.0F));
            Matrix4f matrix4f = poseStack.last().pose();
            BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.addVertex(matrix4f, -0.5F, -0.5F, -0.5F).setUv(f8, f10).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferbuilder.addVertex(matrix4f, 0.5F, -0.5F, -0.5F).setUv(f7, f10).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferbuilder.addVertex(matrix4f, 0.5F, 0.5F, -0.5F).setUv(f7, f9).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferbuilder.addVertex(matrix4f, -0.5F, 0.5F, -0.5F).setUv(f8, f9).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
            poseStack.popPose();
        }
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderEntityFlame(Material fireLayer0, Material fireLayer1, PoseStack pMatrixStack, MultiBufferSource pBuffer, Entity pEntity) {
        TextureAtlasSprite fire_0 = fireLayer0.sprite();
        TextureAtlasSprite fire_1 = fireLayer1.sprite();
        Camera camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
        pMatrixStack.pushPose();
        float f = pEntity.getBbWidth() * 1.4F;
        pMatrixStack.scale(f, f, f);
        float f1 = 0.5F;
        float f3 = pEntity.getBbHeight() / f;
        float f4 = 0.0F;
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(-camera.getYRot()));
        pMatrixStack.translate(0.0F, 0.0F, -0.3F + (float) ((int) f3) * 0.02F);
        float f5 = 0.0F;
        int i = 0;
        VertexConsumer consumer = pBuffer.getBuffer(Sheets.cutoutBlockSheet());
        for (PoseStack.Pose pose = pMatrixStack.last(); f3 > 0.0F; ++i) {
            TextureAtlasSprite fire_3 = i % 2 == 0 ? fire_0 : fire_1;
            float f6 = fire_3.getU0();
            float f7 = fire_3.getV0();
            float f8 = fire_3.getU1();
            float f9 = fire_3.getV1();
            if (i / 2 % 2 == 0) {
                float f10 = f8;
                f8 = f6;
                f6 = f10;
            }
            fireVertex(pose, consumer, f1 - 0.0F, 0.0F - f4, f5, f8, f9);
            fireVertex(pose, consumer, -f1 - 0.0F, 0.0F - f4, f5, f6, f9);
            fireVertex(pose, consumer, -f1 - 0.0F, 1.4F - f4, f5, f6, f7);
            fireVertex(pose, consumer, f1 - 0.0F, 1.4F - f4, f5, f8, f7);
            f3 -= 0.45F;
            f4 -= 0.45F;
            f1 *= 0.9F;
            f5 += 0.03F;
        }
        pMatrixStack.popPose();
    }

    private static void fireVertex(PoseStack.Pose matrixEntry, VertexConsumer buffer, float x, float y, float z, float texU, float texV) {
        buffer.addVertex(matrixEntry, x, y, z).setColor(-1).setUv(texU, texV).setUv1(0, 10).setLight(240).setNormal(matrixEntry, 0.0F, 1.0F, 0.0F);
    }
}
