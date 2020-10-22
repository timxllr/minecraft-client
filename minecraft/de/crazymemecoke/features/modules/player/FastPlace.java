package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import org.lwjgl.input.Keyboard;

public class FastPlace extends Module {
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_NONE, Category.PLAYER, -1);
	}

	public void onDisable() {
		Wrapper.mc.rightClickDelayTimer = 6;
	}

	@Override
	public void onEvent(Event event) {
		Wrapper.mc.rightClickDelayTimer = 0;
	}
}