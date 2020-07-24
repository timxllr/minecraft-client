package de.crazymemecoke.module.modules.movement;

import de.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.Values;
import de.crazymemecoke.utils.entity.EntityUtils;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;


public class Glide extends Module {
    ArrayList<String> mode = new ArrayList<>();

    public Glide() {
        super("Glide", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
        mode.add("New");
        mode.add("Old");

        Client.getInstance().getSetmgr().rSetting(new Setting("Glide Mode", this, "New", mode));
    }

    public double motion;
    public boolean speed;
    public int time = 0;
    public int dtime = 0;

    @Override
    public void onEnable() {
        if (Client.getInstance().getSetmgr().getSettingByName("Glide Mode", this).getValString().equalsIgnoreCase("New")) {
            time = 0;
            dtime = 0;
            mc.thePlayer.setSprinting(false);
            double x = mc.thePlayer.posX;
            double y = mc.thePlayer.posY;
            double z = mc.thePlayer.posZ;
            if (mc.thePlayer != null) {
                double[] d = {0.2D, 0.24D};
                for (int a = 0; a < 100; a++) {
                    for (int i = 0; i < d.length; i++) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,
                                mc.thePlayer.posY + d[i], mc.thePlayer.posZ, false));
                    }
                }
            } else if (mc.thePlayer != null) {
                for (int i = 0; i < 4; i++) {
                    mc.thePlayer.sendQueue
                            .addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 1.01D, z, false));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, false));
                }
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.99D, z, false));
            }
        }
    }

    @Override
    public void onDisable() {
        if (Client.getInstance().getSetmgr().getSettingByName("Glide Mode", this).getValString().equalsIgnoreCase("New")) {
            time = 0;
            dtime = 0;
        }
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            if (Client.getInstance().getSetmgr().getSettingByName("Glide Mode", this).getValString().equalsIgnoreCase("New")) {
                motion = 0f;
                this.motion = 0.0;
                this.speed = true;
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
            } else if (Client.getInstance().getSetmgr().getSettingByName("Glide Mode", this).getValString().equalsIgnoreCase("Old")) {
                EntityUtils.damagePlayer(1);
                if ((mc.thePlayer.motionY <= -Values.getValues().glidespeed) && (!mc.thePlayer.isInWater())
                        && (!mc.thePlayer.onGround) && (!mc.thePlayer.isOnLadder())) {
                    mc.thePlayer.motionY = (-Values.getValues().glidespeed);
                }
            }
        }
    }
}
