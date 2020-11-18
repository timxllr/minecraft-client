package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class CustomSpeed extends Module {

    double motionX, motionY, motionZ, timerSpeed;

    public CustomSpeed() {
        super("CustomSpeed", Keyboard.KEY_NONE, Category.MOVEMENT);

        Client.main().setMgr().addSetting(new Setting("Motion X", this, 0, 0, 50, false));
        Client.main().setMgr().addSetting(new Setting("Motion Y", this, 0, 0, 50, false));
        Client.main().setMgr().addSetting(new Setting("Motion Z", this, 0, 0, 50, false));
        Client.main().setMgr().addSetting(new Setting("Timer Speed", this, 1, 0, 50, false));
    }

    @Override
    public void onEvent(Event event) {
        motionX = Client.main().setMgr().settingByName("Motion X", this).getNum();
        motionY = Client.main().setMgr().settingByName("Motion Y", this).getNum();
        motionZ = Client.main().setMgr().settingByName("Motion Z", this).getNum();
        timerSpeed = Client.main().setMgr().settingByName("Timer Speed", this).getNum();

        if (event instanceof EventUpdate) {
            mc.thePlayer.motionX += motionX;
            mc.thePlayer.motionY += motionY;
            mc.thePlayer.motionZ += motionZ;
            mc.timer.timerSpeed = (float) timerSpeed;
        }
    }

    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1.0F;
    }
}
