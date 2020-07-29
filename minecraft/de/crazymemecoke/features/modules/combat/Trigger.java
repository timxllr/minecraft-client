package de.crazymemecoke.features.modules.combat;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.events.Op;
import de.crazymemecoke.utils.time.TimerUtil;
import de.crazymemecoke.utils.events.UpdateEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Trigger extends Module {
	public Trigger() {
		super("Trigger", Keyboard.KEY_NONE, Category.COMBAT, -1);
	}

	public static int cps = 12;
	@Op
	public static boolean hurttime = false;
	private boolean isCritting;
	private TimerUtil delay = new TimerUtil();

	public void onEnable() {
		this.delay.setLastMS();
	}

	@EventTarget
	public void onUpdate(UpdateEvent e) {
		boolean sprint = this.mc.thePlayer.isSprinting();
		try {
			if (this.mc.objectMouseOver.typeOfHit == null) {
				return;
			}
			if ((this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
					&& (this.delay.isDelayComplete(1000 / cps))) {
				this.delay.setLastMS();
				Entity ent = this.mc.objectMouseOver.entityHit;
				if ((ent instanceof EntityLivingBase)) {
					if (this.mc.thePlayer.getDistanceToEntity(ent) > 3.9D) {
						return;
					}
					if ((hurttime) && (((EntityLivingBase) ent).hurtTime > 0.3D)) {
						return;
					}
					if (!(ent instanceof EntityPlayer)) {
						return;
					}
					if (Friend.Friend.contains(ent.getName())) {
						return;
					}
					if (ent.isInvisible()) {
						return;
					}
					this.mc.thePlayer.setSprinting(false);
					if ((ent.isEntityAlive()) && (!this.mc.thePlayer.isEating()) && (!this.mc.thePlayer.isDead)) {
						if (this.mc.thePlayer.isBlocking()) {
							this.mc.getNetHandler().addToSendQueue(
									new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM,
											new BlockPos(0, 0, 0), EnumFacing.UP));
						}
						this.mc.thePlayer.swingItem();
						this.mc.getNetHandler().getNetworkManager()
								.sendPacket(new C02PacketUseEntity(ent, C02PacketUseEntity.Action.ATTACK));
						for (int i = 0; i < 3; i++) {
							this.mc.getNetHandler().addToSendQueue(
									new C02PacketUseEntity(ent, new Vec3(ent.posX, ent.posY, ent.posZ)));
						}
						this.mc.thePlayer.setSprinting(true);
					}
				}
			}
		} catch (Exception localException) {
		}
	}
}
