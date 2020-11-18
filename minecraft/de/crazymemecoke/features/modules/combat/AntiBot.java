package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.settingsmanager.SettingsManager;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", Keyboard.KEY_NONE, Category.COMBAT);

        SettingsManager s = new SettingsManager();

        s.addSetting(new Setting("Ticks Existed", this, 30, 0, 100, true));
        s.addSetting(new Setting("Is Alive?", this, true));
    }

    @Override
    public void onEvent(Event event) {

    }
}
