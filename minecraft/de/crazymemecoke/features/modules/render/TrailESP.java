package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.eventmanager.impl.EventTick;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.Vec4;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ModuleInfo(name = "TrailESP", category = Category.RENDER, description = "Renders a thin line as your movement path")
public class TrailESP extends Module {

    private final List<Vec4> points = new ArrayList<>();

    public Setting pointCounter = new Setting("Point Counter", this, 100, 10, 1000, true);
    public Setting trailLength = new Setting("Trail Length", this, 1000, 100, 10000, true);
    public Setting trailWidth = new Setting("Trail Width", this, 2, 1, 50, true);
    public Setting rainbow = new Setting("Rainbow", this, true);

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventTick) {
            try {
                if (timeHelper.hasReached((long) pointCounter.getCurrentValue())) {
                    points.add(new Vec4(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, System.currentTimeMillis()));
                }
            } catch (NullPointerException ignored) {
            }
            for (int i = 0; i < points.size(); i++) {
                if (System.currentTimeMillis() - points.get(i).getW() > trailLength.getCurrentValue()) {
                    points.remove(i);
                }
            }
        }

        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.threeD) {
                if (rainbow.isToggled()) {
                    GlStateManager.pushMatrix();
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glLineWidth((float) trailWidth.getCurrentValue());
                    GL11.glBegin(GL11.GL_LINE_STRIP);
                    int offset = 0;
                    for (int i = 0; i < points.size(); i++) {
                        Color color = Rainbow.getRainbow(offset, 10000, 1, 1);
                        GL11.glColor3f(((float) color.getRed() / 255), ((float) color.getGreen() / 255), ((float) color.getBlue() / 255));
                        offset += 200;
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
                    GL11.glLineWidth((float) trailWidth.getCurrentValue());
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
