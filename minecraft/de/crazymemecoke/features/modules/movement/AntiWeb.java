package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "AntiWeb", category = Category.MOVEMENT, description = "Lets you walk inside of webs like they weren't even there")
public class AntiWeb extends Module {

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
            mc.thePlayer.isInWeb = false;
        }
    }
}
