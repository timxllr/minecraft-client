package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.eventmanager.impl.EventTick;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.Vec4;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrailESP extends Module {

    private final List<Vec4> points = new ArrayList<>();
    private final TimeHelper timer = new TimeHelper();

    public TrailESP() {
        super("TrailESP", Keyboard.KEY_NONE, Category.RENDER, -1);

        Client.main().setMgr().newSetting(new Setting("Point Counter", this, 100, 10, 1000, true));
        Client.main().setMgr().newSetting(new Setting("Trail Length", this, 1000, 100, 10000, true));
        Client.main().setMgr().newSetting(new Setting("Trail Width", this, 2, 1, 50, true));
        Client.main().setMgr().newSetting(new Setting("Rainbow", this, true));
    }

    @Override
    public void onEvent(Event event) {
        int pointCounter = (int) Client.main().setMgr().settingByName("Point Counter", this).getNum();
        int trailLength = (int) Client.main().setMgr().settingByName("Trail Length", this).getNum();
        int trailWidth = (int) Client.main().setMgr().settingByName("Trail Width", this).getNum();
        boolean isRainbow = Client.main().setMgr().settingByName("Rainbow", this).getBool();

        if (event instanceof EventTick) {
            try {
                if (EntityUtils.isMoving()) {
                    if (timer.hasReached(pointCounter)) {
                        points.add(new Vec4(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, System.currentTimeMillis()));
                    }
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            for (int i = 0; i < points.size(); i++) {
                if (System.currentTimeMillis() - points.get(i).getW() > trailLength) {
                    points.remove(i);
                }
            }
        }

        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.threeD) {
                if (isRainbow) {
                    GlStateManager.pushMatrix();
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glLineWidth(trailWidth);
                    GL11.glBegin(GL11.GL_LINE_STRIP);
                    int offset = 0;
                    for (int i = 0; i < points.size(); i++) {
                        Color color = Rainbow.rainbow(offset, 1);
                        GL11.glColor3f(((float) color.getRed() / 255), ((float) color.getGreen() / 255), ((float) color.getBlue() / 255));
                        offset += 30;
                        GL11.glVertex3d(points.get(i).getX() - RenderManager.renderPosX, points.get(i).getY() - RenderManager.renderPosY, points.get(i).getZ() - RenderManager.renderPosZ);
                    }
                    GL11.glColor3d(1, 1, 1);
                    GL11.glEnd();
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GlStateManager.popMatrix();
                } else {
                    GlStateManager.pushMatrix();
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glLineWidth(trailWidth);
                    GL11.glColor3d(1, 1, 1);
                    GL11.glBegin(GL11.GL_LINE_STRIP);
                    for (int i = 0; i < points.size(); i++) {
                        GL11.glVertex3d(points.get(i).getX() - RenderManager.renderPosX, points.get(i).getY() - RenderManager.renderPosY, points.get(i).getZ() - RenderManager.renderPosZ);
                    }
                    GL11.glColor3d(1, 1, 1);
                    GL11.glEnd();
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GlStateManager.popMatrix();
                }
            }
        }
    }
}
