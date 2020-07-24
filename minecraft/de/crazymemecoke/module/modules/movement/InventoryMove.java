package de.crazymemecoke.module.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

public class InventoryMove extends Module {

	public InventoryMove() {
		super("InventoryMove", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
	}

	public void onUpdate() {
		if (this.getState()) {
			if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiChat))) {
				KeyBinding[] moveKeys = { mc.gameSettings.keyBindForward, mc.gameSettings.keyBindSprint,
						mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight,
						mc.gameSettings.keyBindJump };
				KeyBinding[] array;
				int length = (array = moveKeys).length;
				for (int i = 0; i < length; i++) {
					KeyBinding bind = array[i];
					KeyBinding.setKeyBindState(bind.getKeyCode(), Keyboard.isKeyDown(bind.getKeyCode()));
				}
			}
		}
	}
}