package de.crazymemecoke.features.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.combat.Aura;
import de.crazymemecoke.features.ui.guiscreens.clienthelper.GuiClientHelper;
import de.crazymemecoke.features.ui.tabgui.TabGUI;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Interface extends GuiIngame {

    Minecraft mc = Wrapper.mc;
    FontManager font = Client.instance().getFontManager();

    public Interface(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        if (!(Client.instance().modManager().getByName("Invis").getState())) {
            Display.setTitle(Client.instance().getClientName() + " " + Client.instance().getClientVersion() + " | made by " + Client.instance().getClientCoder());
            if (Client.instance().modManager().getByName("HUD").getState()) {
                if (Client.instance().setMgr().getSettingByName("Developer Mode", Client.instance().modManager().getByName("HUD")).getBool()) {
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
        if (Client.instance().setMgr().getSettingByName("Watermark", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderWatermark();
        }
        if (Client.instance().setMgr().getSettingByName("TabGUI", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderTabGUI();
        }
        if (Client.instance().setMgr().getSettingByName("ArrayList", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderArrayList();
        }
        if (Client.instance().setMgr().getSettingByName("Target HUD", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderTargetHUD();
        }
        if (Client.instance().setMgr().getSettingByName("KeyStrokes", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderKeyStrokes();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            mc.displayGuiScreen(new GuiClientHelper());
        }
    }

    private void renderKeyStrokes() {
        ScaledResolution s = new ScaledResolution(mc);

        /*
        W, A, S, D
         */
        RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 70, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 90, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 30, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 70, 13, Client.instance().getAmbienBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            RenderUtils.drawFilledCircle(s.width() - 90, s.height() - 40, 13, Client.instance().getAmbienBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 40, 13, Client.instance().getAmbienBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            RenderUtils.drawFilledCircle(s.width() - 30, s.height() - 40, 13, Client.instance().getAmbienBlueColor());
        }

        Client.instance().getFontManager().getFont("esp", 25, Font.PLAIN).drawStringWithShadow("W", s.width() - 66, s.height() - 73, -1);
        Client.instance().getFontManager().getFont("esp", 25, Font.PLAIN).drawStringWithShadow("A", s.width() - 95, s.height() - 43, -1);
        Client.instance().getFontManager().getFont("esp", 25, Font.PLAIN).drawStringWithShadow("S", s.width() - 65, s.height() - 43, -1);
        Client.instance().getFontManager().getFont("esp", 25, Font.PLAIN).drawStringWithShadow("D", s.width() - 34, s.height() - 43, -1);
    }

    private void renderTargetHUD() {
        ScaledResolution s = new ScaledResolution(mc);

        if (!(Aura.currentTarget == null) && Aura.currentTarget instanceof EntityPlayer && Client.instance().setMgr().getSettingByName("Target HUD", Client.instance().modManager().getByName("HUD")).getBool()) {
            RenderUtils.drawRect(s.width() / 2 - 130, s.height() / 2 - 50, s.width() / 2 + 130, s.height() / 2 + 50, new Color(0, 0, 0, 110).getRGB());

            EntityPlayer p = (EntityPlayer) Aura.currentTarget;
            Client.instance().getFontManager().cabin23.drawStringWithShadow("Spieler: " + p.getName(), s.width() / 2 - 125, s.height() / 2 - 45, -1);
            Client.instance().getFontManager().cabin23.drawStringWithShadow("Leben: " + p.getHealth() + " / " + p.getMaxHealth(), s.width() / 2 - 125, s.height() / 2 - 35, -1);

            ItemStack i = p.getCurrentEquippedItem();
            if (i == null) {
                Client.instance().getFontManager().cabin23.drawStringWithShadow("Item: Kein Item", s.width() / 2 - 125, s.height() / 2 - 25, -1);
            } else {
                Client.instance().getFontManager().cabin23.drawStringWithShadow("Item: " + i.getDisplayName(), s.width() / 2 - 125, s.height() / 2 - 25, -1);
            }

            // TODO: Player Model fixen (current state: not working)
            // GuiInventory.drawEntityOnScreen(51, 75, 30, (float) (51), (float) (75 - 50), p);

        }
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);

        String mode = Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode();

        switch (mode) {
            case "ambien": {
                RenderUtils.drawRect(0, 0, 73, 25, new Color(0, 0, 0).getRGB());
                Client.instance().getFontManager().ambien45.drawStringWithShadow("A", 3, 1, Client.instance().getAmbienBlueColor());
                Client.instance().getFontManager().ambien45.drawStringWithShadow("mbien", 17, 1, Client.instance().getGrey());
                Client.instance().getFontManager().ambien20.drawStringWithShadow(Client.instance().getClientVersion(), 55, 0, 0x349ac0);
                break;
            }
            case "vortex": {
                GL11.glPushMatrix();
                GL11.glScalef(2f, 2f, 2f);
                Client.instance().getFontManager().vortex20.drawString("V", 0, -2, Client.instance().getVortexRedColor());
                GL11.glScalef(2f / 4, 2f / 4, 2f / 4);
                GL11.glPopMatrix();

                Client.instance().getFontManager().vortex20.drawString("ortex", 18, 6, Client.instance().getGrey());

                GL11.glPushMatrix();
                GL11.glScalef(0.8f, 0.8f, 0.8f);
                Client.instance().getFontManager().vortex20.drawString(Client.instance().getClientVersion(), 72, 9, 0x349ac0);
                GL11.glPopMatrix();
                break;
            }
            case "suicide": {
                Client.instance().getFontManager().getFont("Comfortaa", 30, Font.PLAIN).drawStringWithShadow("SUICIDE", 2, 3, Client.instance().getSuicideBlueColor());
                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(Client.instance().getClientVersion(), 68, 4, Client.instance().getSuicideBlueColor());

                RenderUtils.drawRect(0, 110, 75, 151, Client.instance().getSuicideDarkBlueGreyColor());
                RenderUtils.drawRect(0, 110, 73, 153, Client.instance().getSuicideBlueGreyColor());

                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("FPS: ", 2, 115, Client.instance().getSuicideBlueColor());
                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(String.valueOf(mc.debugFPS), 26, 115, -1);

                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("Ping: ", 2, 127, Client.instance().getSuicideBlueColor());
                try {
                    Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(String.valueOf(mc.getCurrentServerData().pingToServer), 30, 127, -1);
                } catch (Exception ex) {
                    Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("N/A", 30, 127, -1);
                }

                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("Time: ", 2, 139, Client.instance().getSuicideBlueColor());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Client.instance().getFontManager().getFont("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(dtf.format(now), 32, 139, -1);
            }
        }

        /*
        This is the old Watermark.
        I don't use it for now.
         */

        /*int width = 65;
        int height = 65;

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.instance().getClientIcon()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());*/
    }

    private void renderTabGUI() {
        TabGUI gui = new TabGUI(mc);

        if (Client.instance().setMgr().getSettingByName("Watermark", Client.instance().modManager().getByName("HUD")).getBool()) {
            String mode = Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode();

            switch (mode) {
                case "ambien": {
                    gui.drawGui(1, 30, 72);
                    break;
                }
                case "vortex": {
                    gui.drawGui(1, 20, 72);
                    break;
                }
                case "suicide": {
                    gui.drawGui(1, 20, 72);
                    break;
                }
            }

        } else {
            gui.drawGui(1, 0, 72);
        }
    }

    private void renderArrayList() {
        ScaledResolution s = new ScaledResolution(mc);
        String module;
        int stringY = 2;
        int rectY = 1;

        String mode = Client.instance().setMgr().getSettingByName("ArrayList Rect Mode", Client.instance().modManager().getByName("HUD")).getMode();

        for (Module m : Client.instance().modManager().modules) {
            module = m.getName();
            if (m.getState() && !m.isCategory(Category.GUI)) {
                if (Client.instance().setMgr().getSettingByName("ArrayList Background", Client.instance().modManager().getByName("HUD")).getBool()) {
                    if (mode.equalsIgnoreCase("Left")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 5, (rectY - 2), s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                    } else if (mode.equalsIgnoreCase("Right")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 5, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        RenderUtils.drawRect(s.width() - 3, (rectY - 2), s.width(), (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 4, stringY, Rainbow.rainbow(1, 1).getRGB());
                    } else if (mode.equalsIgnoreCase("None")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                    }
                } else {
                    Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                }
                rectY += 12;
                stringY += 12;
            }
        }
    }
}