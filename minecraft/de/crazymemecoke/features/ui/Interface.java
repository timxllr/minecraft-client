package de.crazymemecoke.features.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.combat.Aura;
import de.crazymemecoke.features.ui.guiscreens.clienthelper.GuiClientHelper;
import de.crazymemecoke.features.ui.tabgui.TabGUI;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
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
    FontManager font = Client.main().fontMgr();

    public Interface(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        if (!(Client.main().modMgr().getByName("Invis").state())) {
            Display.setTitle(Client.main().getClientName() + " " + Client.main().getClientVersion() + " | made by " + Client.main().getClientCoder());
            if (Client.main().modMgr().getByName("HUD").state()) {
                if (Client.main().setMgr().settingByName("Developer Mode", Client.main().modMgr().getByName("HUD")).getBool()) {
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
        if (Client.main().setMgr().settingByName("Watermark", Client.main().modMgr().getByName("HUD")).getBool()) {
            renderWatermark();
        }
        if (Client.main().setMgr().settingByName("TabGUI", Client.main().modMgr().getByName("HUD")).getBool()) {
            renderTabGUI();
        }
        if (Client.main().setMgr().settingByName("ArrayList", Client.main().modMgr().getByName("HUD")).getBool()) {
            renderArrayList();
        }
        if (Client.main().setMgr().settingByName("Target HUD", Client.main().modMgr().getByName("HUD")).getBool()) {
            renderTargetHUD();
        }
        if (Client.main().setMgr().settingByName("KeyStrokes", Client.main().modMgr().getByName("HUD")).getBool()) {
            renderKeyStrokes();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            mc.displayGuiScreen(new GuiClientHelper());
        }
    }

    private void renderKeyStrokes() {
        ScaledResolution s = new ScaledResolution(mc);

        RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 70, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 90, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawFilledCircle(s.width() - 30, s.height() - 40, 13, new Color(0, 0, 0, 150).getRGB());

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 70, 13, Client.main().getAmbienOldBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            RenderUtils.drawFilledCircle(s.width() - 90, s.height() - 40, 13, Client.main().getAmbienOldBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            RenderUtils.drawFilledCircle(s.width() - 60, s.height() - 40, 13, Client.main().getAmbienOldBlueColor());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            RenderUtils.drawFilledCircle(s.width() - 30, s.height() - 40, 13, Client.main().getAmbienOldBlueColor());
        }

        Client.main().fontMgr().font("esp", 25, Font.PLAIN).drawStringWithShadow("W", s.width() - 66, s.height() - 73, -1);
        Client.main().fontMgr().font("esp", 25, Font.PLAIN).drawStringWithShadow("A", s.width() - 95, s.height() - 43, -1);
        Client.main().fontMgr().font("esp", 25, Font.PLAIN).drawStringWithShadow("S", s.width() - 65, s.height() - 43, -1);
        Client.main().fontMgr().font("esp", 25, Font.PLAIN).drawStringWithShadow("D", s.width() - 34, s.height() - 43, -1);
    }

    private void renderTargetHUD() {
        ScaledResolution s = new ScaledResolution(mc);

        if (Aura.currentTarget instanceof EntityPlayer && Client.main().setMgr().settingByName("Target HUD", Client.main().modMgr().getByName("HUD")).getBool()) {
            RenderUtils.drawRect(s.width() / 2 - 130, s.height() / 2 - 50, s.width() / 2 + 130, s.height() / 2 + 50, new Color(0, 0, 0, 110).getRGB());

            EntityPlayer p = (EntityPlayer) Aura.currentTarget;
            Client.main().fontMgr().cabin23.drawStringWithShadow("Spieler: " + p.getName(), s.width() / 2 - 125, s.height() / 2 - 45, -1);
            Client.main().fontMgr().cabin23.drawStringWithShadow("Leben: " + p.getHealth() + " / " + p.getMaxHealth(), s.width() / 2 - 125, s.height() / 2 - 35, -1);

            ItemStack i = p.getCurrentEquippedItem();
            if (i == null) {
                Client.main().fontMgr().cabin23.drawStringWithShadow("Item: Kein Item", s.width() / 2 - 125, s.height() / 2 - 25, -1);
            } else {
                Client.main().fontMgr().cabin23.drawStringWithShadow("Item: " + i.getDisplayName(), s.width() / 2 - 125, s.height() / 2 - 25, -1);
            }

            GuiInventory.drawEntityOnScreen(51, 75, 30, (float) (51) - 50, (float) (75 - 50) - 20, (EntityLivingBase) Aura.currentTarget);

        }
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);

        String mode = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();

        switch (mode) {
            case "ambien old": {
                RenderUtils.drawRect(0, 0, 73, 25, new Color(0, 0, 0).getRGB());
                Client.main().fontMgr().ambien45.drawStringWithShadow("A", 3, 1, Client.main().getAmbienOldBlueColor());
                Client.main().fontMgr().ambien45.drawStringWithShadow("mbien", 17, 1, Client.main().getGrey());
                Client.main().fontMgr().ambien20.drawStringWithShadow(Client.main().getClientVersion(), 55, 0, 0x349ac0);
                break;
            }
            case "vortex": {
                GL11.glPushMatrix();
                GL11.glScalef(2f, 2f, 2f);
                Client.main().fontMgr().vortex20.drawString("V", 0, -2, Client.main().getVortexRedColor());
                GL11.glScalef(2f / 4, 2f / 4, 2f / 4);
                GL11.glPopMatrix();

                Client.main().fontMgr().vortex20.drawString("ortex", 18, 6, Client.main().getGrey());

                GL11.glPushMatrix();
                GL11.glScalef(0.8f, 0.8f, 0.8f);
                Client.main().fontMgr().vortex20.drawString(Client.main().getClientVersion(), 72, 9, 0x349ac0);
                GL11.glPopMatrix();
                break;
            }
            case "suicide": {
                Client.main().fontMgr().font("Comfortaa", 30, Font.PLAIN).drawStringWithShadow("SUICIDE", 2, 3, Client.main().getSuicideBlueColor());
                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(Client.main().getClientVersion(), 68, 4, Client.main().getSuicideBlueColor());

                RenderUtils.drawRect(0, 110, 75, 151, Client.main().getSuicideDarkBlueGreyColor());
                RenderUtils.drawRect(0, 110, 73, 153, Client.main().getSuicideBlueGreyColor());

                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("FPS: ", 2, 115, Client.main().getSuicideBlueColor());
                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(String.valueOf(Minecraft.debugFPS), 26, 115, -1);

                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("Ping: ", 2, 127, Client.main().getSuicideBlueColor());
                try {
                    Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(String.valueOf(mc.getCurrentServerData().pingToServer), 30, 127, -1);
                } catch (Exception ex) {
                    Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("N/A", 30, 127, -1);
                }

                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow("Time: ", 2, 139, Client.main().getSuicideBlueColor());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN).drawStringWithShadow(dtf.format(now), 32, 139, -1);
                break;
            }
            case "apinity": {
                RenderUtils.drawRect(0, 35, 40, 175, new Color(0, 0, 0, 150).getRGB());

                Client.main().fontMgr().font("Raleway Light", 45, Font.PLAIN).drawStringWithShadow("Apinity", 1, 1, Client.main().getApinityGreyColor());
                Client.main().fontMgr().font("Raleway Light", 30, Font.PLAIN).drawStringWithShadow(Client.main().getClientVersion(), 72, 1, Client.main().getApinityBlueColor());
                try {
                    Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("Ping: " + mc.getCurrentServerData().pingToServer, 2, 116, Client.main().getApinityBlueColor());
                } catch (Exception ex) {
                    Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("Ping: N/A", 1, 116, Client.main().getApinityBlueColor());
                }

                Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("FPS: " + Minecraft.debugFPS, 2, 125, Client.main().getApinityBlueColor());

                Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("X: " + mc.thePlayer.getPosition().getX(), 2, 145, Client.main().getApinityBlueColor());
                Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("Y: " + mc.thePlayer.getPosition().getY(), 2, 155, Client.main().getApinityBlueColor());
                Client.main().fontMgr().font("Comfortaa", 15, Font.PLAIN).drawStringWithShadow("Z: " + mc.thePlayer.getPosition().getZ(), 2, 165, Client.main().getApinityBlueColor());
                break;
            }
            case "huzuni": {
                Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow("Huzuni Dev " + Client.main().getClientVersion(), 2, 2, -1);
                break;
            }
            case "wurst": {
                RenderUtils.drawRect(0, 10, 145, 28, new Color(255, 255, 255, 130).getRGB());

                Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawString("v" + Client.main().getClientVersion() + " MC1.8.8", 80, 15, new Color(0, 0, 0).getRGB());

                int width = 75;
                int height = 20;
                int x = 0;
                int y = 9;

                RenderUtils.drawImage(Client.main().getWurstWatermark(), x, y, width, height);
                break;
            }
            case "nodus": {
                mc.fontRendererObj.drawString("Nodus", 2, 2, Client.main().getNodusPurpleColor());
                mc.fontRendererObj.drawString("v" + Client.main().getClientVersion(), 35, 2, Client.main().getNodusTealColor());
                break;
            }
            case "saint": {
                UnicodeFontRenderer f1 = Client.main().fontMgr().font("Verdana", 20, Font.PLAIN);
                UnicodeFontRenderer f2 = Client.main().fontMgr().font("Verdana", 17, Font.PLAIN);
                String s1 = "Saint";
                String s2 = "v" + Client.main().getClientVersion();
                f1.drawStringWithShadow(s1, s.width() / 2 - f1.getStringWidth(s1) / 2, 3, -1);
                f2.drawStringWithShadow(s2, s.width() / 2 - f2.getStringWidth(s2) / 2, 13, new Color(0x4A4A4A).getRGB());

                f2.drawStringWithShadow("FPS:", 2, 88, -1);
                f2.drawStringWithShadow(String.valueOf(Minecraft.debugFPS), 22, 88, new Color(0x4A4A4A).getRGB());
                break;
            }
            case "icarus old": {
                mc.fontRendererObj.drawString("Icarus (b" + Client.main().getClientVersion() + ")", 2, 2, -1);
                break;
            }
            case "icarus new": {
                RenderUtils.drawRect(s.width() - 62, 0, s.width(), 20, Client.main().getIcarusNewGreyColor());

                Client.main().fontMgr().font("BigNoodleTiltling", 40, Font.BOLD).drawStringWithShadow("Icarus", s.width() - 60, -2, -1);
                break;
            }
            case "ambien new": {
                RenderUtils.drawRect(0, 10, 112, 40, Client.main().getAmbienNewDarkGreyColor());

                Client.main().fontMgr().font("FIFA Welcome", 60, Font.PLAIN).drawStringWithShadow("A", 2, 7, Client.main().getAmbienNewBlueColor());
                Client.main().fontMgr().font("FIFA Welcome", 60, Font.PLAIN).drawStringWithShadow("mbien", 20, 7, -1);
                Client.main().fontMgr().font("BigNoodleTiltling", 20, Font.PLAIN).drawStringWithShadow("V", 89, 24, Client.main().getAmbienNewBlueColor());
                Client.main().fontMgr().font("BigNoodleTiltling", 20, Font.PLAIN).drawStringWithShadow(Client.main().getClientVersion(), 96, 24, -1);
                break;
            }
            case "hero": {
                RenderUtils.drawRect(0, 0, 60, 21, new Color(0, 0, 0, 70).getRGB());

                Client.main().fontMgr().font("Raleway Light", 55, Font.PLAIN).drawStringWithShadow("Hero", -2, -4, Client.main().getHeroGreenColor());
                break;
            }
            case "klientus": {
                Client.main().fontMgr().font("Verdana", 50, Font.PLAIN).drawStringWithShadow("K", 1, 1, new Color(0x00659f).getRGB());
                Client.main().fontMgr().font("Verdana", 50, Font.PLAIN).drawStringWithShadow("lientus", 18, 1, -1);

                RenderUtils.drawRect(3, 28, 105, 30, -1);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                Client.main().fontMgr().font("Verdana", 18, Font.PLAIN).drawStringWithShadow(dtf.format(now), 32, 32, -1);
                break;
            }
        }
    }

    private void renderTabGUI() {
        TabGUI gui = new TabGUI(mc);

        if (Client.main().setMgr().settingByName("Watermark", Client.main().modMgr().getByName("HUD")).getBool()) {
            String mode = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();

            switch (mode) {
                case "ambien old": {
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
                case "apinity": {
                    gui.drawGui(5, 40, 55);
                    break;
                }
                case "huzuni": {
                    gui.drawGui(3, 15, 95);
                    break;
                }
                case "saint": {
                    gui.drawGui(3, 15, 65);
                    break;
                }
                case "icarus old": {
                    gui.drawGui(3, 15, 65);
                    break;
                }
                case "icarus new": {
                    gui.drawGui(3, 15, 65);
                    break;
                }
                case "ambien new": {
                    gui.drawGui(1, 45, 65);
                    break;
                }
                case "hero": {
                    gui.drawGui(1, 22, 59);
                    break;
                }
                case "vanta": {
                    gui.drawGui(1, 20, 65);
                    break;
                }
            }
        }
    }

    private void renderArrayList() {
        ScaledResolution s = new ScaledResolution(mc);
        String module;

        int stringY = 2;
        int rectY = 1;

        String mode = Client.main().setMgr().settingByName("ArrayList Rect Mode", Client.main().modMgr().getByName("HUD")).getMode();
        String design = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();

        UnicodeFontRenderer comfortaa = Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN);
        UnicodeFontRenderer bigNoodleTilting = Client.main().fontMgr().font("BigNoodleTilting", 20, Font.PLAIN);

        for (Module m : Client.main().modMgr().modules) {
            module = m.name();
            if (m.state() && !m.isCategory(Category.GUI)) {
                if (Client.main().setMgr().settingByName("ArrayList Background", Client.main().modMgr().getByName("HUD")).getBool()) {
                    if (mode.equalsIgnoreCase("Left")) {
                        switch (design) {
                            case "ambien new":
                            case "ambien old": {
                                RenderUtils.drawRect(s.width() - bigNoodleTilting.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                RenderUtils.drawRect(s.width() - bigNoodleTilting.getStringWidth(module) - 5, (rectY - 2), s.width() - bigNoodleTilting.getStringWidth(module) - 3, (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                                bigNoodleTilting.drawStringWithShadow(module, s.width() - bigNoodleTilting.getStringWidth(module) - 2, stringY - 2, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                            default: {
                                RenderUtils.drawRect(s.width() - comfortaa.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                RenderUtils.drawRect(s.width() - comfortaa.getStringWidth(module) - 5, (rectY - 2), s.width() - comfortaa.getStringWidth(module) - 3, (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                                comfortaa.drawStringWithShadow(module, s.width() - comfortaa.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                        }
                    } else if (mode.equalsIgnoreCase("Right")) {
                        switch (design) {
                            case "ambien new":
                            case "ambien old": {
                                RenderUtils.drawRect(s.width() - bigNoodleTilting.getStringWidth(module) - 5, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                RenderUtils.drawRect(s.width() - 3, (rectY - 2), s.width(), (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                                bigNoodleTilting.drawStringWithShadow(module, s.width() - bigNoodleTilting.getStringWidth(module) - 4, stringY - 1, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                            default: {
                                RenderUtils.drawRect(s.width() - comfortaa.getStringWidth(module) - 5, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                RenderUtils.drawRect(s.width() - 3, (rectY - 2), s.width(), (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                                comfortaa.drawStringWithShadow(module, s.width() - comfortaa.getStringWidth(module) - 4, stringY, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                        }
                    } else if (mode.equalsIgnoreCase("None")) {
                        switch (design) {
                            case "ambien new":
                            case "ambien old": {
                                RenderUtils.drawRect(s.width() - bigNoodleTilting.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                bigNoodleTilting.drawStringWithShadow(module, s.width() - bigNoodleTilting.getStringWidth(module) - 1, stringY - 1, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                            default: {
                                RenderUtils.drawRect(s.width() - comfortaa.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                                comfortaa.drawStringWithShadow(module, s.width() - comfortaa.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                                break;
                            }
                        }
                    }
                } else {
                    Client.main().fontMgr().comfortaa20.drawString(module, s.width() - Client.main().fontMgr().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                }
                rectY += 12;
                stringY += 12;
            }
        }
    }
}