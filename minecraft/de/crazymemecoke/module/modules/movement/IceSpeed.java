package de.crazymemecoke.module.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.init.Blocks;

public class IceSpeed extends Module {

	public IceSpeed() {
		super("IceSpeed", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbowNormal(1, 1).hashCode());
	}

	public void onUpdate() {
		if (this.getState()) {
			Blocks.ice.slipperiness = 0.39F;
			Blocks.packed_ice.slipperiness = 0.39F;
		} else {
			Blocks.ice.slipperiness = 0.98F;
			Blocks.packed_ice.slipperiness = 0.98F;
		}
	}
}