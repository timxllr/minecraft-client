package de.crazymemecoke.features.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;

public class OutlineESP extends Module {

	public OutlineESP() {
		super("OutlineESP", Keyboard.KEY_NONE, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());
	}

}
