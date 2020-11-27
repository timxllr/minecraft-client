package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.utils.Wrapper;
import org.lwjgl.input.Keyboard;

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