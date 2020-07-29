package de.crazymemecoke.features.modules.combat;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.notificationmanager.Notification;
import de.crazymemecoke.manager.notificationmanager.NotificationManager;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.events.MoveEvent;
import de.crazymemecoke.utils.events.PacketSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Iterator;

public class Aura extends Module {

    SettingsManager sM = Client.getInstance().getSetmgr();
    public static ArrayList<Entity> targets = new ArrayList<>();
    public static Entity currentTarget;
    double range, cps;
    float yaw, pitch;
    long current, last;
    boolean teams, players, animals, mobs, villager, invisibles, rotations;
    String auraMode;

    public Aura() {
        super("Aura", Keyboard.KEY_NONE, Category.COMBAT, -1);

        sM.rSetting(new Setting("Range", this, 4, 3.5, 7, true));
        sM.rSetting(new Setting("CPS", this, 10, 1, 20, true));
        sM.rSetting(new Setting("Teams", this, false));
        sM.rSetting(new Setting("Players", this, true));
        sM.rSetting(new Setting("Animals", this, false));
        sM.rSetting(new Setting("Mobs", this, false));
        sM.rSetting(new Setting("Villager", this, false));
        sM.rSetting(new Setting("Invisibles", this, false));
        sM.rSetting(new Setting("Rotations", this, true));

        ArrayList<String> auraMode = new ArrayList<>();
        auraMode.add("Single");
        auraMode.add("Multi");
        sM.rSetting(new Setting("Mode", this, "Single", auraMode));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            range = sM.getSettingByName("Range", this).getValDouble();
            cps = sM.getSettingByName("CPS", this).getValDouble();
            teams = sM.getSettingByName("Teams", this).getValBoolean();
            players = sM.getSettingByName("Players", this).getValBoolean();
            animals = sM.getSettingByName("Animals", this).getValBoolean();
            mobs = sM.getSettingByName("Mobs", this).getValBoolean();
            villager = sM.getSettingByName("Villager", this).getValBoolean();
            invisibles = sM.getSettingByName("Invisibles", this).getValBoolean();
            rotations = sM.getSettingByName("Rotations", this).getValBoolean();
            auraMode = sM.getSettingByName("Mode", this).getValString();
        }
    }


    @Override
    public void onEnable() {
        EventManager.register(this);

        if (sM.getSettingByName("Mode", this).getValString().equalsIgnoreCase("Multi")) {
            Client.getInstance().getModuleManager().getModByName("Aura").setState(false);
        }
    }

    @Override
    public void onDisable() {
        EventManager.unregister(this);
        currentTarget = null;
        targets = null;
    }

    @Override
    public void onPreMotionUpdate() {
        if (getState()) {
            if (auraMode.equalsIgnoreCase("Single")) {
                currentTarget = getClosest(mc.playerController.getBlockReachDistance());

                if (currentTarget == null)
                    return;

                updateTime();

                if (rotations) {
                    yaw = mc.thePlayer.rotationYaw;
                    pitch = mc.thePlayer.rotationPitch;
                } else {
                    calcRotation(currentTarget);
                }

                if (current - last > 1000 / cps) {
                    attack(currentTarget);
                    resetTime();
                }
            }
        }
    }

    @Override
    public void onPostMotionUpdate() {
        if (getState()) {
            if (auraMode.equalsIgnoreCase("Single")) {
                if (currentTarget == null)
                    return;

                if (rotations) {
                    mc.thePlayer.rotationYaw = yaw;
                    mc.thePlayer.rotationPitch = pitch;
                }
            }
        }
    }

    @EventTarget
    public void onMove(MoveEvent e) {
        if (rotations)
            return;

        e.setYaw(yaw);
        e.setPitch(pitch);
    }

    public void calcRotation(Entity target) {
        if (target == null || mc.thePlayer == null)
            return;
/*
        Vec3 eyePosition = new Vec3(player.posX, player.getEntityBoundingBox().minY + player.getEyeHeight(), player.posZ);

        AxisAlignedBB tbb = target.getEntityBoundingBox();
        Vec3 targetVector = new Vec3(tbb.minX + (tbb.maxX - tbb.minX), tbb.minY + (tbb.maxY - tbb.minY), tbb.minZ + (tbb.maxZ - tbb.minZ));

        double xDiff = targetVector.xCoord - eyePosition.xCoord;
        double yDiff = targetVector.yCoord - eyePosition.yCoord;
        double zDiff = targetVector.zCoord - eyePosition.zCoord;

        yaw = -MathHelper.wrapAngleTo180_float((float) Math.toDegrees(Math.atan2(xDiff, zDiff)));
        pitch = MathHelper.wrapAngleTo180_float((float) -Math.toDegrees(Math.atan2(yDiff, Math.sqrt(xDiff * xDiff + zDiff * zDiff))));*/
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        double xDiff = target.posX - player.posX;
        double zDiff = target.posZ - player.posZ;
        double yDiff;
        if (target instanceof EntityLivingBase) {
            final EntityLivingBase targetEntity = (EntityLivingBase) target;
            yDiff = targetEntity.posY + targetEntity.getEyeHeight() - (player.posY + player.getEyeHeight());
        } else {
            yDiff = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (player.posY + player.getEyeHeight());
        }
        final double xzDiff = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        yaw = (float) (Math.atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f;
        pitch = (float) (-(Math.atan2(yDiff, xzDiff) * 180.0 / Math.PI));


    }


    private void attack(Entity entity) {
        if ((entity instanceof EntityPlayer && players) || (entity instanceof EntityMob && mobs) || (entity instanceof EntityAnimal && animals) || (entity instanceof EntityVillager && villager) || (invisibles)) {
            mc.thePlayer.swingItem();
            mc.playerController.attackEntity(mc.thePlayer, entity);
        }
    }

    private void updateTime() {
        current = (System.nanoTime() / 1000000L);
    }

    private void resetTime() {
        last = (System.nanoTime() / 1000000L);
    }

    private Entity getClosest(double range) {
        double dist = range;
        Entity target = null;
        for (Object object : mc.theWorld.loadedEntityList) {
            Entity entity = (Entity) object;
            if (canAttack(entity)) {
                double currentDist = mc.thePlayer.getDistanceToEntity(entity);
                if (currentDist <= dist) {
                    dist = currentDist;
                    target = entity;
                }
            }
        }
        return target;
    }

    private boolean canAttack(Entity entity) {
        return entity != mc.thePlayer && entity.isEntityAlive() && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > 30;
    }

    @EventTarget
    public void onPacket(PacketSendEvent e) {
        if (getState()) {
            if (rotations)
                return;

            if (e.getPacket() instanceof C03PacketPlayer) {
                C03PacketPlayer orig = (C03PacketPlayer) e.getPacket();
                orig.yaw = yaw;
                orig.pitch = pitch;
                orig.rotating = true;
                e.setPacket(orig);
            }
        }
    }
}