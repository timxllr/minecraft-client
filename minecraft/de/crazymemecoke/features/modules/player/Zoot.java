package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Zoot extends Module {

	public Zoot() {
		super("Zoot", Keyboard.KEY_NONE, Category.PLAYER);
	}

	public boolean potion = true;
	public boolean breath = true;
	public boolean firionfreeze = true;

	private final Potion[] potions = { Potion.poison, Potion.blindness, Potion.moveSlowdown, Potion.hunger };


	private boolean isStandingStill() {
		return (Math.abs(this.mc.thePlayer.motionX) <= 0.01D) && (Math.abs(this.mc.thePlayer.motionZ) <= 0.01D)
				&& (Math.abs(this.mc.thePlayer.motionY) <= 0.1D) && (Math.abs(this.mc.thePlayer.motionY) >= -0.1D);
	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof EventMotion) {
			if(((EventMotion) event).getType() == EventMotion.Type.PRE) {
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
		}
		if(event instanceof EventPacket) {
			if (((((EventPacket) event).getPacket() instanceof C03PacketPlayer)) && (isStandingStill()) && (!this.mc.thePlayer.isUsingItem())) {
				if (this.potion) {
					event.setCanceled(true);
					return;
				}
				if ((this.breath) && (this.mc.thePlayer.isCollidedVertically)
						&& (((this.mc.thePlayer.isInsideOfMaterial(Material.lava)) && (!this.mc.thePlayer.isBurning()))
						|| (this.mc.thePlayer.isInsideOfMaterial(Material.water)))) {
					event.setCanceled(true);
				}
				if ((this.firionfreeze) && (this.mc.thePlayer.isBurning()) && (!mc.thePlayer.isInWater())) {
					event.setCanceled(true);
				}
			}
		}
	}
}