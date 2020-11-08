package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class NoRotation extends Module {

    public NoRotation() {
        super("NoRotation", Keyboard.KEY_NONE, Category.PLAYER);
    }


    @Override
    public void onEvent(Event event) {
    }
}
