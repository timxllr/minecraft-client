package de.crazymemecoke.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.ui.menus.ClientManager;
import de.crazymemecoke.ui.tabgui.TabGUI;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GuiIngameHook extends GuiIngame {

    Minecraft mc = Wrapper.mc;

    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        if (!(Client.getInstance().getModuleManager().getModByName("Invis").getState())) {
            Display.setTitle(Client.getInstance().getClientName() + " " + Client.getInstance().getClientVersion() + " | made by " + Client.getInstance().getClientAuthor());
            if (Client.getInstance().getModuleManager().getModByName("HUD").getState()) {
                if (Client.getInstance().getSetmgr().getSettingByName("Developer Mode", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
                    doRenderStuff();
                } else {
                    if (mc.currentScreen == null && !mc.gameSettings.showDebugInfo) {
                        doRenderStuff();
                    }
                }
            }
        } else {
            Display.setTitle("Minecraft 1.8.8");
        }
    }

    private void doRenderStuff() {
        if (Client.getInstance().getSetmgr().getSettingByName("Watermark", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderWatermark();
        }
        if (Client.getInstance().getSetmgr().getSettingByName("TabGUI", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderTabGUI();
        }
        if (Client.getInstance().getSetmgr().getSettingByName("ArrayList", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderArrayList();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            mc.displayGuiScreen(new ClientManager());
        }
        if (Client.getInstance().getSetmgr().getSettingByName("Hotbar", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderHotbarInfo();
        }
    }

    private void renderHotbarInfo() {
        ScaledResolution s = new ScaledResolution(mc);
        if (Client.getInstance().getSetmgr().getSettingByName("Hotbar Design", Client.getInstance().getModuleManager().getModByName("HUD")).getValString().equalsIgnoreCase("Big Hotbar")) {
            String l1 = "FPS: " + Minecraft.debugFPS;
            String l2 = "Name: " + mc.thePlayer.getName();
            String date = (new SimpleDateFormat("dd.MM.YYYY")).format(Calendar.getInstance().getTime());
            String time = (new SimpleDateFormat("hh:mm")).format(Calendar.getInstance().getTime());
            String r1 = date + " / " + time;
            String r2 = (int) mc.thePlayer.posX + " / " + (int) mc.thePlayer.posY + " / " + (int) mc.thePlayer.posZ;

            if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Normal")) {
                Client.getInstance().getFontManager().comfortaa20.drawString(l1, 2, s.getScaledHeight() - 21, Rainbow.rainbowNormal(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(l2, 2, s.getScaledHeight() - 11, Rainbow.rainbowNormal(1, 1).getRGB());

                Client.getInstance().getFontManager().comfortaa20.drawString(r1, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight() - 21, Rainbow.rainbowNormal(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(r2, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r2), s.getScaledHeight() - 11, Rainbow.rainbowNormal(1, 1).getRGB());
            } else if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Candy")) {
                Client.getInstance().getFontManager().comfortaa20.drawString(l1, 2, s.getScaledHeight() - 21, Rainbow.rainbowCandy(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(l2, 2, s.getScaledHeight() - 11, Rainbow.rainbowCandy(1, 1).getRGB());

                Client.getInstance().getFontManager().comfortaa20.drawString(r1, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight() - 21, Rainbow.rainbowCandy(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(r2, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r2), s.getScaledHeight() - 11, Rainbow.rainbowCandy(1, 1).getRGB());
            }
        } else {
            String l1 = "FPS: " + Minecraft.debugFPS;
            String l2 = "Name: " + mc.thePlayer.getName();
            String date = (new SimpleDateFormat("dd.MM.YYYY")).format(Calendar.getInstance().getTime());
            String time = (new SimpleDateFormat("hh:mm")).format(Calendar.getInstance().getTime());
            String r1 = date + " / " + time;
            String r2 = (int) mc.thePlayer.posX + " / " + (int) mc.thePlayer.posY + " / " + (int) mc.thePlayer.posZ;

            RenderUtils.drawRect(0, s.getScaledHeight() - 22, Client.getInstance().getFontManager().comfortaa20.getStringWidth(l2) + 5, s.getScaledHeight(), new Color(0, 0, 0, 200).hashCode());
            RenderUtils.drawRect(0, s.getScaledHeight() - 23, Client.getInstance().getFontManager().comfortaa20.getStringWidth(l2) + 5, s.getScaledHeight() - 22, new Color(30, 148, 233, 255).hashCode());
            RenderUtils.drawRect(Client.getInstance().getFontManager().comfortaa20.getStringWidth(l2) + 5, s.getScaledHeight() - 23, Client.getInstance().getFontManager().comfortaa20.getStringWidth(l2) + 6, s.getScaledHeight(), new Color(30, 148, 233, 255).hashCode());

            RenderUtils.drawRect(s.getScaledWidth() - 5 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight() - 22, s.getScaledWidth(), s.getScaledHeight(), new Color(0, 0, 0, 200).hashCode());
            RenderUtils.drawRect(s.getScaledWidth() - 5 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1) - 1, s.getScaledHeight() - 23, s.getScaledWidth(), s.getScaledHeight() - 22, new Color(30, 148, 233, 255).hashCode());
            RenderUtils.drawRect(s.getScaledWidth() - 5 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1) - 1, s.getScaledHeight() - 23, s.getScaledWidth() - 5 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight(), new Color(30, 148, 233, 255).hashCode());

            if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Normal")) {
                Client.getInstance().getFontManager().comfortaa20.drawString(l1, 2, s.getScaledHeight() - 21, Rainbow.rainbowNormal(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(l2, 2, s.getScaledHeight() - 11, Rainbow.rainbowNormal(1, 1).getRGB());

                Client.getInstance().getFontManager().comfortaa20.drawString(r1, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight() - 21, Rainbow.rainbowNormal(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(r2, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r2), s.getScaledHeight() - 11, Rainbow.rainbowNormal(1, 1).getRGB());
            } else if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Candy")) {
                Client.getInstance().getFontManager().comfortaa20.drawString(l1, 2, s.getScaledHeight() - 21, Rainbow.rainbowCandy(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(l2, 2, s.getScaledHeight() - 11, Rainbow.rainbowCandy(1, 1).getRGB());

                Client.getInstance().getFontManager().comfortaa20.drawString(r1, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r1), s.getScaledHeight() - 21, Rainbow.rainbowCandy(1, 1).getRGB());
                Client.getInstance().getFontManager().comfortaa20.drawString(r2, s.getScaledWidth() - 4 - Client.getInstance().getFontManager().comfortaa20.getStringWidth(r2), s.getScaledHeight() - 11, Rainbow.rainbowCandy(1, 1).getRGB());
            }
        }
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);

        if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Normal")) {
            Client.getInstance().getFontManager().comfortaa40.drawString(Client.getInstance().getClientName(), s.getScaledWidth() - 85, 2, Rainbow.rainbowNormal(1, 1).getRGB());
        } else if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Candy")) {
            Client.getInstance().getFontManager().comfortaa40.drawString(Client.getInstance().getClientName(), s.getScaledWidth() - 85, 2, Rainbow.rainbowCandy(1, 1).getRGB());
        }
    }

    private void renderTabGUI() {
        TabGUI gui = new TabGUI(mc);
        gui.drawGui(5, 5, 55);
    }

    private void renderArrayList() {
        ScaledResolution s = new ScaledResolution(mc);
        if (!Client.getInstance().getSetmgr().getSettingByName("Watermark", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            int yDist = 1;
            for (Module m : Client.getInstance().getModuleManager().modules) {
                {
                    if (m.getState() && !m.isCategory(Category.GUI)) {

                        if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Normal")) {
                            Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                    yDist, Rainbow.rainbowNormal(1, 1).getRGB());
                        } else if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Candy")) {
                            Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                    yDist, Rainbow.rainbowCandy(1, 1).getRGB());
                        }
                        yDist += 10;
                    }
                }
            }
        } else {
            int yDist = 20;
            for (Module m : Client.getInstance().getModuleManager().modules) {
                {
                    if (m.getState() && !m.isCategory(Category.GUI)) {

                        if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Normal")) {
                            Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                    yDist, Rainbow.rainbowNormal(1, 1).getRGB());
                        } else if (Client.getInstance().getColorMode().getValString().equalsIgnoreCase("Candy")) {
                            Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                    yDist, Rainbow.rainbowCandy(1, 1).getRGB());
                        }
                        yDist += 10;
                    }
                }
            }
        }
    }
}