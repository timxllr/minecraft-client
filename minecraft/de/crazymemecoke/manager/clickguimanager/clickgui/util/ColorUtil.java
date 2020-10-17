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
        return new Color((int) Client.main().setMgr().settingByName("Red", Client.main().modMgr().getByName("ClickGUI")).getNum(),
                (int) Client.main().setMgr().settingByName("Green", Client.main().modMgr().getByName("ClickGUI")).getNum(),
                (int) Client.main().setMgr().settingByName("Blue", Client.main().modMgr().getByName("ClickGUI")).getNum());
    }
}
