package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Keyboard.KEY_NONE, Category.PLAYER, -1);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventUpdate) {
            if (!(mc.thePlayer.isEntityAlive())) {
                mc.thePlayer.respawnPlayer();
            }
        }
    }
}
