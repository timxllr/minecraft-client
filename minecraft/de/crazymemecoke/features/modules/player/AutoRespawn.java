package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Keyboard.KEY_NONE, Category.PLAYER, -1);
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            if (!(mc.thePlayer.isEntityAlive())) {
                mc.thePlayer.respawnPlayer();
            }
        }
    }
}
