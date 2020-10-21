package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.events.impl.EventUpdate;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class DeathDerp extends Module {
	public DeathDerp() {
		super("DeathDerp", Keyboard.KEY_NONE, Category.PLAYER, -1);
	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			if (this.mc.thePlayer.getHealth() <= 0.0F) {
				this.mc.thePlayer.motionY = 0.5D;
			}
		}
	}
}
