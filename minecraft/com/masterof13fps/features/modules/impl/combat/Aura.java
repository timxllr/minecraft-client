package com.masterof13fps.features.modules.impl.combat;

import com.masterof13fps.features.modules.Category;
import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.manager.eventmanager.impl.EventMotion;
import com.masterof13fps.manager.eventmanager.impl.EventMoveFlying;
import com.masterof13fps.manager.eventmanager.impl.EventPacket;
import com.masterof13fps.manager.eventmanager.impl.EventUpdate;
import com.masterof13fps.manager.settingsmanager.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import java.util.ArrayList;

@ModuleInfo(name = "Aura", category = Category.COMBAT, description = "You automatically hit players")
public class Aura extends Module {

    public static Entity currentTarget;
    static ArrayList<Entity> targets = new ArrayList<>();
    Setting precision = new Setting("Precision", this, 0.1, 0.05, 0.5, false);
    Setting accuracy = new Setting("Accuracy", this, 0.3, 0.1, 0.8, false);
    Setting predictionMultiplier = new Setting("Prediction Multiplier", this, 0.4, 0, 1, false);
    Setting ticksExisted = new Setting("Ticks Existed", this, 30, 0, 100, true);
    Setting range = new Setting("Range", this, 4, 3.5, 7, true);
    Setting cps = new Setting("CPS", this, 10, 1, 20, true);
    Setting players = new Setting("Players", this, true);
    Setting animals = new Setting("Animals", this, false);
    Setting mobs = new Setting("Mobs", this, false);
    Setting villager = new Setting("Villager", this, false);
    Setting teams = new Setting("Teams", this, false);
    Setting rotations = new Setting("Rotations", this, true);
    Setting ignoreDead = new Setting("Ignore Dead", this, false);
    Setting targetHUD = new Setting("Target HUD", this, true);
    float yaw, pitch, curYaw, curPitch;
    long current, last;

    @Override
    public void onToggle() {
    }


    @Override
    public void onEnable() {
        curYaw = mc.thePlayer.rotationYaw;
        curPitch = mc.thePlayer.rotationPitch;
    }

    @Override
    public void onDisable() {
        currentTarget = null;
        targets = null;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            currentTarget = getClosest(mc.playerController.getBlockReachDistance());

            if (currentTarget == null)
                return;

            if (currentTarget instanceof EntityPlayer)
                setDisplayName("Aura §7" + currentTarget.getName());
            else
                setDisplayName("Aura");


            updateTime();

            if (rotations.isToggled()) {
                float[] rots = faceEntity(currentTarget, curYaw, curPitch, (float) precision.getCurrentValue(),
                        (float) accuracy.getCurrentValue(),
                        (float) predictionMultiplier.getCurrentValue());
                yaw = rots[0];
                pitch = rots[1];
                curYaw = yaw;
                curPitch = pitch;
            } else {
                yaw = mc.thePlayer.rotationYaw;
                pitch = mc.thePlayer.rotationPitch;
            }

            if (current - last > 1000 / cps.getCurrentValue()) {
                attack(currentTarget);
                resetTime();
            }
        }

        if (event instanceof EventMotion) {
            if (((EventMotion) event).getType() == EventMotion.Type.PRE) {
                if (shouldAttack()) {
                    ((EventMotion) event).setYaw(yaw);
                    ((EventMotion) event).setPitch(pitch);
                }
            } else if (((EventMotion) event).getType() == EventMotion.Type.POST) {
                if (currentTarget == null)
                    return;

                if (!rotations.isToggled()) {
                    if (shouldAttack()) {
                        mc.thePlayer.rotationYaw = yaw;
                        mc.thePlayer.rotationPitch = pitch;
                    }
                }
            }
        }
        if (event instanceof EventMoveFlying) {
            try {
                if (currentTarget != null || !targets.isEmpty()) {
                    if (shouldAttack()) {
                        ((EventMoveFlying) event).setYaw(yaw);
                    }
                }
            } catch (NullPointerException ignored) {
            }
        }
        if (event instanceof EventPacket) {
            if (((EventPacket) event).getType() == EventPacket.Type.SEND) {
                if (!rotations.isToggled())
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

    boolean shouldAttack() {
        return (currentTarget instanceof EntityPlayer && players.isToggled()) || (currentTarget instanceof EntityAnimal && animals.isToggled()) || (currentTarget instanceof EntityMob && mobs.isToggled()) || (currentTarget instanceof EntityVillager && villager.isToggled());
    }

    float updateRotation(float curRot, float destination, float speed) {
        float f = MathHelper.wrapAngleTo180_float(destination - curRot);

        if (f > speed) {
            f = speed;
        }

        if (f < -speed) {
            f = -speed;
        }

        return curRot + f;
    }

    Vec3 getBestVector(Entity entity, float accuracy, float precision) {
        try {
            Vec3 playerVector = mc.thePlayer.getPositionEyes(1.0F);
            Vec3 nearestVector = new Vec3(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

            float height = entity.height;
            float width = entity.width * accuracy;

            for (float y = 0; y < height; y += precision) {
                for (float x = -width; x < width; x += precision) {
                    for (float z = -width; z < width; z += precision) {
                        Vec3 currentVector = new Vec3(entity.posX + x * width, entity.posY + (entity.getEyeHeight() / height) * y, entity.posZ + z * width);

                        if (playerVector.distanceTo(currentVector) < playerVector.distanceTo(nearestVector))
                            nearestVector = currentVector;
                    }
                }
            }
            return nearestVector;
        } catch (Exception e) {
            return entity.getPositionVector();
        }
    }

    float[] faceEntity(Entity entity, float currentYaw, float currentPitch, float accuracy, float precision, float predictionMultiplier) {
        Vec3 rotations = getBestVector(entity, accuracy, precision);

        double x = rotations.xCoord - mc.thePlayer.posX;
        double y = rotations.yCoord - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        double z = rotations.zCoord - mc.thePlayer.posZ;

        double xDiff = (entity.posX - entity.prevPosX) * predictionMultiplier;
        double zDiff = (entity.posZ - entity.prevPosZ) * predictionMultiplier;

        double distance = mc.thePlayer.getDistanceToEntity(entity);

        if (distance < 0.05)
            return new float[]{currentYaw, currentPitch};

        double angle = MathHelper.sqrt_double(x * x + z * z);
        float yawAngle = (float) (MathHelper.func_181159_b(z + zDiff, x + xDiff) * 180.0D / Math.PI) - 90.0F;
        float pitchAngle = (float) (-(MathHelper.func_181159_b(y, angle) * 180.0D / Math.PI));
        float finalPitch = pitchAngle >= 90 ? 90 : pitchAngle;
        float f = mc.gameSettings.mouseSensitivity * 0.8F + 0.2F;
        float f1 = f * f * f * 1.5F;

        float f2 = (yawAngle - currentYaw) * f1;
        float f3 = (finalPitch - currentPitch) * f1;

        float difYaw = yawAngle - currentYaw;
        float difPitch = finalPitch - currentPitch;

        float yaw = updateRotation(currentYaw + f2, yawAngle, Math.abs(MathHelper.wrapAngleTo180_float(difYaw * 0.1F)));
        float pitch = updateRotation(currentPitch + f3, finalPitch, Math.abs(MathHelper.wrapAngleTo180_float(difPitch * 0.1F)));

        yaw -= yaw % f1;
        pitch -= pitch % f1;

        return new float[]{yaw, pitch};
    }


    private void attack(Entity entity) {
        if ((entity instanceof EntityPlayer && players.isToggled()) || (entity instanceof EntityMob && mobs.isToggled()) || (entity instanceof EntityAnimal && animals.isToggled()) || (entity instanceof EntityVillager && villager.isToggled())) {
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
        if (!ignoreDead.isToggled()) {
            return entity != mc.thePlayer && !entity.isDead && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > ticksExisted.getCurrentValue();
        } else {
            return entity != mc.thePlayer && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > ticksExisted.getCurrentValue();
        }
    }
}