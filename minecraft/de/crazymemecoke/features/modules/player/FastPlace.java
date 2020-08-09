package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class FastPlace extends Module {
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_NONE, Category.PLAYER, -1);
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