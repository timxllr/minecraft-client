package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;

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
