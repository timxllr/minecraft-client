package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class NoBob extends Module {
    public NoBob() {
        super("NoBob", Keyboard.KEY_NONE, Category.RENDER, -1);
    }

    @Override
    public void onUpdate() {
        if (state()) {
            mc.thePlayer.distanceWalkedModified = 0.0f;
        }
    }
}
