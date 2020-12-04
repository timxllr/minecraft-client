package com.masterof13fps.features.modules.impl.misc;

import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.utils.NotifyUtil;
import com.masterof13fps.utils.time.TimeHelper;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.manager.eventmanager.impl.EventTick;
import com.masterof13fps.features.modules.Category;
import com.masterof13fps.manager.notificationmanager.NotificationType;

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
