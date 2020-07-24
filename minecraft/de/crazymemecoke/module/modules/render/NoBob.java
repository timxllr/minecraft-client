package de.crazymemecoke.module.modules.render;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import org.lwjgl.input.Keyboard;

public class NoBob extends Module {
    public NoBob() {
        super("NoBob", Keyboard.KEY_NONE, Category.RENDER, -1);
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            mc.thePlayer.distanceWalkedModified = 0.0f;
        }
    }
}
