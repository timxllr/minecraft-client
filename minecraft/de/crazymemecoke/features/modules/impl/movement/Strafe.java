package de.crazymemecoke.features.modules.impl.movement;

import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.entity.PlayerUtil;

@ModuleInfo(name = "Strafe", category = Category.MOVEMENT, description = "Fixes your strafings when you're in air")
public class Strafe extends Module {

    public Setting mode = new Setting("Mode", this, "NCP", new String[] {"NCP", "AAC"});

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
            setDisplayName("Strafe [" + mode + "]");

            switch (mode.getCurrentMode()) {
                case "NCP": {
                    doNCP();
                    break;
                }
                case "AAC": {
                    doAAC();
                    break;
                }
            }
        }
    }

    private void doAAC() {
        if (EntityUtils.isMoving()) {
            if (mc.gameSettings.keyBindJump.pressed) {
                PlayerUtil.setSpeed(0.23D);
            } else {
                PlayerUtil.setSpeed(0.135D);
            }
        }
    }

    private void doNCP() {
        if (EntityUtils.isMoving()) {
            if (mc.gameSettings.keyBindJump.pressed) {
                PlayerUtil.setSpeed(0.26D);
            } else {
                PlayerUtil.setSpeed(0.17D);
            }
        }
    }
}