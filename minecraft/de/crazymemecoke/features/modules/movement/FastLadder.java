package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class FastLadder extends Module {

    ArrayList<String> mode = new ArrayList<>();

    public FastLadder() {
        super("FastLadder", Keyboard.KEY_NONE, Category.MOVEMENT);

        mode.add("Vanilla");
        mode.add("AAC");

        Client.main().setMgr().addSetting(new Setting("Mode", this, "Vanilla", mode));
        Client.main().setMgr().addSetting(new Setting("Vanilla Speed", this, 0.4, 0.1, 1.0, false));
        Client.main().setMgr().addSetting(new Setting("AAC Speed", this, 0.2, 0.1, 1.0, false));
    }

    private void doAAC() {
        double speed = Client.main().setMgr().settingByName("AAC Speed", this).getNum();

        if ((mc.thePlayer.isOnLadder()) && (mc.thePlayer.isCollidedHorizontally)) {
            mc.thePlayer.motionY = speed;
        }
    }

    private void doVanilla() {
        double speed = Client.main().setMgr().settingByName("Vanilla Speed", this).getNum();

        if ((mc.thePlayer.isOnLadder()) && (mc.thePlayer.isCollidedHorizontally)) {
            mc.thePlayer.motionY = speed;
        }
    }

    @Override
    public void onEvent(Event event) {
        String mode = Client.main().setMgr().settingByName("Mode", this).getMode();

        switch (mode) {
            case "Vanilla": {
                doVanilla();
                break;
            }
            case "AAC": {
                doAAC();
                break;
            }
        }
    }
}