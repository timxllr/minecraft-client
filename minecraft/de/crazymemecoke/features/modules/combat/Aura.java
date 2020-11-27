package de.crazymemecoke.features.modules.combat;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.settingsmanager.SettingsManager;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.eventmanager.impl.EventMoveFlying;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.util.ArrayList;

@ModuleInfo(name = "Aura", category = Category.COMBAT, description = "You automatically hit players")
public class Aura extends Module {

    public static ArrayList<Entity> targets = new ArrayList<>();
    public static Entity currentTarget;
    SettingsManager sM = Client.main().setMgr();
    double range, cps, ticksExisted;
    float yaw, pitch, curYaw, curPitch;
    long current, last;
    boolean teams, players, animals, mobs, villager, rotations, ignoreDead;

    public Aura() {
        sM.addSetting(new Setting("Precision", this, 0.1F, 0.05F, 0.5F, false));
        sM.addSetting(new Setting("Accuracy", this, 0.3F, 0.1F, 0.8F, false));
        sM.addSetting(new Setting("Prediction Multiplier", this, 0.4F, 0F, 1F, false));
        sM.addSetting(new Setting("Ticks Existed", this, 30, 0, 100, true));
        sM.addSetting(new Setting("Range", this, 4, 3.5, 7, true));
        sM.addSetting(new Setting("CPS", this, 10, 1, 20, true));
        sM.addSetting(new Setting("Players", this, true));
        sM.addSetting(new Setting("Animals", this, false));
        sM.addSetting(new Setting("Mobs", this, false));
        sM.addSetting(new Setting("Villager", this, false));
        sM.addSetting(new Setting("Teams", this, false));
        sM.addSetting(new Setting("Rotations", this, true));
        sM.addSetting(new Setting("Ignore Dead", this, false));
    }

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
            ticksExisted = sM.settingByName("Ticks Existed", this).getCurrentValue();
            range = sM.settingByName("Range", this).getCurrentValue();
            cps = sM.settingByName("CPS", this).getCurrentValue();
            teams = sM.settingByName("Teams", this).isToggled();
            players = sM.settingByName("Players", this).isToggled();
            animals = sM.settingByName("Animals", this).isToggled();
            mobs = sM.settingByName("Mobs", this).isToggled();
            villager = sM.settingByName("Villager", this).isToggled();
            rotations = sM.settingByName("Rotations", this).isToggled();
            ignoreDead = sM.settingByName("Ignore Dead", this).isToggled();

            currentTarget = getClosest(mc.playerController.getBlockReachDistance());

            if (currentTarget == null)
                return;

            if (currentTarget instanceof EntityPlayer) {

            } else {
                setDisplayName("Aura");
            }

            updateTime();

            if (rotations) {
                float precision = (float) sM.settingByName("Precision", this).getCurrentValue();
                float accuracy = (float) sM.settingByName("Accuracy", this).getCurrentValue();
                float predictionMultiplier = (float) sM.settingByName("Prediction Multiplier", this).getCurrentValue();

                float[] rots = faceEntity(currentTarget, curYaw, curPitch, precision, accuracy, predictionMultiplier);
                yaw = rots[0];
                pitch = rots[1];
                curYaw = yaw;
                curPitch = pitch;
            } else {
                yaw = mc.thePlayer.rotationYaw;
                pitch = mc.thePlayer.rotationPitch;
            }

            if (current - last > 1000 / cps) {
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

                if (!rotations) {
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
                if (!rotations)
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

    public boolean shouldAttack() {
        return (currentTarget instanceof EntityPlayer && sM.settingByName("Players", this).isToggled()) || (currentTarget instanceof EntityAnimal && sM.settingByName("Animals", this).isToggled()) || (currentTarget instanceof EntityMob && sM.settingByName("Mobs", this).isToggled()) || (currentTarget instanceof EntityVillager && sM.settingByName("Villager", this).isToggled());
    }

    public float updateRotation(float curRot, float destination, float speed) {
        float f = MathHelper.wrapAngleTo180_float(destination - curRot);

        if (f > speed) {
            f = speed;
        }

        if (f < -speed) {
            f = -speed;
        }

        return curRot + f;
    }

    public final Vec3 getVectorForRotation(float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2, f3, f * f2);
    }

    public Vec3 getLook(float yaw, float pitch) {
        return getVectorForRotation(pitch, yaw);
    }

    public Vec3 getBestVector(Entity entity, float accuracy, float precision) {
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

    public float[] faceEntity(Entity entity, float currentYaw, float currentPitch, float accuracy, float precision, float predictionMultiplier) {
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
        if ((entity instanceof EntityPlayer && players) || (entity instanceof EntityMob && mobs) || (entity instanceof EntityAnimal && animals) || (entity instanceof EntityVillager && villager)) {
            mc.thePlayer.swingItem();
            mc.playerController.attackEntity(mc.thePlayer, entity);

            if (Client.main().setMgr().settingByName("Target HUD", Client.main().modMgr().getByName("HUD")).isToggled()) {
                renderTargetHUD();
            }
        }
    }

    private void renderTargetHUD() {
        ScaledResolution s = new ScaledResolution(mc);

        if (Aura.currentTarget instanceof EntityPlayer && Client.main().setMgr().settingByName("Target HUD", Client.main().modMgr().getByName("HUD")).isToggled()) {
            RenderUtils.drawRect(s.width() / 2 - 130, s.height() / 2 - 60, s.width() / 2 + 90, s.height() / 2 + 20, new Color(0, 0, 0, 110).getRGB());

            EntityPlayer p = (EntityPlayer) Aura.currentTarget;
            Client.main().fontMgr().cabin23.drawStringWithShadow("Spieler: " + p.getName(), s.width() / 2 - 125, s.height() / 2 - 45, -1);
            Client.main().fontMgr().cabin23.drawStringWithShadow("Leben: " + p.getHealth() + " / " + p.getMaxHealth(), s.width() / 2 - 125, s.height() / 2 - 35, -1);

            ItemStack i = p.getCurrentEquippedItem();
            if (i == null) {
                Client.main().fontMgr().cabin23.drawStringWithShadow("Item: Kein Item", s.width() / 2 - 125, s.height() / 2 - 25, -1);
            } else {
                Client.main().fontMgr().cabin23.drawStringWithShadow("Item: " + i.getDisplayName(), s.width() / 2 - 125, s.height() / 2 - 25, -1);
            }

            GuiInventory.drawEntityOnScreen(s.width() / 2 + 30, s.height() / 2 + 15, 30, (float) (51) - 50, (float) (75 - 50) - 20, (EntityLivingBase) Aura.currentTarget);

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
        return entity != mc.thePlayer && (ignoreDead && !entity.isDead) && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > ticksExisted;
    }
}