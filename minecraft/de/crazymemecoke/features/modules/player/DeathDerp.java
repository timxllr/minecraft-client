package de.crazymemecoke.features.modules.player;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.utils.events.eventapi.EventTarget;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.events.UpdateEvent;

public class DeathDerp extends Module {
	public DeathDerp() {
		super("DeathDerp", Keyboard.KEY_NONE, Category.PLAYER, -1);
	}

	@EventTarget
	public void onUpdate(UpdateEvent e) {
		if (this.mc.thePlayer.getHealth() <= 0.0F) {
			this.mc.thePlayer.motionY = 0.5D;
		}
	}
}
