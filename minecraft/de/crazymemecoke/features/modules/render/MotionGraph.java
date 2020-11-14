package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.eventmanager.impl.EventTick;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MotionGraph extends Module {

    private final List<Double> motionSpeed = new ArrayList<>();

    public MotionGraph() {
        super("MotionGraph", Keyboard.KEY_NONE, Category.RENDER);

        Client.main().setMgr().addSetting(new Setting("Outline", this, true));
        Client.main().setMgr().addSetting(new Setting("Rainbow", this, true));
    }

    @Override
    public void onEvent(Event event) {
        boolean isOutlined = Client.main().setMgr().settingByName("Outline", this).getBool();
        boolean isRainbow = Client.main().setMgr().settingByName("Rainbow", this).getBool();

        if (event instanceof EventTick) {
            motionSpeed.add(Math.hypot(mc.thePlayer.motionX, mc.thePlayer.motionZ) * 100);

            if (motionSpeed.size() > 70) {
                motionSpeed.remove((motionSpeed.size() - 71));
            }
        }
        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.twoD) {
                ScaledResolution sr = new ScaledResolution(mc);

                if (isOutlined) {
                    if (isRainbow) {
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                        GL11.glColor3f(0, 0, 0);
                        GL11.glLineWidth(4);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add2 = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            GL11.glVertex2d(sr.width() / 2 - 60 + add2, sr.height() - 75 - motionSpeed.get(i));
                            add2 += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();

                        GL11.glPushMatrix();
                        GL11.glColor3f(1, 1, 1);
                        GL11.glLineWidth(2);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add = 0;
                        int offset = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            Color color = Rainbow.rainbow(offset, 1);
                            GL11.glColor3f(((float) color.getRed() / 255), ((float) color.getGreen() / 255), ((float) color.getBlue() / 255));
                            offset += 30;
                            GL11.glVertex2d(sr.width() / 2 - 60 + add, sr.height() - 75 - motionSpeed.get(i));
                            add += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                    } else {
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                        GL11.glColor3f(0, 0, 0);
                        GL11.glLineWidth(4);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add2 = 0;
                        int offset = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            GL11.glVertex2d(sr.width() / 2 - 60 + add2, sr.height() - 75 - motionSpeed.get(i));
                            add2 += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();

                        GL11.glPushMatrix();
                        GL11.glColor3f(1, 1, 1);
                        GL11.glLineWidth(2);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            GL11.glVertex2d(sr.width() / 2 - 60 + add, sr.height() - 75 - motionSpeed.get(i));
                            add += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                    }
                } else {
                    if (isRainbow) {
                        GL11.glPushMatrix();
                        GL11.glColor3f(1, 1, 1);
                        GL11.glLineWidth(2);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add = 0;
                        int offset = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            Color color = Rainbow.rainbow(offset, 1);
                            GL11.glColor3f(((float) color.getRed() / 255), ((float) color.getGreen() / 255), ((float) color.getBlue() / 255));
                            offset += 30;
                            GL11.glVertex2d(sr.width() / 2 - 60 + add, sr.height() - 75 - motionSpeed.get(i));
                            add += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                    } else {
                        GL11.glPushMatrix();
                        GL11.glColor3f(1, 1, 1);
                        GL11.glLineWidth(2);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        double add = 0;
                        for (int i = 0; i < motionSpeed.size(); i++) {
                            GL11.glVertex2d(sr.width() / 2 - 60 + add, sr.height() - 75 - motionSpeed.get(i));
                            add += 2;
                        }
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                    }
                }
            }
        }
    }
}
