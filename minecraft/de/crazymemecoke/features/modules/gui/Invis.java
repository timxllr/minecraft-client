package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class Invis extends Module {
    public Invis() {
        super("Invis", Keyboard.KEY_NONE, Category.GUI, -1);
    }

    @Override
    public void onEvent(Event event) {

    }
}
