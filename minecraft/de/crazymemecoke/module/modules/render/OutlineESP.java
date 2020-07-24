package de.crazymemecoke.module.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;

public class OutlineESP extends Module {

	public OutlineESP() {
		super("OutlineESP", Keyboard.KEY_NONE, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());
	}

}
