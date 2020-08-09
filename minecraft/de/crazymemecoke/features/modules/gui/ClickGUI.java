package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI, -1);
    }

    @Override
    public void setup() {
        ArrayList<String> theme = new ArrayList<>();

        theme.add("JellyLike");
        theme.add("New");
        theme.add("Ambien");

        Client.instance().getSetmgr().rSetting(new Setting("Design", this, "New", theme));
        Client.instance().getSetmgr().rSetting(new Setting("Sound", this, false));
        Client.instance().getSetmgr().rSetting(new Setting("Blur", this, true));
        Client.instance().getSetmgr().rSetting(new Setting("Red", this, 255, 0, 255, true));
        Client.instance().getSetmgr().rSetting(new Setting("Green", this, 26, 0, 255, true));
        Client.instance().getSetmgr().rSetting(new Setting("Blue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Client.instance().getClickGui());
        super.onDisable();
    }
}