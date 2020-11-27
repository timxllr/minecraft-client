package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;

@ModuleInfo(name = "Fullbright", category = Category.RENDER, description = "Makes everything really bright")
public class Fullbright extends Module {

	@Override
	public void onToggle() {

	}

	@Override
	public void onEnable() {
		Wrapper.mc.gameSettings.gammaSetting = 100f;
	}

	@Override
	public void onDisable() {
		Wrapper.mc.gameSettings.gammaSetting = 1f;
	}

	@Override
	public void onEvent(Event event) {

	}
}