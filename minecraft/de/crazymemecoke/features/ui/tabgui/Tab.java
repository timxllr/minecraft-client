package de.crazymemecoke.features.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.RenderHelper;
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
            if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(hacks.get(i).getName() + 4) > maxWidth) {
                maxWidth = (int) (Minecraft.getMinecraft().fontRendererObj.getStringWidth(hacks.get(i).getName()) + 7.5F);
            }
        }
        menuWidth = maxWidth;
        menuHeight = (hacks.size() * gui.tabHeight - 1);
    }

    public void drawTabMenu(int x, int y) {
        countMenuSize();
        x += 2;
        y += 2;
        RenderHelper.drawRect(x - 1, y - 1, x + menuWidth, y + menuHeight - 1, -1879048192);
        for (int i = 0; i < hacks.size(); i++) {

            Module currentHack = hacks.get(i);
            // Selected Tab Rect
            RenderHelper.drawRect(x - 1, y + gui.tabHeight * i - 1, x + menuWidth, y + gui.tabHeight * i + 10, i == TabGUI.selectedItem ? new Color(255, 140, 59, 120) : new Color(0, 0, 0, 0));
            // Selected Tab String
            Client.instance().getFontManager().comfortaa20.drawString(currentHack.getName(), x, y + gui.tabHeight * i + 1, currentHack.getState() ? -1 : -1);
        }
    }
}
