package de.Hero.clickgui.util;

import java.awt.Color;

import de.crazymemecoke.Client;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class ColorUtil {

    public static Color getClickGUIColor() {
        return new Color((int) Client.getInstance().getSetmgr().getSettingByName("GuiRed", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble(),
                (int) Client.getInstance().getSetmgr().getSettingByName("GuiGreen", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble(),
                (int) Client.getInstance().getSetmgr().getSettingByName("GuiBlue", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble());
    }
}
