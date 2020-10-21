package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", Keyboard.KEY_NONE, Category.COMBAT, -1);

        SettingsManager s = new SettingsManager();

        s.newSetting(new Setting("Ticks Existed", this, 30, 0, 100, true));
        s.newSetting(new Setting("Is Alive?", this, true));
    }

    @Override
    public void onEvent(Event event) {

    }
}
