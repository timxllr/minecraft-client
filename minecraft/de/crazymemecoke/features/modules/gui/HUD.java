package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class HUD extends Module {

    public HUD() {
        super("HUD", Keyboard.KEY_NONE, Category.GUI, Rainbow.rainbow(1, 1).hashCode());

        Client.getInstance().getSetmgr().rSetting(new Setting("Hotbar", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("ArrayList", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("TabGUI", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Watermark", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Notifications", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Target HUD", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Developer Mode", this, false));
    }

}
