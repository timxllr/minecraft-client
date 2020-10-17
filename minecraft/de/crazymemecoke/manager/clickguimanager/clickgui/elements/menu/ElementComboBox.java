package de.crazymemecoke.manager.clickguimanager.clickgui.elements.menu;

import java.awt.Color;

import de.crazymemecoke.manager.clickguimanager.clickgui.elements.Element;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.ModuleButton;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.ColorUtil;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.FontUtil;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

/**
 * Made by HeroCode
 * it's free to use
 * but you have to credit me
 *
 * @author HeroCode
 */
public class ElementComboBox extends Element {
    /*
     * Konstrukor
     */
    public ElementComboBox(ModuleButton iparent, Setting iset) {
        parent = iparent;
        set = iset;
        super.setup();
    }

    /*
     * Rendern des Elements
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();

        /*
         * Die Box und Umrandung rendern
         */
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

        FontUtil.drawTotalCenteredString(setstrg, (int) (x + width / 2), (int) (y + 15 / 2), 0xffffffff);
        int clr1 = color;
        int clr2 = temp.getRGB();

        RenderUtils.drawRect(x, y + 14, x + width, y + 15, 0x77000000);
        if (comboextended) {
            RenderUtils.drawRect(x, y + 15, x + width, y + height, 0xaa121212);
            double ay = y + 15;
            for (String sld : set.getOptions()) {
                String elementtitle = sld.substring(0, 1).toUpperCase() + sld.substring(1, sld.length());
                FontUtil.drawCenteredString(elementtitle, (int) (x + width / 2), (int) ay + 2, 0xffffffff);

                /*
                 * Ist das Element ausgew�hlt, wenn ja dann markiere
                 * das Element in der ComboBox
                 */
                if (sld.equalsIgnoreCase(set.getMode())) {
                    RenderUtils.drawRect(x, ay, x + 1.5, ay + FontUtil.getFontHeight() + 2, clr1);
                }
                /*
                 * Wie bei mouseClicked 'is hovered', wenn ja dann markiere
                 * das Element in der ComboBox
                 */
                if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY < ay + FontUtil.getFontHeight() + 2) {
                    RenderUtils.drawRect(x + width - 1.2, ay, x + width, ay + FontUtil.getFontHeight() + 2, clr2);
                }
                ay += FontUtil.getFontHeight() + 2;
            }
        }
    }

    /*
     * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
     * sollen alle anderen Versuche der Interaktion abgebrochen werden?
     */
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (isButtonHovered(mouseX, mouseY)) {
                comboextended = !comboextended;
                return true;
            }

            /*
             * Also wenn die Box ausgefahren ist, dann wird f�r jede m�gliche Options
             * �berpr�ft, ob die Maus auf diese zeigt, wenn ja dann global jeder weitere
             * call an mouseClicked gestoppt und die Values werden aktualisiert
             */
            if (!comboextended) return false;
            double ay = y + 15;
            for (String slcd : set.getOptions()) {
                if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY <= ay + FontUtil.getFontHeight() + 2) {
                    if (set.getBool())
                        Minecraft.getMinecraft().thePlayer.playSound("tile.piston.in", 20.0F, 20.0F);

                    if (clickgui != null && Client.main().setMgr() != null)
                        set.setMode(slcd.toLowerCase());
                    return true;
                }
                ay += FontUtil.getFontHeight() + 2;
            }
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /*
     * Einfacher HoverCheck, ben�tigt damit die Combobox ge�ffnet und geschlossen werden kann
     */
    public boolean isButtonHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 15;
    }
}
