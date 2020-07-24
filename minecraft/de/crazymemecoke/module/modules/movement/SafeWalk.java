package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());
    }

}
