package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.entity.PlayerUtil;
import de.crazymemecoke.utils.time.TimeHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class LongJump extends Module {
    private final TimeHelper timer = new TimeHelper();
    private boolean jump;

    public LongJump() {
        super("LongJump", Keyboard.KEY_NONE, Category.MOVEMENT);

        ArrayList<String> mode = new ArrayList<>();

        mode.add("AAC Old");
        mode.add("NCP");
        mode.add("MineSecure");

        Client.main().setMgr().addSetting(new Setting("Mode", this, "NCP", mode));
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion) {
            switch (Client.main().setMgr().settingByName("Mode", this).getMode()) {
                case "AAC Old": {
                    doAACOld();
                    break;
                }
                case "NCP": {
                    doNCP();
                    break;
                }
                case "MineSecure": {
                    doMineSecure();
                    break;
                }
            }
        }
    }

    private void doMineSecure() {
        if (EntityUtils.isMoving()) {
            if ((mc.thePlayer.onGround) && !(mc.thePlayer.isInWater())) {
                mc.timer.timerSpeed = 1.0F;
                mc.thePlayer.motionY = 0.54D;
            } else if (!mc.thePlayer.isInWater()) {
                PlayerUtil.setSpeed(3.0D);
            }
        } else {
            mc.thePlayer.motionZ = (mc.thePlayer.motionX = 0.0D);
        }
    }

    private void doNCP() {
        if ((EntityUtils.isMoving()) && (mc.thePlayer.fallDistance < 1.0F)) {
            float x = (float) -Math.sin(PlayerUtil.getDirection());
            float z = (float) Math.cos(PlayerUtil.getDirection());
            if (mc.thePlayer.isCollidedVertically && mc.gameSettings.keyBindJump.pressed) {
                mc.thePlayer.motionX = (x * 0.29F);
                mc.thePlayer.motionZ = (z * 0.29F);
            }
            if (mc.thePlayer.motionY == 0.33319999363422365D) {
                mc.thePlayer.motionX = (x * 1.261D);
                mc.thePlayer.motionZ = (z * 1.261D);
            }
        }
    }

    private void doAACOld() {
        mc.gameSettings.keyBindForward.pressed = false;
        if (mc.thePlayer.onGround) {
            jump = true;
        }

        if (mc.thePlayer.onGround && timer.isDelayComplete(500L)) {
            mc.thePlayer.motionY = 0.42D;
            PlayerUtil.toFwd(2.3D);
            timer.reset();
        } else if (!mc.thePlayer.onGround && jump) {
            mc.thePlayer.motionX = mc.thePlayer.motionZ = 0.0D;
            jump = false;
        }
    }

}
