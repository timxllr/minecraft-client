package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.utils.Wrapper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "FastPlace", category = Category.PLAYER, description = "Lets you place blocks real fast")
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