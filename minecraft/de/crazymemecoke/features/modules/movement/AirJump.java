package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.events.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AirJump extends Module {
    public AirJump() {
        super("AirJump", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventUpdate) {
            mc.thePlayer.onGround = true;
        }
    }
}
