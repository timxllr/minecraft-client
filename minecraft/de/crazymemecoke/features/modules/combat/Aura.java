package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.events.impl.EventMotion;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.events.impl.EventMoveFlying;
import de.crazymemecoke.manager.events.impl.EventPacket;
import de.crazymemecoke.manager.events.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
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

public class Aura extends Module {

    SettingsManager sM = Client.main().setMgr();
    public static ArrayList<Entity> targets = new ArrayList<>();
    public static Entity currentTarget;
    double range, cps, ticksExisted;
    float yaw, pitch;
    long current, last;
    boolean teams, players, animals, mobs, villager, invisibles, rotations;
    String auraMode;

    public Aura() {
        super("Aura", Keyboard.KEY_NONE, Category.COMBAT, -1);

        sM.newSetting(new Setting("Ticks Existed", this, 30, 0, 100, true));
        sM.newSetting(new Setting("Range", this, 4, 3.5, 7, true));
        sM.newSetting(new Setting("CPS", this, 10, 1, 20, true));
        sM.newSetting(new Setting("Teams", this, false));
        sM.newSetting(new Setting("Players", this, true));
        sM.newSetting(new Setting("Animals", this, false));
        sM.newSetting(new Setting("Mobs", this, false));
        sM.newSetting(new Setting("Villager", this, false));
        sM.newSetting(new Setting("Invisibles", this, false));
        sM.newSetting(new Setting("Rotations", this, true));

        ArrayList<String> auraMode = new ArrayList<>();
        auraMode.add("Single");
        auraMode.add("Multi");
        sM.newSetting(new Setting("Mode", this, "Single", auraMode));
    }


    @Override
    public void onEnable() {
        if (sM.settingByName("Mode", this).getMode().equalsIgnoreCase("Multi")) {
            Client.main().modMgr().getByName("Aura").setState(false);
        }
    }

    @Override
    public void onDisable() {
        currentTarget = null;
        targets = null;
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof EventUpdate) {

            ticksExisted = sM.settingByName("Ticks Existed", this).getNum();
            range = sM.settingByName("Range", this).getNum();
            cps = sM.settingByName("CPS", this).getNum();
            teams = sM.settingByName("Teams", this).getBool();
            players = sM.settingByName("Players", this).getBool();
            animals = sM.settingByName("Animals", this).getBool();
            mobs = sM.settingByName("Mobs", this).getBool();
            villager = sM.settingByName("Villager", this).getBool();
            invisibles = sM.settingByName("Invisibles", this).getBool();
            rotations = sM.settingByName("Rotations", this).getBool();
            auraMode = sM.settingByName("Mode", this).getMode();

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

        if (event instanceof EventMotion) {
            if (((EventMotion) event).getType() == EventMotion.Type.PRE) {

                if (rotations)
                    return;

                ((EventMotion) event).setYaw(yaw);
                ((EventMotion) event).setPitch(pitch);

            } else if (((EventMotion) event).getType() == EventMotion.Type.POST) {
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
        if (event instanceof EventMoveFlying) {
            ((EventMoveFlying) event).setYaw(yaw);
        }
        if (event instanceof EventPacket) {
            if (((EventPacket) event).getType() == EventPacket.Type.SEND) {
                if (rotations)
                    return;

                if (((EventPacket) event).getPacket() instanceof C03PacketPlayer) {
                    C03PacketPlayer orig = (C03PacketPlayer) ((EventPacket) event).getPacket();
                    orig.yaw = yaw;
                    orig.pitch = pitch;
                    orig.rotating = true;
                    ((EventPacket) event).setPacket(orig);
                }
            }
        }
    }

    public void calcRotation(Entity target) {
        if (target == null || mc.thePlayer == null)
            return;
/*      Vec3 eyePosition = new Vec3(player.posX, player.getEntityBoundingBox().minY + player.getEyeHeight(), player.posZ);

        AxisAlignedBB tbb = target.getEntityBoundingBox();
        Vec3 targetVector = new Vec3(tbb.minX + (tbb.maxX - tbb.minX), tbb.minY + (tbb.maxY - tbb.minY), tbb.minZ + (tbb.maxZ - tbb.minZ));

        double xDiff = targetVector.xCoord - eyePosition.xCoord;
        double yDiff = targetVector.yCoord - eyePosition.yCoord;
        double zDiff = targetVector.zCoord - eyePosition.zCoord;

        yaw = -MathHelper.wrapAngleTo180_float((float) Math.toDegrees(Math.atan2(xDiff, zDiff)));
        pitch = MathHelper.wrapAngleTo180_float((float) -Math.toDegrees(Math.atan2(yDiff, Math.sqrt(xDiff * xDiff + zDiff * zDiff))));*/
        EntityPlayerSP player = Minecraft.mc().thePlayer;
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
        return entity != mc.thePlayer && entity.isEntityAlive() && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > ticksExisted;
    }
}