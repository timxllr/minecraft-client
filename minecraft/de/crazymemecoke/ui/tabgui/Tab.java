package de.crazymemecoke.ui.tabgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.ui.RenderHelper;
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
        this.tabName = TabName;
        this.gui = GUI;
        this.hacks = new ArrayList<Module>();
    }

    public void countMenuSize() {
        int maxWidth = 0;
        for (int i = 0; i < this.hacks.size(); i++) {
            if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.hacks.get(i).getName() + 4) > maxWidth) {
                maxWidth = (int) (Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.hacks.get(i).getName()) + 7.5F);
            }
        }
        this.menuWidth = maxWidth;
        this.menuHeight = (this.hacks.size() * this.gui.tabHeight - 1);
    }

    public void drawTabMenu(int x, int y) {
        countMenuSize();
        x += 2;
        y += 2;
        RenderHelper.drawRect(x - 1, y - 1, x + this.menuWidth - 2, y + this.menuHeight - 1, -1879048192);
        RenderHelper.drawRect(x - 2, y - 2, x - 1, y + this.menuHeight, -16777216);
        RenderHelper.drawRect(x - 2, y + this.menuHeight - 1, x + this.menuWidth - 1, y + this.menuHeight, -16777216);
        RenderHelper.drawRect(x - 2, y - 2, x + this.menuWidth - 1, y - 1, -16777216);
        RenderHelper.drawRect(x + this.menuWidth - 2, y - 2, x + this.menuWidth - 1, y + this.menuHeight, -16777216);
        for (int i = 0; i < this.hacks.size(); i++) {

            Module currentHack = this.hacks.get(i);
            // Selected Tab Rect
            RenderHelper.drawRect(x - 1, y + this.gui.tabHeight * i - 1, x + this.menuWidth - 2, y + this.gui.tabHeight * i + 10, i == TabGUI.selectedItem ? new Color(255, 140, 59, 120) : new Color(0, 0, 0, 0));
            // Selected Tab String
            Client.getInstance().getFontManager().comfortaa20.drawString(currentHack.getName(), x, y + this.gui.tabHeight * i + 1, currentHack.getState() ? Rainbow.rainbow(1, 1).hashCode() : -1);
        }
    }
}
