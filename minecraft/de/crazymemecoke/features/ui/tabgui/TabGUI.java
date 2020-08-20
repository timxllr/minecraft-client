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
        for (Category category : Category.values()) {
            String capitalizedName = category.name().substring(0, 1).toUpperCase() + category.name().substring(1).toLowerCase();
            final Tab tab = new Tab(this, capitalizedName);

            for (Module module : Client.instance().modManager().getModules()) {
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

    public void drawGui(int posX, int posY, int width) {
        int x = posX;
        int y = posY;
        guiWidth = width;
        // Background
        String mode = Client.instance().setMgr().getSettingByName("Design", Client.instance().modManager().getByName("HUD")).getMode();
        switch (mode) {
            case "ambien": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
                break;
            }
            case "vortex": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, new Color(0, 0, 0).getRGB());
                break;
            }
            case "suicide": {
                RenderHelper.drawRect(posX - 1, posY - 1, posX + guiWidth, posY + guiHeight - 13, Client.instance().getSuicideBlueGreyColor());
                break;
            }
        }

        int yOff = posY;
        for (int i = 0; i < tabsList.size(); i++) {

            switch (mode) {
                case "ambien": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.instance().getAmbienBlueColor() : 0);
                    Client.instance().getFontManager().getFont("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "vortex": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Client.instance().getVortexRedColor() : 0);
                    Client.instance().getFontManager().getFont("Raleway Light", 20, Font.PLAIN).drawStringWithShadow((tabsList.get(i)).tabName, x + 1, yOff, -1);
                    break;
                }
                case "suicide": {
                    RenderHelper.drawRect(x - 1, yOff - 1, x + guiWidth, y + tabHeight * i + 11, i == selectedTab ? Rainbow.rainbow(1, 1f).getRGB() : 0);
                    Client.instance().getFontManager().getFont("Raleway Light", 20, Font.PLAIN).drawStringWithShadow(StringUtils.capitalize((tabsList.get(i)).tabName.toLowerCase()), x + 1, yOff, -1);
                    break;
                }
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
