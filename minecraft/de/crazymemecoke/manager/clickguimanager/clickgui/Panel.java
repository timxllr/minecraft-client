package de.crazymemecoke.manager.clickguimanager.clickgui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.ModuleButton;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.ColorUtil;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.FontUtil;
import de.crazymemecoke.utils.render.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class Panel {
    public String title;
    public double x;
    public double y;
    private double x2;
    private double y2;
    public double width;
    public double height;
    public boolean dragging;
    public boolean extended;
    public boolean visible;
    public ArrayList<ModuleButton> Elements = new ArrayList<>();
    public ClickGUI clickgui;

    public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended,
                 ClickGUI parent) {
        this.title = ititle;
        this.x = ix;
        this.y = iy;
        this.width = iwidth;
        this.height = iheight;
        this.extended = iextended;
        this.dragging = false;
        this.visible = true;
        this.clickgui = parent;
        setup();
    }

    public void setup() {
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (!this.visible)
            return;

        if (this.dragging) {
            x = x2 + mouseX;
            y = y2 + mouseY;
        }

        Color temp = ColorUtil.getClickGUIColor().darker();
        int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();

        // Closed Panel
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff121212);

        if (Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("New")) {
            RenderUtils.drawRect(x - 2, y, x, y + height, outlineColor);
            FontUtil.drawStringWithShadow(title, x + 2, y + height / 2 - FontUtil.getFontHeight() / 2, 0xffefefef);
        } else if (Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("JellyLike")) {
            RenderUtils.drawRect(x + 4, y + 2, x + 4.3, y + height - 2, 0xffaaaaaa);
            RenderUtils.drawRect(x - 4 + width, y + 2, x - 4.3 + width, y + height - 2, 0xffaaaaaa);
            FontUtil.drawTotalCenteredStringWithShadow(title, x + width / 2, y + height / 2 + 1, 0xffefefef);
        } else if (Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("Ambien")) {
            RenderUtils.drawRect(x, y, x + width, y + height, new Color(92, 49, 135).getRGB());
            FontUtil.drawAmbienStringWithShadow(title, x + 2, y + height / 2 - FontUtil.getFontHeight() / 2 - 2, 0xffefefef);
        }

        if (this.extended && !Elements.isEmpty()) {
            double startY = y + height;
            int epanelcolor = Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("New") ? 0xff232323 : Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("JellyLike") ? 0xbb151515 : 0;

            for (ModuleButton et : Elements) {
                if (Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("New")) {
                    RenderUtils.drawRect(x - 2, startY, x + width, startY + et.height + 1, outlineColor);
                }
                if (Client.instance().getSetmgr().getSettingByName("Design", Client.instance().modManager().getByName("ClickGUI")).getMode().equalsIgnoreCase("Ambien")) {
                    RenderUtils.drawRect(x, startY, x + width, startY + et.height + 1, new Color(36, 35, 36).getRGB());
                }

                RenderUtils.drawRect(x, startY, x + width, startY + et.height + 1, epanelcolor);
                et.x = x + 2;
                et.y = startY;
                et.width = width - 4;
                et.drawScreen(mouseX, mouseY, partialTicks);
                startY += et.height + 1;
            }

            RenderUtils.drawRect(x, startY + 1, x + width, startY + 1, epanelcolor);

        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!this.visible) {
            return false;
        }
        if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
            x2 = this.x - mouseX;
            y2 = this.y - mouseY;
            dragging = true;
            return true;
        } else if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
            extended = !extended;
            return true;
        } else if (extended) {
            for (ModuleButton et : Elements) {
                if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (!this.visible) {
            return;
        }
        if (state == 0) {
            this.dragging = false;
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
