package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.entity.PlayerUtil;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class Strafe extends Module {

    public Strafe() {
        super("Strafe", Keyboard.KEY_NONE, Category.MOVEMENT);

        ArrayList<String> mode = new ArrayList<>();

        mode.add("NCP");
        mode.add("AAC");

        Client.main().setMgr().addSetting(new Setting("Mode", this, "NCP", mode));
    }


    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion) {
            String mode = Client.main().setMgr().settingByName("Mode", this).getMode();

            setDisplayName("Strafe [" + mode + "]");

            switch (mode) {
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