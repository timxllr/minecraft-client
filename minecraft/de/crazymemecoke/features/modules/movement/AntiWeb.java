package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.utils.events.eventapi.EventTarget;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AntiWeb extends Module {

    public AntiWeb() {
        super("AntiWeb", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @EventTarget
    public void onUpdate() {
        if (getState()) {
            mc.thePlayer.isInWeb = false;
        }
    }

}
