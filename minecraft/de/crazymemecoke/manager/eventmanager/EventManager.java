package de.crazymemecoke.manager.eventmanager;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.Module;

public class EventManager {

    public void onEvent(Event event) {
        try {
            for (Module module : Client.main().modMgr().getModules()) {
                if (module.enabled) {
                    module.onEvent(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
