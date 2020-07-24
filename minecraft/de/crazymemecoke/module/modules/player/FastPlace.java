package de.crazymemecoke.module.modules.player;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class FastPlace extends Module {
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_NONE, Category.PLAYER, Rainbow.rainbowNormal(1, 1).hashCode());
	}

	public void onDisable() {
		Wrapper.mc.rightClickDelayTimer = 6;
	}

	public void onUpdate() {
		if (this.getState()) {
			Wrapper.mc.rightClickDelayTimer = 0;
		}
	}
}