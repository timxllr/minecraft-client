package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class FastLadder extends Module {

    ArrayList<String> mode = new ArrayList<>();

    public FastLadder() {
        super("FastLadder", Keyboard.KEY_NONE, Category.MOVEMENT, -1);

        mode.add("Vanilla");
        mode.add("AAC");

        Client.instance().setMgr().newSetting(new Setting("Mode", this, "Vanilla", mode));
        Client.instance().setMgr().newSetting(new Setting("Vanilla Speed", this, 0.4, 0.1, 1.0, false));
        Client.instance().setMgr().newSetting(new Setting("AAC Speed", this, 0.2, 0.1, 1.0, false));
    }

    @Override
    public void onUpdate() {
        String mode = Client.instance().setMgr().getSettingByName("Mode", this).getMode();

        if (getState()) {
            switch (mode) {
                case "vanilla": {
                    doVanilla();
                    break;
                }
                case "aac": {
                    doAAC();
                    break;
                }
            }
        }
    }

    private void doAAC() {
        double speed = Client.instance().setMgr().getSettingByName("AAC Speed", this).getNum();

        if ((mc.thePlayer.isOnLadder()) && (mc.thePlayer.isCollidedHorizontally)) {
            mc.thePlayer.motionY = speed;
        }
    }

    private void doVanilla() {
        double speed = Client.instance().setMgr().getSettingByName("Vanilla Speed", this).getNum();

        if ((mc.thePlayer.isOnLadder()) && (mc.thePlayer.isCollidedHorizontally)) {
            mc.thePlayer.motionY = speed;
        }
    }
}