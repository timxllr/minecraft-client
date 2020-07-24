package de.crazymemecoke.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
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
