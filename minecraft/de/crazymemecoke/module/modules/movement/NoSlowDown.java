package de.crazymemecoke.module.modules.movement;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlowDown extends Module {

	public NoSlowDown() {
		super("NoSlowDown", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());
	}

	@Override
	public void onUpdate() {
		if (mc.thePlayer.isBlocking()) {
			this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
					C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.DOWN));
		}
	}

}