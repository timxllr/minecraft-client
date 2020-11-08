package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class NoBob extends Module {
    public NoBob() {
        super("NoBob", Keyboard.KEY_NONE, Category.RENDER);
    }

    @Override
    public void onEvent(Event event) {
    }
}
