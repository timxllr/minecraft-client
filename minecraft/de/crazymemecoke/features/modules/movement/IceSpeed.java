package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.init.Blocks;

public class IceSpeed extends Module {

	public IceSpeed() {
		super("IceSpeed", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			Blocks.ice.slipperiness = 0.39F;
			Blocks.packed_ice.slipperiness = 0.39F;
		}
	}

	@Override
	public void onDisable() {
		Blocks.ice.slipperiness = 0.98F;
		Blocks.packed_ice.slipperiness = 0.98F;
		super.onDisable();
	}
}