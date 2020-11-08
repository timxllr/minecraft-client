package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.eventmanager.Event;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_NONE, Category.RENDER);
	}

	@Override
	public void onEnable() {
		Wrapper.mc.gameSettings.gammaSetting = 100f;
		super.onEnable();
	}

	@Override
	public void onDisable() {
		Wrapper.mc.gameSettings.gammaSetting = 1f;
		super.onDisable();
	}

	@Override
	public void onEvent(Event event) {

	}
}