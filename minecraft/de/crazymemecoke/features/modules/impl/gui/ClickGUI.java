package de.crazymemecoke.features.modules.impl.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.Panel;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;

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