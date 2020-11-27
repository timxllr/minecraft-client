package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.init.Blocks;

@ModuleInfo(name = "IceSpeed", category = Category.MOVEMENT, description = "Lets you walk very fast on (packed-)ice")
public class IceSpeed extends Module {

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			Blocks.ice.slipperiness = 0.39F;
			Blocks.packed_ice.slipperiness = 0.39F;
		}
	}

	@Override
	public void onToggle() {

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {
		Blocks.ice.slipperiness = 0.98F;
		Blocks.packed_ice.slipperiness = 0.98F;
	}
}