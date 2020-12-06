package com.masterof13fps.features.modules.impl.movement;

import com.masterof13fps.Client;
import com.masterof13fps.utils.entity.EntityUtils;
import com.masterof13fps.utils.entity.PlayerUtil;
import com.masterof13fps.features.modules.Category;
import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.manager.eventmanager.impl.EventUpdate;
import com.masterof13fps.manager.settingsmanager.Setting;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

@ModuleInfo(name = "Fly", category = Category.MOVEMENT, description = "Lets you fly")
public class Fly extends Module {

    public Setting mode = new Setting("Mode", this, "Fly", new String[]{"Fly", "Glide"});
    public Setting flyMode = new Setting("Fly Mode", this, "Jetpack", new String[]{"Vanilla", "Jetpack", "Hypixel", "Motion", "AAC 1.9.8", "AAC 1.9.10 Old", "AAC 1.9.10 New", "AAC 3.0.5", "CubeCraft", "Intave"});
    public Setting glideMode = new Setting("Glide Mode", this, "New", new String[]{"Old", "New"});
    public double motion;
    public boolean speed;
    public int time = 0;
    public int dtime = 0;
    double glidespeed = 0.07000000000000001D;
    private int delay = 0;

    @Override
    public void onToggle() {

    }

    @Override
    public void onDisable() {
        time = 0;
        dtime = 0;
        mc.thePlayer.capabilities.isFlying = false;
        mc.timer.timerSpeed = 1F;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {

            if (mode.getCurrentMode().equalsIgnoreCase("Fly")) {
                setDisplayName("Fly §7" + mode.getCurrentMode() + " / " + flyMode.getCurrentMode());

                if (flyMode.getCurrentMode().equalsIgnoreCase("Vanilla")) {
                    mc.thePlayer.capabilities.isFlying = true;
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("Motion")) {
                    mc.thePlayer.onGround = true;
                    mc.thePlayer.motionY = 0.0D;
                    if (mc.gameSettings.keyBindForward.isPressed() || mc.gameSettings.keyBindLeft.isPressed() || mc.gameSettings.keyBindRight.isPressed() || mc.gameSettings.keyBindBack.isPressed()) {
                        PlayerUtil.setSpeed(1.0D);
                    }
                    if (mc.gameSettings.keyBindSneak.pressed) {
                        mc.thePlayer.motionY -= 0.5D;
                    } else if (mc.gameSettings.keyBindJump.pressed) {
                        mc.thePlayer.motionY += 0.5D;
                    }
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("Hypixel")) {
                    mc.thePlayer.motionY = 0.0D;
                    mc.thePlayer.onGround = true;
                    mc.thePlayer.motionX *= 1.1D;
                    mc.thePlayer.motionZ *= 1.1D;
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("AAC 3.0.5")) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(mc.thePlayer, C02PacketUseEntity.Action.INTERACT));
                    if (delay == 0) {
                        mc.timer.timerSpeed = 1.1F;
                    }

                    if (delay == 2) {
                        mc.thePlayer.motionX *= 1.1D;
                        mc.thePlayer.motionZ *= 1.1D;
                        mc.thePlayer.motionY = 0.1D;
                    } else if (delay > 2) {
                        mc.timer.timerSpeed = 1.0F;
                        delay = 0;
                    }
                    ++delay;
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("AAC 1.9.10 Old")) {
                    if ((double) mc.thePlayer.fallDistance > 2.5D) {
                        ++mc.thePlayer.motionY;
                        mc.thePlayer.fallDistance = 0.0F;
                    }
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("Jetpack")) {
                    if (mc.gameSettings.keyBindJump.pressed) {
                        mc.thePlayer.jump();
                        mc.thePlayer.moveForward = 0.8F;
                    }
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("AAC 1.9.10 New")) {
                    double var6;
                    mc.thePlayer.jumpMovementFactor = 0.024F;
                    if (timeHelper.hasReached(500L)) {
                        if (!mc.thePlayer.onGround) {
                            if (mc.thePlayer.fallDistance > 4.0F) {
                                mc.thePlayer.motionX *= 0.6D;
                                mc.thePlayer.motionZ *= 0.6D;
                                var6 = mc.thePlayer.posY + 6.0D;
                                mc.thePlayer.setPositionAndUpdate(mc.thePlayer.posX, var6, mc.thePlayer.posZ);
                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                                timeHelper.reset();
                            }
                        }
                    } else {
                        mc.thePlayer.motionX *= 1.3D;
                        mc.thePlayer.motionZ *= 1.3D;
                        mc.thePlayer.motionY = -0.3D;
                    }
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("AAC 1.9.8")) {
                    mc.thePlayer.motionY = 0.2D;
                    if (timeHelper.hasReached(100L)) {
                        mc.thePlayer.motionY = -0.3D;
                        timeHelper.reset();
                    }
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("CubeCraft")) {
                    if (EntityUtils.isMoving() && !mc.thePlayer.onGround)
                        mc.timer.timerSpeed = 0.29f;

                    if (timeHelper.hasReached(200) && !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isInWater() && !mc.thePlayer.onGround && EntityUtils.isMoving()) {
                        double multiply = 2.1;
                        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
                        double posX = -Math.sin(yaw) * multiply;
                        double posZ = Math.cos(yaw) * multiply;
                        mc.thePlayer.setPosition(mc.thePlayer.posX + posX, mc.thePlayer.posY, mc.thePlayer.posZ + posZ);
                    }
                    mc.thePlayer.motionY = -0.01;
                } else if (flyMode.getCurrentMode().equalsIgnoreCase("Intave")) {
                    if (EntityUtils.isMoving() && !mc.thePlayer.onGround)
                        mc.timer.timerSpeed = 0.29f;

                    if (timeHelper.hasReached(200) && !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isInWater() && !mc.thePlayer.onGround && EntityUtils.isMoving()) {
                        double multiply = 2.1;
                        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
                        double posX = -Math.sin(yaw) * multiply;
                        double posZ = Math.cos(yaw) * multiply;
                        mc.thePlayer.setPosition(mc.thePlayer.posX + posX, mc.thePlayer.posY, mc.thePlayer.posZ + posZ);
                    }
                    mc.thePlayer.motionY = -0.01;
                }
            } else if (mode.getCurrentMode().equalsIgnoreCase("Glide")) {
                setDisplayName("Fly [" + mode + " / " + glideMode + "]");

                if (glideMode.getCurrentMode().equalsIgnoreCase("New")) {
                    motion = 0f;
                    motion = 0.0;
                    speed = true;
                    if (mc.thePlayer.isSneaking()) {
                        mc.thePlayer.motionY = -0.5;
                    } else {
                        if ((mc.thePlayer.motionY < 0.0D) && (mc.thePlayer.isAirBorne) && (!mc.thePlayer.isInWater())
                                && (!mc.thePlayer.isOnLadder()) && (!mc.thePlayer.isInsideOfMaterial(Material.lava))) {
                            mc.thePlayer.setSprinting(false);
                            mc.thePlayer.motionY = -motion;
                            mc.thePlayer.jumpMovementFactor *= 1.21337F;
                        }
                    }
                } else if (glideMode.getCurrentMode().equalsIgnoreCase("Old")) {
                    EntityUtils.damagePlayer(1);
                    if ((mc.thePlayer.motionY <= -glidespeed) && (!mc.thePlayer.isInWater())
                            && (!mc.thePlayer.onGround) && (!mc.thePlayer.isOnLadder())) {
                        mc.thePlayer.motionY = (-glidespeed);
                    }
                }
            }
        }
    }

    @Override
    public void onEnable() {
        if (Client.main().setMgr().settingByName("Mode", this).getCurrentMode().equalsIgnoreCase("Glide")) {
            if (Client.main().setMgr().settingByName("Glide Mode", this).getCurrentMode().equalsIgnoreCase("New")) {
                time = 0;
                dtime = 0;
                mc.thePlayer.setSprinting(false);
                double x = mc.thePlayer.posX;
                double y = mc.thePlayer.posY;
                double z = mc.thePlayer.posZ;
                double[] d = {0.2D, 0.24D};
                for (int a = 0; a < 100; a++) {
                    for (int i = 0; i < d.length; i++) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,
                                mc.thePlayer.posY + d[i], mc.thePlayer.posZ, false));
                    }
                }
            }
        }
    }
}
