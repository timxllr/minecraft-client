package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class FastLadder extends Module {

	public FastLadder() {
		super("FastLadder", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
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