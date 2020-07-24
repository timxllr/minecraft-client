package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import org.lwjgl.input.Keyboard;

public class Step extends Module {

    public Step() {
        super("Step", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    public void onUpdate() {
        if (this.getState()) {
            if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            } else {
                mc.thePlayer.stepHeight = 0.5F;
            }
        }
    }
}
