package de.crazymemecoke.module.modules.player;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class FastUse extends Module {

	public FastUse() {
		super("FastUse", Keyboard.KEY_NONE, Category.PLAYER, Rainbow.rainbowNormal(1, 1).hashCode());
	}

	boolean NCP = true;

	@Override
	public void onUpdate() {
		if (this.getState()) {
			try {
				if (isSword(this.mc.thePlayer.inventory.getCurrentItem().getItem())) {
					return;
				}
			} catch (Exception e) {
				return;
			}
			if (this.mc.thePlayer.getItemInUseDuration() > 15) {
				for (int i = 0; i < 20; i++) {
					Minecraft.getMinecraft().thePlayer.sendQueue
							.addToSendQueue(new C03PacketPlayer(this.mc.thePlayer.onGround));
				}
				if ((this.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow)) {
					if (!NCP) {
						shootBow();
						return;
					}
					this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
							C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.UP));
					this.mc.thePlayer.stopUsingItem();
				} else {
					this.mc.thePlayer.stopUsingItem();
				}
			}
		}
	}

	public void shootBow() {
		int item = this.mc.thePlayer.inventory.currentItem;
		this.mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255,
				this.mc.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
		this.mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(item));
		for (int i = 0; i < 20; i++) {
			this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
		}
		this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
				C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.fromAngle(-1.0D)));
	}

	private boolean isSword(Item item) {
		return item instanceof ItemSword;
	}

}