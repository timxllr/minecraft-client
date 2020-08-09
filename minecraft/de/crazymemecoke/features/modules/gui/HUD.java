package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HUD extends Module {

    public HUD() {
        super("HUD", Keyboard.KEY_NONE, Category.GUI, -1);
        ArrayList<String> arrayListRectMode = new ArrayList<>();

        arrayListRectMode.add("Left");
        arrayListRectMode.add("Right");
        arrayListRectMode.add("None");

        Client.instance().getSetmgr().rSetting(new Setting("ArrayList Rect Mode", this, "Left", arrayListRectMode));
        Client.instance().getSetmgr().rSetting(new Setting("Hotbar", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("ArrayList", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("ArrayList Background", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("TabGUI", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("Watermark", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("Notifications", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("Target HUD", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("KeyStrokes", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("Developer Mode", this, false));
    }

}
