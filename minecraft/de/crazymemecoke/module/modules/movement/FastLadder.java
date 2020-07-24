package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class FastLadder extends Module {

	public FastLadder() {
		super("FastLadder", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());
	}

	@Override
	public void onUpdate() {
		if (getState()) {
			if ((mc.thePlayer.isOnLadder()) && (mc.thePlayer.isCollidedHorizontally)) {
				mc.thePlayer.motionY = 0.28D;
			}
		}
	}
}