package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

@ModuleInfo(name = "HighJump", category = Category.MOVEMENT, description = "Lets you jump higher than usual")
public class HighJump extends Module {

    public Setting boost = new Setting("Boost", this,0.5, 0.1, 50, false);

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
        if(event instanceof EventUpdate) {
            if (mc.gameSettings.keyBindJump.pressed && mc.gameSettings.keyBindForward.pressed) {
                mc.thePlayer.motionY = boost.getCurrentValue();
            }
        }
    }
}
