package de.crazymemecoke.features.modules.misc;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventTick;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.time.TimeHelper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "MemoryCleaner", category = Category.MISC, description = "Cleans your RAM (for more performance)")
public class MemoryCleaner extends Module {
    TimeHelper timeHelper = new TimeHelper();

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
        if(event instanceof EventTick && !(mc.theWorld == null)){
            if(timeHelper.hasReached(60000L)){
                Thread cleanThread = new Thread(() -> {
                    System.gc();
                    NotifyUtil.notification("MemoryCleaner", "Zwischenspeicher geleert!", NotificationType.INFO, 5);
                    timeHelper.reset();
                });
                cleanThread.start();
            }
        }
    }
}
