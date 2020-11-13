package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.clickgui.Panel;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI);
    }

    @Override
    public void setup() {
        ArrayList<String> theme = new ArrayList<>();

        theme.add("Caesium");

        Client.main().setMgr().newSetting(new Setting("Design", this, "Caesium", theme));
        Client.main().setMgr().newSetting(new Setting("Sound", this, false));
        Client.main().setMgr().newSetting(new Setting("Blur", this, true));
        Client.main().setMgr().newSetting(new Setting("Red", this, 255, 0, 255, true));
        Client.main().setMgr().newSetting(new Setting("Green", this, 26, 0, 255, true));
        Client.main().setMgr().newSetting(new Setting("Blue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        if (!(Client.main().modMgr().getModule(Invis.class)).state()) {
            mc.displayGuiScreen(new Panel(Client.main().setMgr().settingByName("Design", this).getMode(), 22));
            Client.main().modMgr().getModule(ClickGUI.class).setState(false);
        }
    }

    @Override
    public void onEvent(Event event) {

    }
}