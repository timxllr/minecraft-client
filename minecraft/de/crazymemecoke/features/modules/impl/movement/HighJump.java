package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;

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
