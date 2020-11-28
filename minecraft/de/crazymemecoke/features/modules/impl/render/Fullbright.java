package de.crazymemecoke.features.modules.impl.render;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.ModuleInfo;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
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