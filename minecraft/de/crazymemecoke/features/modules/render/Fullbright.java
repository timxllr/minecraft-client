package de.crazymemecoke.features.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.Wrapper;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_NONE, Category.RENDER, -1);
	}

	public void onUpdate() {
		if (this.getState()) {
			Wrapper.mc.gameSettings.gammaSetting = 100f;
		} else {
			Wrapper.mc.gameSettings.gammaSetting = 1f;
		}
	}
}