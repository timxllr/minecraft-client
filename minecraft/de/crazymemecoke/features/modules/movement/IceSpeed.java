package de.crazymemecoke.features.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.init.Blocks;

public class IceSpeed extends Module {

	public IceSpeed() {
		super("IceSpeed", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
	}

	public void onUpdate() {
		if (this.state()) {
			Blocks.ice.slipperiness = 0.39F;
			Blocks.packed_ice.slipperiness = 0.39F;
		} else {
			Blocks.ice.slipperiness = 0.98F;
			Blocks.packed_ice.slipperiness = 0.98F;
		}
	}
}