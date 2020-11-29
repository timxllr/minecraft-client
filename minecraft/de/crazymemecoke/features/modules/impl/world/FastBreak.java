package de.crazymemecoke.features.modules.impl.world;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventTick;

@ModuleInfo(name = "FastBreak", category = Category.WORLD, description = "Breaks blocks faster than usual")
public class FastBreak extends Module {

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventTick){
            if (mc.playerController.curBlockDamageMP > 0.8F) {
                mc.playerController.curBlockDamageMP = 1.0F;
            }
            mc.playerController.blockHitDelay = 0;
        }
    }
}
