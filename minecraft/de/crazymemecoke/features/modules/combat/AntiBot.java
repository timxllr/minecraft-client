package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.settingsmanager.SettingsManager;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "AntiBot",category = Category.COMBAT, description = "You don't attack bots")
public class AntiBot extends Module {
    public AntiBot() {

        SettingsManager s = Client.main().setMgr();

        s.addSetting(new Setting("Ticks Existed", this, 30, 0, 100, true));
        s.addSetting(new Setting("Is Alive?", this, true));
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {

    }
}
