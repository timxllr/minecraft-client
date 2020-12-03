package de.crazymemecoke.features.modules.impl.world;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.Wrapper;

@ModuleInfo(name = "FastPlace", category = Category.WORLD, description = "Lets you place blocks real fast")
public class FastPlace extends Module {

	@Override
	public void onToggle() {

	}

	@Override
	public void onEnable() {

	}

	public void onDisable() {
		Wrapper.mc.rightClickDelayTimer = 6;
	}

	@Override
	public void onEvent(Event event) {
		Wrapper.mc.rightClickDelayTimer = 0;
	}
}