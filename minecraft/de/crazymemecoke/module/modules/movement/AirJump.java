package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import org.lwjgl.input.Keyboard;

public class AirJump extends Module {
    public AirJump() {
        super("AirJump", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            mc.thePlayer.onGround = true;
        }
    }
}
