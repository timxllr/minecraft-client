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
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI, Rainbow.rainbow(1, 1).hashCode());
    }

    @Override
    public void setup() {
        ArrayList<String> theme = new ArrayList<>();

        theme.add("JellyLike");
        theme.add("New");
        theme.add("Experimental");


        Client.getInstance().getSetmgr().rSetting(new Setting("Design", this, "New", theme));
        Client.getInstance().getSetmgr().rSetting(new Setting("Sound", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Blur", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Red", this, 255, 0, 255, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Green", this, 26, 0, 255, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Blue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Client.getInstance().getClickGui());
        super.onDisable();
    }
}