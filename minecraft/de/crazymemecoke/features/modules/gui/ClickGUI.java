package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.Panel;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;

import java.util.ArrayList;

@ModuleInfo(name = "ClickGUI", category = Category.GUI, description = "Shows you a visual interface with all modules and settings", bind = 54)
public class ClickGUI extends Module {

    public Setting design = new Setting("Design", this, "Caesium", new String[] {"Caesium"});
    public Setting sound = new Setting("Sound", this, false);
    public Setting blur = new Setting("Blur", this, true);

    @Override
    public void onToggle() {
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new Panel(design.getCurrentMode(), 22));
        Client.main().modMgr().getModule(ClickGUI.class).setState(false);
        System.out.println(bind());
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEvent(Event event) {
    }
}