package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class Step extends Module {

    public Step() {
        super("Step", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
        ArrayList<String> mode = new ArrayList<>();

        mode.add("Vanilla");

        Client.getInstance().getSetmgr().rSetting(new Setting("Mode", this, "Vanilla", mode));
    }

    SettingsManager sM = Client.getInstance().getSetmgr();

    public void onUpdate() {
        if (this.getState()) {
            if (sM.getSettingByName("Mode", this).getValString().equalsIgnoreCase("Vanilla")) {
                if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
                    mc.thePlayer.jump();
                } else {
                    mc.thePlayer.stepHeight = 0.5F;
                }
            }
        }
    }
}
