package de.crazymemecoke.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.ui.RenderHelper;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class TabGUI {
    public int guiWidth = 88;
    public int guiHeight = 0;
    public int tabHeight = 12;
    public static int selectedTab = 0;
    public static int selectedItem = 0;
    public static boolean mainMenu = true;
    public static ArrayList tabsList;
    private final Minecraft mc;

    public TabGUI(Minecraft minecraft) {
        this.mc = minecraft;
        tabsList = new ArrayList();
        Tab tabCombat = new Tab(this, "Combat");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getCategory() == Category.COMBAT) {
                tabCombat.hacks.add(module);
            }
        }

        Tab tabRender = new Tab(this, "Render");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getCategory() == Category.RENDER) {
                tabRender.hacks.add(module);
            }
        }

        Tab tabMovement = new Tab(this, "Movement");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getCategory() == Category.MOVEMENT) {
                tabMovement.hacks.add(module);
            }
        }

        Tab tabPlayer = new Tab(this, "Player");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getCategory() == Category.PLAYER) {
                tabPlayer.hacks.add(module);
            }
        }

        Tab tabWorld = new Tab(this, "World");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
            if (module.getCategory() == Category.WORLD) {
                tabWorld.hacks.add(module);
            }
        }

        Tab tabExploits = new Tab(this, "Exploits");
        for (Module module : Client.getInstance().getModuleManager().getModules()) {
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

        this.guiHeight = (this.tabHeight + tabsList.size() * this.tabHeight);
    }

    public void drawGui(int posY, int posX, int width) {
        int x = posY;
        int y = posX;
        this.guiWidth = width;
        RenderHelper.drawRect(posY - 1, posX - 1, posY + this.guiWidth, posX + this.guiHeight - 13, -1879048192);
        RenderHelper.drawRect(posY - 2, posX - 1, posY + this.guiWidth + 1, posX, -16777216);
        RenderHelper.drawRect(posY - 2, posX - 1, posY - 1, posX + this.guiHeight - 12, Rainbow.rainbowNormal(1, 1).hashCode());
        RenderHelper.drawRect(posY - 2, posX + this.guiHeight - 13, posY + this.guiWidth + 1,
                posX + this.guiHeight - 12, -16777216);
        RenderHelper.drawRect(posY + this.guiWidth, posX - 1, posY + this.guiWidth + 1, posX + this.guiHeight - 12,
                -16777216);
        int yOff = posX + 1;
        for (int i = 0; i < tabsList.size(); i++) {
            RenderHelper.drawRect(x - 1, yOff - 1, x + this.guiWidth, y + this.tabHeight * i + 11,
                    i == selectedTab ? -55511 : 0);
            Client.getInstance().getFontManager().comfortaa20.drawString(((Tab) tabsList.get(i)).tabName, x, yOff,
                    -3);
            if ((i == selectedTab) && (!mainMenu)) {
                ((Tab) tabsList.get(i)).drawTabMenu(x + this.guiWidth + 2, yOff - 2);
            }
            yOff += this.tabHeight;
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
                selectedItem = ((Tab) tabsList.get(selectedTab)).hacks.size() - 1;
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
            if (selectedItem > ((Tab) tabsList.get(selectedTab)).hacks.size() - 1) {
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
            ((Tab) tabsList.get(selectedTab)).hacks.get(sel).toggleModule();
        }
    }
}
