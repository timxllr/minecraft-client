package de.crazymemecoke.features.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

public class Strafe extends Module {

    public Strafe() {
        super("Strafe", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbow(1, 1).hashCode());
    }

    @EventTarget
    public void onUpdate() {
        if (getState()) {
            if (!(mc.thePlayer.hurtTime > 0)) {
                if ((mc.thePlayer.onGround) || (mc.thePlayer.isAirBorne) && (!mc.thePlayer.isInWater())) {
                    float dir = mc.thePlayer.rotationYaw;
                    if (mc.thePlayer.moveForward < 0.0F) {
                        dir += 180.0F;
                    }
                    if (mc.thePlayer.moveStrafing > 0.0F) {
                        dir -= 90.0F * (mc.thePlayer.moveForward < 0.0F ? -0.5F
                                : mc.thePlayer.moveForward > 0.0F ? 0.8F : 1.0F);
                    }
                    if (mc.thePlayer.moveStrafing < 0.0F) {
                        dir += 90.0F * (mc.thePlayer.moveForward < 0.0F ? -0.5F
                                : mc.thePlayer.moveForward > 0.0F ? 0.8F : 1.0F);
                    }
                    double hOff = 0.221D;
                    if (mc.thePlayer.isSprinting()) {
                        hOff *= 1.3190000119209289D;
                    }
                    if (mc.thePlayer.isSneaking()) {
                        hOff *= 0.3D;
                    }

                    float var9 = (float) ((float) Math.cos((dir + 90.0F) * 3.141592653589793D / 180.0D) * hOff);
                    float zD = (float) ((float) Math.sin((dir + 90.0F) * 3.141592653589793D / 180.0D) * hOff);
                    if ((mc.gameSettings.keyBindForward.pressed) || (mc.gameSettings.keyBindLeft.pressed)
                            || (mc.gameSettings.keyBindRight.pressed) || (mc.gameSettings.keyBindBack.pressed)) {
                        mc.thePlayer.posX = (var9);
                        mc.thePlayer.posZ = (zD);
                    }
                }
            }
        }
    }
}