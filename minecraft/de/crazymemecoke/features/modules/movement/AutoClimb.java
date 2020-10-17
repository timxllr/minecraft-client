package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class AutoClimb extends Module {
    public AutoClimb() {
        super("AutoClimb", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @Override
    public void onUpdate() {
        if (state()) {
            if (mc.thePlayer.isOnLadder()) {
                mc.thePlayer.motionY += 0.1D;
            }
        }
    }
}
