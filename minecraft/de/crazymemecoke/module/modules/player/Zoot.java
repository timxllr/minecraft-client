package de.crazymemecoke.module.modules.player;

import org.lwjgl.input.Keyboard;
import com.darkmagician6.eventapi.EventTarget;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.events.PacketSendEvent;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Zoot extends Module {

	public Zoot() {
		super("Zoot", Keyboard.KEY_NONE, Category.PLAYER, Rainbow.rainbowNormal(1, 1).hashCode());
	}

	public boolean potion = true;
	public boolean breath = true;
	public boolean firionfreeze = true;

	private final Potion[] potions = { Potion.poison, Potion.blindness, Potion.moveSlowdown, Potion.hunger };

	@Override
	public void onPreMotionUpdate() {
		Potion[] arrayOfPotion;
		int j = (arrayOfPotion = this.potions).length;
		for (int i = 0; i < j; i++) {
			Potion potion = arrayOfPotion[i];
			if (this.mc.thePlayer.getActivePotionEffect(potion) != null) {
				PotionEffect effect = this.mc.thePlayer.getActivePotionEffect(potion);
				for (int index = 0; index < effect.getDuration() / 20; index++) {
					this.mc.getNetHandler().addToSendQueue(new C03PacketPlayer(this.mc.thePlayer.onGround));
				}
			}
		}
		if ((this.mc.thePlayer.isBurning()) && (!this.mc.thePlayer.isPotionActive(Potion.fireResistance))
				&& (!this.mc.thePlayer.isImmuneToFire()) && (this.mc.thePlayer.onGround)
				&& (!this.mc.thePlayer.isInWater()) && (!this.mc.thePlayer.isInsideOfMaterial(Material.lava))
				&& (!this.mc.thePlayer.isInsideOfMaterial(Material.fire))) {
			for (int index = 0; index < 20; index++) {
				this.mc.getNetHandler().addToSendQueue(new C03PacketPlayer(this.mc.thePlayer.onGround));
			}
		}
	}

	@EventTarget
	public void onPacketSend(PacketSendEvent e) {
		if (((e.getPacket() instanceof C03PacketPlayer)) && (isStandingStill()) && (!this.mc.thePlayer.isUsingItem())) {
			if (this.potion) {
				e.setCancelled(true);
				return;
			}
			if ((this.breath) && (this.mc.thePlayer.isCollidedVertically)
					&& (((this.mc.thePlayer.isInsideOfMaterial(Material.lava)) && (!this.mc.thePlayer.isBurning()))
							|| (this.mc.thePlayer.isInsideOfMaterial(Material.water)))) {
				e.setCancelled(true);
			}
			if ((this.firionfreeze) && (this.mc.thePlayer.isBurning()) && (!mc.thePlayer.isInWater())) {
				e.setCancelled(true);
			}
		}
	}

	private boolean isStandingStill() {
		return (Math.abs(this.mc.thePlayer.motionX) <= 0.01D) && (Math.abs(this.mc.thePlayer.motionZ) <= 0.01D)
				&& (Math.abs(this.mc.thePlayer.motionY) <= 0.1D) && (Math.abs(this.mc.thePlayer.motionY) >= -0.1D);
	}
}