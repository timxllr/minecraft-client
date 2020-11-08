package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", Keyboard.KEY_NONE, Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {

    }
}
