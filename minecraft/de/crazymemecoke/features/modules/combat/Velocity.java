package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.manager.eventmanager.Event;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class Velocity extends Module {

	public Velocity() {
		super("Velocity", Keyboard.KEY_NONE, Category.COMBAT);
	}


	@Override
	public void onEvent(Event event) {
	}
}