package de.crazymemecoke.utils.render;

import java.awt.Color;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    public static int reAlpha(int color, float alpha) {
        Color c = new Color(color);
        float r = 0.003921569F * (float) c.getRed();
        float g = 0.003921569F * (float) c.getGreen();
        float b = 0.003921569F * (float) c.getBlue();
        return (new Color(r, g, b, alpha)).getRGB();
    }

    public static void drawRect(double x, double y, double x2, double y2, int color) {
        float red = (color >> 24 & 0xFF) / 255.0F;
        float green = (color >> 16 & 0xFF) / 255.0F;
        float blue = (color >> 8 & 0xFF) / 255.0F;
        float alpha = (color & 0xFF) / 255.0F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        GL11.glPushMatrix();
        glColor4f(green, blue, alpha, red);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);

        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    public static void drawGradientRect(double left, double top, double right, double bottom, int startColor,
                                        int endColor) {
        float var7 = (float) (startColor >> 24 & 255) / 255.0F;
        float var8 = (float) (startColor >> 16 & 255) / 255.0F;
        float var9 = (float) (startColor >> 8 & 255) / 255.0F;
        float var10 = (float) (startColor & 255) / 255.0F;
        float var11 = (float) (endColor >> 24 & 255) / 255.0F;
        float var12 = (float) (endColor >> 16 & 255) / 255.0F;
        float var13 = (float) (endColor >> 8 & 255) / 255.0F;
        float var14 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator var15 = Tessellator.getInstance();
        WorldRenderer worldRenderer = var15.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.color(var8, var9, var10, var7);
        worldRenderer.pos(right, top, 0);
        worldRenderer.pos(left, top, 0);
        worldRenderer.color(var12, var13, var14, var11);
        worldRenderer.pos(left, bottom, 0);
        worldRenderer.pos(right, bottom, 0);
        var15.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    private static void circle(double d, double e, float radius, int fill) {
        arc(d, e, 0.0F, 360.0F, radius, fill);
    }

    private static void arc(double d, double e, float start, float end, float radius, int color) {
        arcEllipse(d, e, start, end, radius, radius, color);
    }

    private static void arcEllipse(double d, double e, float start, float end, float w, float h, int color) {
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        glColor4f(0.0F, 0.0F, 0.0F, 0.0F);

        float temp = 0.0F;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }

        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        float alpha = (color >> 24 & 0xFF) / 255.0F;

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.color(red, green, blue, alpha);
        if (alpha > 0.5F) {
            glEnable(GL_LINE_SMOOTH);
            glLineWidth(2.0F);
            glBegin(3);
            for (float i = end; i >= start; i -= 4.0F) {
                float ldx = (float) Math.cos(i * 3.141592653589793D / 180.0D) * (w * 1.001F);
                float ldy = (float) Math.sin(i * 3.141592653589793D / 180.0D) * (h * 1.001F);
                glVertex2d(d + ldx, e + ldy);
            }
            glEnd();
            glDisable(GL_LINE_SMOOTH);
        }
        glBegin(GL_TRIANGLE_FAN);
        for (float i = end; i >= start; i -= 4.0F) {
            float ldx = (float) Math.cos(i * 3.141592653589793D / 180.0D) * w;
            float ldy = (float) Math.sin(i * 3.141592653589793D / 180.0D) * h;
            glVertex2d(d + ldx, e + ldy);
        }
        glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRoundedRect(double left, double top, double right, double bottom, float roundness, int color) {
        left = (float) (left + (roundness / 2.0F + 0.5D));
        top = (float) (top + (roundness / 2.0F + 0.5D));
        right = (float) (right - (roundness / 2.0F + 0.5D));
        bottom = (float) (bottom - (roundness / 2.0F + 0.5D));
        drawRect(left, top, right, bottom, color);
        circle(right - roundness / 2.0F, top + roundness / 2.0F, roundness, color);
        circle(left + roundness / 2.0F, bottom - roundness / 2.0F, roundness, color);
        circle(left + roundness / 2.0F, top + roundness / 2.0F, roundness, color);
        circle(right - roundness / 2.0F, bottom - roundness / 2.0F, roundness, color);
        drawRect(left - roundness / 2.0F - 0.5F, top + roundness / 2.0F, right, bottom - roundness / 2.0F, color);
        drawRect(left, top + roundness / 2.0F, right + roundness / 2.0F + 0.5F, bottom - roundness / 2.0F, color);
        drawRect(left + roundness / 2.0F, top - roundness / 2.0F - 0.5F, right - roundness / 2.0F, bottom - roundness / 2.0F, color);
        drawRect(left + roundness / 2.0F, top, right - roundness / 2.0F, bottom + roundness / 2.0F + 0.5F, color);
    }

    public static void drawBorderedRect(double left, double top, double right, double bottom, float borderWidth,
                                        int borderColor, int color) {
        float alpha = (borderColor >> 24 & 0xFF) / 255.0f;
        float red = (borderColor >> 16 & 0xFF) / 255.0f;
        float green = (borderColor >> 8 & 0xFF) / 255.0f;
        float blue = (borderColor & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        drawRect(left, top, right, bottom, color);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);

        if (borderWidth == 1.0F) {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }

        GL11.glLineWidth(borderWidth);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(left, top, 0.0F);
        worldRenderer.pos(left, bottom, 0.0F);
        worldRenderer.pos(right, bottom, 0.0F);
        worldRenderer.pos(right, top, 0.0F);
        worldRenderer.pos(left, top, 0.0F);
        worldRenderer.pos(right, top, 0.0F);
        worldRenderer.pos(left, bottom, 0.0F);
        worldRenderer.pos(right, bottom, 0.0F);
        tessellator.draw();
        GL11.glLineWidth(2.0F);

        if (borderWidth == 1.0F) {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void box(double x, double y, double z, double x2, double y2, double z2, Color color) {
        x = x - Minecraft.getMinecraft().getRenderManager().renderPosX;
        y = y - Minecraft.getMinecraft().getRenderManager().renderPosY;
        z = z - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        x2 = x2 - Minecraft.getMinecraft().getRenderManager().renderPosX;
        y2 = y2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        z2 = z2 - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x2, y2, z2));
        GL11.glColor4d(0, 0, 0, 0.5F);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x2, y2, z2));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL_BLEND);
    }

    /**
     * Renders a frame with any size and any color.
     *
     * @param x
     * @param y
     * @param z
     * @param x2
     * @param y2
     * @param z2
     * @param color
     */
    public static void frame(double x, double y, double z, double x2, double y2, double z2, Color color) {
        x = x - Minecraft.getMinecraft().getRenderManager().renderPosX;
        y = y - Minecraft.getMinecraft().getRenderManager().renderPosY;
        z = z - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        x2 = x2 - Minecraft.getMinecraft().getRenderManager().renderPosX;
        y2 = y2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        z2 = z2 - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x2, y2, z2));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL_BLEND);
    }

    public static void framelessBlockESP(BlockPos blockPos, Color color) {
        double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glColor4d(color.getRed() / 255, color.getGreen() / 255, color.getBlue() / 255, 0.15);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL_BLEND);
    }

    public static int enemy = 0;
    public static int friend = 1;
    public static int other = 2;
    public static int target = 3;
    public static int team = 4;

    public static void drawColorBox(AxisAlignedBB axisalignedbb) {
        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);// Starts X.
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        ts.draw();
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        ts.draw();// Ends X.
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);//// Starts Y.
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        ts.draw();
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        ts.draw();// Ends Y.
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);//// Starts Z.
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        ts.draw();
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        wr.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        ts.draw();// Ends Z.
    }

    public static void tracerLine(Entity entity, int mode) {
        double x = entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double y = entity.posY + entity.height / 2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double z = entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        glBlendFunc(770, 771);
        glEnable(GL_BLEND);
        glLineWidth(2.0F);
        glDisable(GL11.GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        if (mode == 0)// Enemy
            GL11.glColor4d(1 - Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40,
                    Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40, 0, 0.5F);
        else if (mode == 1)// Friend
            GL11.glColor4d(0, 0, 1, 0.5F);
        else if (mode == 2)// Other
            GL11.glColor4d(1, 1, 0, 0.5F);
        else if (mode == 3)// Target
            GL11.glColor4d(1, 0, 0, 0.5F);
        else if (mode == 4)// Team
            GL11.glColor4d(0, 1, 0, 0.5F);
        glBegin(GL_LINES);
        {
            glVertex3d(0, Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0);
            glVertex3d(x, y, z);
        }
        glEnd();
        glEnable(GL11.GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public static void tracerLine(Entity entity, Color color) {
        double x = entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double y = entity.posY + entity.height / 2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double z = entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        glBlendFunc(770, 771);
        glEnable(GL_BLEND);
        glLineWidth(2.0F);
        glDisable(GL11.GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glBegin(GL_LINES);
        {
            glVertex3d(0, Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0);
            glVertex3d(x, y, z);
        }
        glEnd();
        glEnable(GL11.GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public static void tracerLine(int x, int y, int z, Color color) {
        x += 0.5 - Minecraft.getMinecraft().getRenderManager().renderPosX;
        y += 0.5 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        z += 0.5 - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        glBlendFunc(770, 771);
        glEnable(GL_BLEND);
        glLineWidth(2.0F);
        glDisable(GL11.GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glBegin(GL_LINES);
        {
            glVertex3d(0, Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0);
            glVertex3d(x, y, z);
        }
        glEnd();
        glEnable(GL11.GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        tessellator.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
        tessellator.draw();
    }

    public static void drawOutlinedBlockESP(double x, double y, double z, float red, float green, float blue,
                                            float alpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(lineWidth);
        glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawBlockESP(double x, double y, double z, float red, float green, float blue, float alpha,
                                    float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glLineWidth(lineWidth);
        glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue,
                                         float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red,
                                             float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawSolidEntityESP(double x, double y, double z, double width, double height, float red,
                                          float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green,
                                     float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha,
                                      float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWdith);
        glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0D, 0.0D + Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0D);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public static void drawCircle(int x, int y, double r, int c) {
        float f = ((c >> 24) & 0xff) / 255F;
        float f1 = ((c >> 16) & 0xff) / 255F;
        float f2 = ((c >> 8) & 0xff) / 255F;
        float f3 = (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 360; i++) {
            double x2 = Math.sin(((i * Math.PI) / 180)) * r;
            double y2 = Math.cos(((i * Math.PI) / 180)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawFilledCircle(int x, int y, double r, int c) {
        float f = ((c >> 24) & 0xff) / 255F;
        float f1 = ((c >> 16) & 0xff) / 255F;
        float f2 = ((c >> 8) & 0xff) / 255F;
        float f3 = (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        for (int i = 0; i <= 360; i++) {
            double x2 = Math.sin(((i * Math.PI) / 180)) * r;
            double y2 = Math.cos(((i * Math.PI) / 180)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void dr(double i, double j, double k, double l, int i1) {
        if (i < k) {
            double j1 = i;
            i = k;
            k = j1;
        }

        if (j < l) {
            double k1 = j;
            j = l;
            l = k1;
        }

        float f = ((i1 >> 24) & 0xff) / 255F;
        float f1 = ((i1 >> 16) & 0xff) / 255F;
        float f2 = ((i1 >> 8) & 0xff) / 255F;
        float f3 = (i1 & 0xff) / 255F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(f1, f2, f3, f);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);//
        worldRenderer.pos(i, l, 0.0D);
        worldRenderer.pos(k, l, 0.0D);
        worldRenderer.pos(k, j, 0.0D);
        worldRenderer.pos(i, j, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

}
