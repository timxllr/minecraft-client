package de.crazymemecoke.module.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.Wrapper;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());

	}

	public void onUpdate() {
		if (getState()) {
			if (!(Wrapper.mc.thePlayer.isCollidedHorizontally) && Wrapper.mc.thePlayer.moveForward > 0.0f) {
				Wrapper.mc.thePlayer.setSprinting(true);
			} else {
				Wrapper.mc.thePlayer.setSprinting(false);
			}
		}
	}
}