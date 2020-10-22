package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlowDown extends Module {

	public NoSlowDown() {
		super("NoSlowDown", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
	}

	@Override
	public void onEvent(Event event) {
		if (mc.thePlayer.isBlocking()) {
			this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
					C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.DOWN));
		}
	}
}