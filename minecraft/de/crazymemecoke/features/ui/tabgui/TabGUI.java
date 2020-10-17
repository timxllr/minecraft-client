package de.crazymemecoke.features.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.RenderHelper;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;

public class TabGUI {
    public static int selectedTab = 0;
    public static int selectedItem = 0;
    public static boolean mainMenu = true;
    public static ArrayList<Tab> tabsList;
    private final Minecraft mc;
    public int guiWidth = 88;
    public int guiHeight = 0;
    public int tabHeight = 12;

    public TabGUI(Minecraft minecraft) {
        mc = minecraft;
        tabsList = new ArrayList<>();
        for (Category category : Category.values()) {
            String capitalizedName = category.name().substring(0, 1).toUpperCase() + category.name().substring(1).toLowerCase();
            final Tab tab = new Tab(this, capitalizedName);

            for (Module module : Client.main().modMgr().getModules()) {
                if (module.getCategory() == category) {
                    tab.hacks.add(module);
                }
            }

            if (!tab.tabName.equalsIgnoreCase("Gui")) {
                tabsList.add(tab);
            }
        }

        guiHeight = (tabHeight + tabsList.size() * tabHeight);
    }

    public static void parseKeyUp() {
        if (mainMenu) {
            selectedTab -= 1;
            if (selectedTab < 0) {
                selectedTab = tabsList.size() - 1;
            }
        } else {
            selectedItem -= 1;
            if (selectedItem < 0) {
                selectedItem = (tabsList.get(selectedTab)).hacks.size() - 1;
            }
        }
    }

    public static void parseKeyDown() {
        if (mainMenu) {
            selectedTab += 1;
            if (selectedTab > tabsList.size() - 1) {
                selectedTab = 0;
            }
        } else {
            selectedItem += 1;
            if (selectedItem > (tabsList.get(selectedTab)).hacks.size() - 1) {
                selectedItem = 0;
            }
        }
    }

    public static void parseKeyLeft() {
        if (!mainMenu) {
            mainMenu = true;
        }
    }

    public static void parseKeyRight() {
        if (mainMenu) {
            mainMenu = false;
            selectedItem = 0;
        }
    }

    public static void parseKeyToggle() {
        if (!mainMenu) {
            int sel = selectedItem;
            (tabsList.get(selectedTab)).hacks.get(sel).toggleModule();
        }
    }

    public void drawGui(int posX, int posY, int width) {
        int x = posX;
        int y = posY;
        guiWidth = width;
        // Background
        String mode = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();
        switch (mode) {
            case "ambien old": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
                break;
            }
            case "vortex": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
                break;
            }
            case "suicide": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getSuicideBlueGreyColor());
                break;
            }
            case "apinity": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getApinityGreyColor());
                break;
            }
            case "huzuni": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getHuzuniGreyColor());
                break;
            }
            case "saint": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getSaintDarkBlueColor());
                break;
            }
            case "icarus old": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getIcarusOldGreyColor());
                break;
            }
            case "icarus new": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getIcarusNewGreyColor());
                break;
            }
            case "ambien new": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getAmbienNewDarkGreyColor());
                break;
            }
            case "hero": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.main().getHeroGreyColor());
                break;
            }
        }

        int yOff = posY;
        for (int i = 0; i < tabsList.size(); i++) {

            switch (mode) {
                case "ambien old": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getAmbienOldBlueColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "vortex": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getVortexRedColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "suicide": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Rainbow.rainbow(1, 1f).getRGB() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "apinity": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getApinityBlueColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "huzuni": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getHuzuniBlueColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "saint": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getSaintDarkTealColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "icarus old": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getIcarusOldOrangeColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "icarus new": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getIcarusNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.BOLD).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff + 1, -1);
                    break;
                }
                case "ambien new": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getAmbienNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.PLAIN).drawCenteredString(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 30, yOff + 1, -1);
                    break;
                }
                case "hero": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.main().getHeroGreenColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x, yOff + 1, -1);
                    break;
                }
            }


            if ((i == selectedTab) && (!mainMenu)) {
                (tabsList.get(i)).drawTabMenu(x + guiWidth + 2, yOff - 2);
            }
            yOff += tabHeight;
        }
    }
}
