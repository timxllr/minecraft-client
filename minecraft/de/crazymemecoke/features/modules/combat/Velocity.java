package de.crazymemecoke.features.modules.combat;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class Velocity extends Module {

	public static boolean Velocity = true;

	public Velocity() {
		super("Velocity", Keyboard.KEY_NONE, Category.COMBAT, -1);
	}

	@Override
	public void onUpdate() {
		if (this.state()) {
			Velocity = true;
		} else {
			Velocity = false;

		}
	}
}