package de.crazymemecoke.features.modules.impl.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;

@ModuleInfo(name = "AutoWalk", category = Category.PLAYER, description = "Automatically walks for you")
public class AutoWalk extends Module {
    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindForward.pressed = false;
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventUpdate) {
            mc.gameSettings.keyBindForward.pressed = true;
        }
    }
}
