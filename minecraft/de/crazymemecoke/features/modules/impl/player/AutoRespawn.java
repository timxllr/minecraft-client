package de.crazymemecoke.features.modules.impl.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;

@ModuleInfo(name = "AutoRespawn", category = Category.PLAYER, description = "Automatically respawns when dead")
public class AutoRespawn extends Module {

    public Setting delay = new Setting("Delay", this, 0,0,1000,false);

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        timeHelper.reset();
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (!(mc.thePlayer.isEntityAlive())) {
                double delay = Client.main().setMgr().settingByName("Delay", this).getCurrentValue();
                if (timeHelper.isDelayComplete((long) delay)) {
                    mc.thePlayer.respawnPlayer();
                    timeHelper.reset();
                }
            }
        }
    }
}
