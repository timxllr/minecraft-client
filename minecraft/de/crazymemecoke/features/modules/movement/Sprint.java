package de.crazymemecoke.features.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT, -1);

	}

	public void onUpdate() {
		if (state()) {
			if (!(Wrapper.mc.thePlayer.isCollidedHorizontally) && Wrapper.mc.thePlayer.moveForward > 0.0f) {
				Wrapper.mc.thePlayer.setSprinting(true);
			} else {
				Wrapper.mc.thePlayer.setSprinting(false);
			}
		}
	}
}