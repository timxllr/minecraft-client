package de.crazymemecoke.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.ui.guiscreens.ClientManager;
import de.crazymemecoke.ui.tabgui.TabGUI;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

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
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);
        Client.getInstance().getFontManager().comfortaa40.drawString(Client.getInstance().getClientName(), s.getScaledWidth() - 85, 2, Rainbow.rainbow(1, 1).getRGB());
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
                        Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                yDist, Rainbow.rainbow(1, 1).getRGB());
                        yDist += 10;
                    }
                }
            }
        } else {
            int yDist = 20;
            for (Module m : Client.getInstance().getModuleManager().modules) {
                {
                    if (m.getState() && !m.isCategory(Category.GUI)) {
                        Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                yDist, Rainbow.rainbow(1, 1).getRGB());
                        yDist += 10;
                    }
                }
            }
        }
    }
}