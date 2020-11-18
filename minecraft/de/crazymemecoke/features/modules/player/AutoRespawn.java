package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.time.TimeHelper;
import org.lwjgl.input.Keyboard;

public class AutoRespawn extends Module {
    TimeHelper timeHelper = new TimeHelper();

    public AutoRespawn() {
        super("AutoRespawn", Keyboard.KEY_NONE, Category.PLAYER);

        Client.main().setMgr().addSetting(new Setting("Delay", this, 0, 0, 1000, false));
    }

    @Override
    public void onDisable() {
        timeHelper.reset();
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (!(mc.thePlayer.isEntityAlive())) {
                double delay = Client.main().setMgr().settingByName("Delay", this).getNum();
                if (timeHelper.isDelayComplete((long) delay)) {
                    mc.thePlayer.respawnPlayer();
                    timeHelper.reset();
                }
            }
        }
    }
}
