package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class AutoSoup extends Module {

	private TimeHelper time = new TimeHelper();

	private long currentMS = 0L;
	private long lastSoup = -1L;
	private int oldslot = -1;

	public AutoSoup() {
		super("AutoSoup", Keyboard.KEY_NONE, Category.COMBAT);
	}

	private void eatSoup() {
		this.currentMS = (System.nanoTime() / 1000000L);
		if (!hasDelayRun(125L)) {
			return;
		}
		int oldSlot = this.mc.thePlayer.inventory.currentItem;
		for (int slot = 44; slot >= 9; slot--) {
			ItemStack stack = this.mc.thePlayer.inventoryContainer.getSlot(slot).getStack();

			if (stack != null)
				if ((slot >= 36) && (slot <= 44)) {
					if (Item.getIdFromItem(stack.getItem()) == 282) {
						this.mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot - 36));
						this.mc.thePlayer.sendQueue.addToSendQueue(
								new C08PacketPlayerBlockPlacement(this.mc.thePlayer.inventory.getCurrentItem()));
						this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
								C07PacketPlayerDigging.Action.DROP_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));

						this.mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(oldSlot));
						this.lastSoup = (System.nanoTime() / 1000000L);
					}
				} else if (Item.getIdFromItem(stack.getItem()) == 282) {
					this.mc.playerController.windowClick(0, slot, 0, 0, this.mc.thePlayer);
					this.mc.playerController.windowClick(0, 44, 0, 0, this.mc.thePlayer);
					this.lastSoup = (System.nanoTime() / 1000000L);
					return;
				}
		}
	}

	private boolean hasDelayRun(long l) {
		return this.currentMS - this.lastSoup >= l;
	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventUpdate) {
			if (this.mc.thePlayer.getHealth() < 14.0F) {
				eatSoup();
			}
		}
	}
}