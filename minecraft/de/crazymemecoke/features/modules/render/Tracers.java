package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Tracers extends Module {

    public Tracers() {
        super("Tracers", Keyboard.KEY_NUMPAD8, Category.RENDER, -1);
    }

    @Override
    public void onUpdate() {
        if (state()) {
            for (final EntityPlayer player : mc.theWorld.playerEntities) {
                if (mc.thePlayer != player) {
                    drawLine(player);
                }
            }
        }
    }

    private void drawLine(final EntityPlayer player) {
        final double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
        final double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
        final double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(0.85f);
        RenderUtils.color(new Color(0xffcd22).getRGB());
        GL11.glLoadIdentity();
        final boolean bobbing = mc.gameSettings.viewBobbing;
        mc.gameSettings.viewBobbing = false;
        mc.entityRenderer.orientCamera(mc.timer.renderPartialTicks);
        GL11.glBegin(3);
        GL11.glVertex3d(0.0, mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y + player.getEyeHeight(), z);
        GL11.glEnd();
        mc.gameSettings.viewBobbing = bobbing;
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
