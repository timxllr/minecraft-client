package de.crazymemecoke.features.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.RenderHelper;
import de.crazymemecoke.utils.render.Colors;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.ArrayList;

public class Tab {
    private final TabGUI gui;
    public ArrayList<Module> hacks;
    public String tabName;
    public int selectedItem = 0;
    public int menuHeight = 0;
    public int menuWidth = 0;
    private int colour;

    public Tab(TabGUI GUI, String TabName) {
        tabName = TabName;
        gui = GUI;
        hacks = new ArrayList<Module>();
    }

    public void countMenuSize() {
        int maxWidth = 0;
        for (int i = 0; i < hacks.size(); i++) {
            if (Minecraft.mc().fontRendererObj.getStringWidth(hacks.get(i).name() + 4) > maxWidth) {
                maxWidth = (int) (Minecraft.mc().fontRendererObj.getStringWidth(hacks.get(i).name()) + 7.5F);
            }
        }
        menuWidth = maxWidth;
        menuHeight = (hacks.size() * gui.tabHeight - 1);
    }

    public void drawTabMenu(int x, int y) {
        countMenuSize();
        x += 2;
        y += 2;
        // Background
        String mode = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();
        switch (mode) {
            case "ambien old":
            case "vortex": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, new Color(0, 0, 0).getRGB());
                break;
            }
            case "ambien newest": {
                RenderHelper.drawRect(x + 2, y + 2, x + menuWidth + 3, y + menuHeight + 2, Colors.main().getAmbienNewestGreyMainColor());
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getAmbienNewestGreySecondColor());
                break;
            }
            case "suicide": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getSuicideBlueGreyColor());
                break;
            }
            case "apinity": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getApinityGreyColor());
                break;
            }
            case "huzuni": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getHuzuniGreyColor());
                break;
            }
            case "saint": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getSaintDarkBlueColor());
                break;
            }
            case "icarus old": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getIcarusOldGreyColor());
                break;
            }
            case "icarus new": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getIcarusNewGreyColor());
                break;
            }
            case "ambien new": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getAmbienNewDarkGreyColor());
                break;
            }
            case "hero": {
                RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, Colors.main().getHeroGreyColor());
                break;
            }
        }

        for (int i = 0; i < hacks.size(); i++) {

            Module currentHack = hacks.get(i);
            // Selected Tab Background & String

            switch (mode) {
                case "ambien old": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getAmbienOldBlueColor() : 0);
                    Client.main().fontMgr().raleWay20.drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : new Color(181, 181, 181).getRGB());
                    break;
                }
                case "ambien newest": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getAmbienNewestBlueColor() : 0);
                    Client.main().fontMgr().font("BebasNeue", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : Colors.main().getAmbienNewestLightGreyColor());
                    break;
                }
                case "vortex": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getVortexRedColor() : 0);
                    Client.main().fontMgr().raleWay20.drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : new Color(181, 181, 181).getRGB());
                    break;
                }
                case "suicide": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Rainbow.rainbow(1, 1f).getRGB() : 0);
                    Client.main().fontMgr().raleWay20.drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : new Color(181, 181, 181).getRGB());
                    break;
                }
                case "apinity": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getApinityBlueColor() : 0);
                    Client.main().fontMgr().raleWay20.drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : new Color(181, 181, 181).getRGB());
                    break;
                }
                case "huzuni": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getHuzuniBlueColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? new Color(255, 255, 255).getRGB() : new Color(181, 181, 181).getRGB());
                    break;
                }
                case "saint": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getSaintDarkTealColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? Colors.main().getSaintOrangeColor() : new Color(255, 255, 255).getRGB());
                    break;
                }
                case "icarus old": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getIcarusOldOrangeColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? Colors.main().getSaintOrangeColor() : new Color(255, 255, 255).getRGB());
                    break;
                }
                case "icarus new": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getIcarusNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.BOLD).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? Colors.main().getSaintOrangeColor() : new Color(255, 255, 255).getRGB());
                    break;
                }
                case "ambien new": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getAmbienNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? Colors.main().getSaintOrangeColor() : new Color(255, 255, 255).getRGB());
                    break;
                }
                case "hero": {
                    RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 11, i == TabGUI.selectedItem ? Colors.main().getHeroGreenColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(currentHack.name(), x + 1, y + gui.tabHeight * i + 1, currentHack.state() ? Colors.main().getHeroGreenColor() : new Color(255, 255, 255).getRGB());
                    break;
                }
            }
        }
    }
}
