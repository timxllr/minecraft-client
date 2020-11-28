package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.ModuleInfo;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.utils.Wrapper;

@ModuleInfo(name = "Sprint", category = Category.MOVEMENT, description = "Automatically sprints when moving")
public class Sprint extends Module {

	@Override
	public void onToggle() {

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			Wrapper.mc.thePlayer.setSprinting(true);
		}
	}
}