package de.crazymemecoke.module.modules.combat;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.input.Keyboard;
import com.darkmagician6.eventapi.EventTarget;

import de.crazymemecoke.command.commands.Friend;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.events.Op;
import de.crazymemecoke.utils.events.PacketSendEvent;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.RotationUtil;
import de.crazymemecoke.utils.time.TickEvent;
import de.crazymemecoke.utils.time.TimerUtil;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class KillAura extends Module {
	public KillAura() {
		super("KillAura", Keyboard.KEY_NONE, Category.COMBAT, -1);
	}

	@Op
	private boolean players = true;
	@Op
	private boolean animals = true;
	@Op
	private boolean monsters = true;
	@Op
	public static boolean hurttime = false;
	@Op
	public static boolean autoblock = true;
	public static int cps = 15;
	public static double range = 4.5D;
	private int maxTargets = 99;
	private int ticksToWait = 0;
	private long switchDelay = 1000L;
	private TimerUtil timer = new TimerUtil();
	private TimerUtil switchTimer = new TimerUtil();
	public static List<Entity> targets = new CopyOnWriteArrayList();
	public static boolean isAttacking;
	private float lastYaw;
	private float lastPitch;
	private int index = 0;
	boolean switched = false;

	public void onEnable() {
		this.timer.setLastMS();
		this.switchTimer.setLastMS();
		this.switched = false;
	}

	private boolean isCritting = false;

	private void attack(EntityLivingBase entity) {
		if (this.timer.isDelayComplete(1000 / cps)) {
			int currentItem2 = Wrapper.mc.thePlayer.inventory.currentItem;
			Wrapper.mc.thePlayer.isSprinting();
			if (Wrapper.mc.thePlayer.isBlocking()) {
				Wrapper.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(
						C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.UP));
			}
			if (mc.thePlayer.onGround) {

				this.isCritting = true;
				Wrapper.mc.getNetHandler()
						.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.mc.thePlayer.posX,
								Wrapper.mc.thePlayer.posY + 0.0625111D, Wrapper.mc.thePlayer.posZ, false));
				Wrapper.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(
						Wrapper.mc.thePlayer.posX, Wrapper.mc.thePlayer.posY, Wrapper.mc.thePlayer.posZ, false));

			}
			Wrapper.mc.thePlayer.swingItem();
			Wrapper.mc.getNetHandler().getNetworkManager()
					.sendPacket(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.ATTACK));
			for (int i = 0; i < 1; i++) {
				Wrapper.mc.getNetHandler().addToSendQueue(
						new C02PacketUseEntity(entity, new Vec3(entity.posX, entity.posY, entity.posZ)));
			}
			if (Wrapper.mc.thePlayer.moveForward > 0.0D) {
				Wrapper.mc.getNetHandler().addToSendQueue(
						new C0BPacketEntityAction(Wrapper.mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
			}
			targets.remove(entity);
			if (targets.size() == 0) {
				this.timer.setLastMS();
			}
		}
	}

	private Entity getTarget() {
		return (Entity) targets.get(0);
	}

	boolean isNameValid(EntityPlayer player) {
		Collection<NetworkPlayerInfo> playerInfos = Wrapper.mc.thePlayer.sendQueue.getPlayerInfoMap();
		for (NetworkPlayerInfo info : playerInfos) {
			if (info.getGameProfile().getName().matches(player.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidTarget(Entity entity) {
		if (entity == null) {
			return false;
		}
		if (!(entity instanceof EntityLivingBase)) {
			return false;
		}
		if ((entity instanceof EntityPlayer) && (!isNameValid((EntityPlayer) entity))) {
			return false;
		}
		if (entity == Wrapper.mc.thePlayer.ridingEntity) {
			return false;
		}
		if (entity.getEntityId() == 64199) {
			return false;
		}
		if (entity.isDead) {
			return false;
		}
		if (((entity instanceof EntityLivingBase)) && (((EntityLivingBase) entity).getHealth() <= 0.0F)) {
			return false;
		}
		if (!entity.isEntityAlive()) {
			return false;
		}
		if ((hurttime) && (((EntityLivingBase) entity).hurtTime > 0.3D)) {
			return false;
		}
		if (((EntityLivingBase) entity).getHealth() <= 0.0F) {
			return false;
		}
		if (((entity instanceof EntityPlayer)) && (Friend.Friend.contains(entity.getName()))) {
			return false;
		}
		if ((entity.isInvisible())) {
			return false;
		}
		if (entity == Wrapper.mc.thePlayer) {
			return false;
		}
		if (Wrapper.mc.thePlayer.getDistanceToEntity(entity) > range) {
			return false;
		}
		return true;
	}

	public void populateTargets() {
		for (Object o : Wrapper.mc.theWorld.loadedEntityList) {
			Entity entity = (Entity) o;
			if ((isValidTarget(entity)) && (targets.size() < this.maxTargets)) {
				targets.add(entity);
			}
		}
	}

	private EntityLivingBase target = null;

	@EventTarget
	public void onTick(TickEvent e) {
		setDisplayName(getName());
	}

	@Override
	public void onPreMotionUpdate() {
		if (this.getState()) {

			if (targets.isEmpty()) {
				populateTargets();
			}
			this.lastYaw = Wrapper.mc.thePlayer.rotationYaw;
			this.lastPitch = Wrapper.mc.thePlayer.rotationPitch;
			for (Entity entity : targets) {
				if (isValidTarget(entity)) {
					if ((autoblock)) {
						try {
							if (Wrapper.mc.thePlayer.getCurrentEquippedItem() != null) {
								if ((Wrapper.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) {
									Wrapper.mc.thePlayer.getCurrentEquippedItem().useItemRightClick(Wrapper.mc.theWorld,
											Wrapper.mc.thePlayer);
								}
							}
						} catch (NumberFormatException localNumberFormatException) {
						}
					}
					float[] entityRotations = RotationUtil.getEntityRotations(Wrapper.mc.thePlayer, entity);
					Wrapper.mc.thePlayer.rotationYaw = entityRotations[0];
					Wrapper.mc.thePlayer.rotationPitch = entityRotations[1];
				} else {
					targets.remove(entity);
				}
			}

		}
	}

	@Override
	public void onPostMotionUpdate() {
		if (this.getState()) {

			for (Entity o : targets) {
				if ((o instanceof EntityLivingBase)) {
					EntityLivingBase ent = (EntityLivingBase) o;
					if (isValidTarget(ent)) {
						attack(ent);
						isAttacking = false;
					}
				}
			}
			Wrapper.mc.thePlayer.rotationYaw = this.lastYaw;
			Wrapper.mc.thePlayer.rotationPitch = this.lastPitch;

		}
	}

	@EventTarget
	public void onPacketSend(PacketSendEvent e) {
		if ((e.getPacket() instanceof C03PacketPlayer)) {
			C03PacketPlayer p = (C03PacketPlayer) e.getPacket();
			for (Entity entity3 : targets) {
				if (isValidTarget(entity3)) {
					float[] entityRotations2 = RotationUtil.getEntityRotations(Wrapper.mc.thePlayer, entity3);
					p.yaw = entityRotations2[0];
					p.pitch = entityRotations2[1];
					isAttacking = true;
				}
			}
		}
	}
}
