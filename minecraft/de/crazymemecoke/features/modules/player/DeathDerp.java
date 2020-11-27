package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

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
