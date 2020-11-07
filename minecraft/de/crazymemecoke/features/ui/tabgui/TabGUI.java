package de.crazymemecoke.features.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import de.crazymemecoke.utils.render.Colors;
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
                if (module.category() == category) {
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
            (tabsList.get(selectedTab)).hacks.get(sel).toggle();
        }
    }

    public void drawGui(int posX, int posY, int width) {
        int x = posX;
        int y = posY;
        guiWidth = width;
        // Background
        String mode = Client.main().setMgr().settingByName("Design", Client.main().modMgr().getByName("HUD")).getMode();
        switch (mode) {
            case "ambien old":
            case "vortex": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
                break;
            }
            case "ambien newest": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getAmbienNewestGreyMainColor());
                break;
            }
            case "suicide": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getSuicideBlueGreyColor());
                break;
            }
            case "apinity": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getApinityGreyColor());
                break;
            }
            case "huzuni": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getHuzuniGreyColor());
                break;
            }
            case "saint": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getSaintDarkBlueColor());
                break;
            }
            case "icarus old": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getIcarusOldGreyColor());
                break;
            }
            case "icarus new": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getIcarusNewGreyColor());
                break;
            }
            case "ambien new": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getAmbienNewDarkGreyColor());
                break;
            }
            case "hero": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Colors.main().getHeroGreyColor());
                break;
            }
            case "koks": {
                RenderUtils.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0,0,0,220).getRGB());
                break;
            }
        }

        int yOff = posY;
        for (int i = 0; i < tabsList.size(); i++) {

            switch (mode) {
                case "ambien old": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getAmbienOldBlueColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "ambien newest": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getAmbienNewestBlueColor() : 0);
                    Client.main().fontMgr().font("BebasNeue", 20, Font.PLAIN).drawCenteredString((tabsList.get(i)).tabName.toUpperCase(), x + 40, yOff + 2, -1);
                    break;
                }
                case "vortex": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getVortexRedColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "suicide": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Rainbow.rainbow(1, 1f).getRGB() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "apinity": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getApinityBlueColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "huzuni": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getHuzuniBlueColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "saint": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getSaintDarkTealColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "icarus old": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getIcarusOldOrangeColor() : 0);
                    Client.main().fontMgr().font("Arial", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
                case "icarus new": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getIcarusNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.BOLD).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff + 1, -1);
                    break;
                }
                case "ambien new": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getAmbienNewBlueColor() : 0);
                    Client.main().fontMgr().font("BigNoodleTitling", 20, Font.PLAIN).drawCenteredString(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 30, yOff + 1, -1);
                    break;
                }
                case "hero": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getHeroGreenColor() : 0);
                    Client.main().fontMgr().font("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x, yOff + 1, -1);
                    break;
                }
                case "koks": {
                    RenderUtils.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Colors.main().getKoksGreenColor() : 0);
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
