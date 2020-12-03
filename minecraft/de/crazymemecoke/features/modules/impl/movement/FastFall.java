package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.Wrapper;

@ModuleInfo(name = "FastFall", category = Category.MOVEMENT, description = "Fall extremely fast while you're in air (useful for webs)")
public class FastFall extends Module {

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
		Wrapper.mc.thePlayer.motionY = -30;
	}
}