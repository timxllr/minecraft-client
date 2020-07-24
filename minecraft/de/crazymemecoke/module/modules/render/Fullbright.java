package de.crazymemecoke.module.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.Wrapper;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_NONE, Category.RENDER, Rainbow.rainbowNormal(1, 1).hashCode());
	}

	public void onUpdate() {
		if (this.getState()) {
			Wrapper.mc.gameSettings.gammaSetting = 100f;
		} else {
			Wrapper.mc.gameSettings.gammaSetting = 1f;
		}
	}
}