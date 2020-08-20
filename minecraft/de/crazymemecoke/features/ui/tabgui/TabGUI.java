package de.crazymemecoke.features.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.RenderHelper;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.ArrayList;

public class TabGUI {
    public int guiWidth = 88;
    public int guiHeight = 0;
    public int tabHeight = 12;
    public static int selectedTab = 0;
    public static int selectedItem = 0;
    public static boolean mainMenu = true;
    public static ArrayList<Tab> tabsList;
    private final Minecraft mc;

    public TabGUI(Minecraft minecraft) {
        mc = minecraft;
        tabsList = new ArrayList<>();
        Tab tabCombat = new Tab(this, "Combat");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.COMBAT) {
                tabCombat.hacks.add(module);
            }
        }

        Tab tabRender = new Tab(this, "Render");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.RENDER) {
                tabRender.hacks.add(module);
            }
        }

        Tab tabMovement = new Tab(this, "Movement");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.MOVEMENT) {
                tabMovement.hacks.add(module);
            }
        }

        Tab tabPlayer = new Tab(this, "Player");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.PLAYER) {
                tabPlayer.hacks.add(module);
            }
        }

        Tab tabWorld = new Tab(this, "World");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.WORLD) {
                tabWorld.hacks.add(module);
            }
        }

        Tab tabExploits = new Tab(this, "Exploits");
        for (Module module : Client.instance().modManager().getModules()) {
            if (module.getCategory() == Category.EXPLOITS) {
                tabExploits.hacks.add(module);
            }
        }

        tabsList.add(tabCombat);
        tabsList.add(tabRender);
        tabsList.add(tabMovement);
        tabsList.add(tabPlayer);
        tabsList.add(tabWorld);
        tabsList.add(tabExploits);

        guiHeight = (tabHeight + tabsList.size() * tabHeight);
    }

    public void drawGui(int posX, int posY, int width) {
        int x = posX;
        int y = posY;
        guiWidth = width;
        // Background
        if (Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode().equalsIgnoreCase("Ambien")) {
            RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
        } else if (Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode().equalsIgnoreCase("Vortex")) {
            RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
        }

        int yOff = posY;
        for (int i = 0; i < tabsList.size(); i++) {
            // Selected Category Background & String
            if (Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode().equalsIgnoreCase("Ambien")) {
                RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.instance().getAmbienBlueColor() : 0);
                Client.instance().getFontManager().raleWay20.drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff + 1, -3);
            } else if (Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode().equalsIgnoreCase("Vortex")) {
                RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.instance().getVortexRedColor() : 0);
                Client.instance().getFontManager().raleWay20.drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff + 1, -3);
            }
            if ((i == selectedTab) && (!mainMenu)) {
                (tabsList.get(i)).drawTabMenu(x + guiWidth + 2, yOff - 2);
            }
            yOff += tabHeight;
        }
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
}
