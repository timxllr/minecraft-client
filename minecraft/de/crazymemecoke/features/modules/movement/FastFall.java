package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import org.lwjgl.input.Keyboard;

public class FastFall extends Module {

	public FastFall() {
		super("FastFall", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
	}

	@Override
	public void onEvent(Event event) {
		Wrapper.mc.thePlayer.motionY = -30;
	}
}