package de.crazymemecoke.module.modules.combat;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;

public class Velocity extends Module {

	public static boolean Velocity = true;

	public Velocity() {
		super("Velocity", Keyboard.KEY_NONE, Category.COMBAT, -1);
	}

	@Override
	public void onUpdate() {
		if (this.getState()) {
			Velocity = true;
		} else {
			Velocity = false;

		}
	}
}