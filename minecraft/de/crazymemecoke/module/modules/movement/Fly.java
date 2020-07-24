package de.crazymemecoke.module.modules.movement;

import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.entity.PlayerUtil;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class Fly extends Module {
    ArrayList<String> mode = new ArrayList<>();
    SettingsManager setMgr = Client.getInstance().getSetmgr();
    TimeHelper timer = new TimeHelper();
    private int delay = 0;

    public Fly() {
        super("Fly", Keyboard.KEY_NONE, Category.MOVEMENT, -1);

        mode.add("Vanilla");
        mode.add("Jetpack");
        mode.add("Hypixel");
        mode.add("Motion");
        mode.add("AAC 1.9.8");
        mode.add("AAC 1.9.10 Old");
        mode.add("AAC 1.9.10 New");
        mode.add("AAC 3.0.5");

        Client.getInstance().getSetmgr().rSetting(new Setting("Fly Mode", this, "Jetpack", mode));
    }

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
    }

    @Override
    public void onUpdate() {
        String mode = setMgr.getSettingByName("Fly Mode", this).getValString();
        if (getState()) {
            if (mode.equalsIgnoreCase("Vanilla")) {
                mc.thePlayer.capabilities.isFlying = true;
            } else if (mode.equalsIgnoreCase("Motion")) {
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
            } else if (mode.equalsIgnoreCase("Hypixel")) {
                mc.thePlayer.motionY = 0.0D;
                mc.thePlayer.onGround = true;
                mc.thePlayer.motionX *= 1.1D;
                mc.thePlayer.motionZ *= 1.1D;
            } else if (mode.equalsIgnoreCase("AAC 3.0.5")) {
                mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(mc.thePlayer, C02PacketUseEntity.Action.INTERACT));
                if (this.delay == 0) {
                    mc.timer.timerSpeed = 1.1F;
                }

                if (this.delay == 2) {
                    mc.thePlayer.motionX *= 1.1D;
                    mc.thePlayer.motionZ *= 1.1D;
                    mc.thePlayer.motionY = 0.1D;
                } else if (this.delay > 2) {
                    mc.timer.timerSpeed = 1.0F;
                    this.delay = 0;
                }
                ++this.delay;
            } else if (mode.equalsIgnoreCase("AAC 1.9.10 Old")) {
                if ((double) mc.thePlayer.fallDistance > 2.5D) {
                    ++mc.thePlayer.motionY;
                    mc.thePlayer.fallDistance = 0.0F;
                }
            } else if (mode.equalsIgnoreCase("Jetpack")) {
                if (mc.gameSettings.keyBindJump.pressed) {
                    mc.thePlayer.jump();
                    mc.thePlayer.moveForward = 0.8F;
                }
            } else if (mode.equalsIgnoreCase("AAC 1.9.10 New")) {
                double var6;
                mc.thePlayer.jumpMovementFactor = 0.024F;
                if (this.timer.hasReached(500L)) {
                    if (!mc.thePlayer.onGround) {
                        if (mc.thePlayer.fallDistance > 4.0F) {
                            mc.thePlayer.motionX *= 0.6D;
                            mc.thePlayer.motionZ *= 0.6D;
                            var6 = mc.thePlayer.posY + 6.0D;
                            mc.thePlayer.setPositionAndUpdate(mc.thePlayer.posX, var6, mc.thePlayer.posZ);
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                            timer.reset();
                        }
                    }
                } else {
                    mc.thePlayer.motionX *= 1.3D;
                    mc.thePlayer.motionZ *= 1.3D;
                    mc.thePlayer.motionY = -0.3D;
                }
            } else if (mode.equalsIgnoreCase("AAC 1.9.8")) {
                mc.thePlayer.motionY = 0.2D;
                if (timer.hasReached(100L)) {
                    mc.thePlayer.motionY = -0.3D;
                    timer.reset();
                }
            }
        }
    }
}
