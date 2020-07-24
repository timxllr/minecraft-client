package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import org.lwjgl.input.Keyboard;

public class AutoClimb extends Module {
    public AutoClimb() {
        super("AutoClimb", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            if (mc.thePlayer.isOnLadder()) {
                mc.thePlayer.motionY += 0.1D;
            }
        }
    }
}
