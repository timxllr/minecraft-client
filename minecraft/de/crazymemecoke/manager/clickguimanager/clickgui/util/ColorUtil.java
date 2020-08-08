package de.crazymemecoke.manager.clickguimanager.clickgui.util;

import java.awt.Color;

import de.crazymemecoke.Client;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class ColorUtil {

    public static Color getClickGUIColor() {
        return new Color((int) Client.getInstance().getSetmgr().getSettingByName("Red", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble(),
                (int) Client.getInstance().getSetmgr().getSettingByName("Green", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble(),
                (int) Client.getInstance().getSetmgr().getSettingByName("Blue", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValDouble());
    }
}
