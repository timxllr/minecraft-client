package de.crazymemecoke.features.modules.impl.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.ModuleInfo;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;

@ModuleInfo(name = "DeathDerp", category = Category.PLAYER, description = "Let you travel into the heaven when dead")
public class DeathDerp extends Module {
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
			if (mc.thePlayer.getHealth() <= 0.0F) {
				mc.thePlayer.motionY = 0.5D;
			}
		}
	}
}
