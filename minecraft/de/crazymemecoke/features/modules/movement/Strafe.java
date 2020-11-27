package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.entity.PlayerUtil;

import java.util.ArrayList;

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