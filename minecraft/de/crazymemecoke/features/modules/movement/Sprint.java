package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT, -1);

	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			Wrapper.mc.thePlayer.setSprinting(true);
		}
	}
}