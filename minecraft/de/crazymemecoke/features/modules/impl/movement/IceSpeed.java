package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.ModuleInfo;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
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