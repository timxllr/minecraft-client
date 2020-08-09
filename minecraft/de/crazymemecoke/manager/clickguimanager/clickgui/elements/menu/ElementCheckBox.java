package de.crazymemecoke.manager.clickguimanager.clickgui.elements.menu;

import java.awt.Color;

import de.crazymemecoke.manager.clickguimanager.clickgui.elements.Element;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.ModuleButton;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.ColorUtil;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.FontUtil;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.utils.render.RenderUtils;

/**
 * Made by HeroCode
 * it's free to use
 * but you have to credit me
 *
 * @author HeroCode
 */
public class ElementCheckBox extends Element {
    /*
     * Konstrukor
     */
    public ElementCheckBox(ModuleButton iparent, Setting iset) {
        parent = iparent;
        set = iset;
        super.setup();
    }

    /*
     * Rendern des Elements
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 200).getRGB();

        /*
         * Die Box und Umrandung rendern
         */
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

        /*
         * Titel und Checkbox rendern.
         */
        FontUtil.drawString(setstrg, (int) (x + 13), (int) (y + FontUtil.getFontHeight() / 2 - 0.5), 0xffffffff);
        RenderUtils.drawRect(x + 1, y + 2, x + 12, y + 13, set.getBool() ? color : 0xff000000);
        if (isCheckHovered(mouseX, mouseY))
            RenderUtils.drawRect(x + 1, y + 2, x + 12, y + 13, 0x55111111);
    }

    /*
     * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
     * sollen alle anderen Versuche der Interaktion abgebrochen werden?
     */
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isCheckHovered(mouseX, mouseY)) {
            set.setBool(!set.getBool());
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /*
     * Einfacher HoverCheck, ben�tigt damit die Value ge�ndert werden kann
     */
    public boolean isCheckHovered(int mouseX, int mouseY) {
        return mouseX >= x + 1 && mouseX <= x + 12 && mouseY >= y + 2 && mouseY <= y + 13;
    }
}
