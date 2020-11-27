package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

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
