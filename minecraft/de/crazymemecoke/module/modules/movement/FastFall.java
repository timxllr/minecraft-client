package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class FastFall extends Module {

	public FastFall() {
		super("FastFall", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());
	}

	@Override
	public void onUpdate() {
		if (getState()) {
			Wrapper.mc.thePlayer.motionY = -30;
		}
	}
}