package com.masterof13fps.features.modules.impl.movement;

import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.utils.entity.EntityUtils;
import com.masterof13fps.utils.entity.PlayerUtil;
import com.masterof13fps.manager.settingsmanager.Setting;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.manager.eventmanager.impl.EventMotion;
import com.masterof13fps.features.modules.Category;

@ModuleInfo(name = "LongJump", category = Category.MOVEMENT, description = "Lets you jump very far away")
public class LongJump extends Module {
    private boolean jump;

    public Setting mode = new Setting("Mode", this, "NCP", new String[] {"AAC Old", "NCP", "MineSecure"});

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion) {
            switch (mode.getCurrentMode()) {
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
        if (isMoving()) {
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
        if ((isMoving()) && (mc.thePlayer.fallDistance < 1.0F)) {
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

        if (mc.thePlayer.onGround && timeHelper.isDelayComplete(500L)) {
            mc.thePlayer.motionY = 0.42D;
            PlayerUtil.toFwd(2.3D);
            timeHelper.reset();
        } else if (!mc.thePlayer.onGround && jump) {
            mc.thePlayer.motionX = mc.thePlayer.motionZ = 0.0D;
            jump = false;
        }
    }

}
