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

    public void drawGui(int posY, int posX, int width) {
        int x = posY;
        int y = posX;
        guiWidth = width;
        // Background
        RenderHelper.drawRect(posY - 1, posX - 1, posY + guiWidth, posX + guiHeight - 13, new Color(0, 0, 0, 150).getRGB());

        int yOff = posX;
        for (int i = 0; i < tabsList.size(); i++) {
            // Selected Category Background
            RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11,
                    i == selectedTab ? -55511 : 0);
            // Selected Category String
            Client.instance().getFontManager().comfortaa20.drawString((tabsList.get(i)).tabName, x, yOff + 1,
                    -3);
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
