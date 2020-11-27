package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Parkour", category = Category.MOVEMENT, description = "Automatically jumps at the end of a block")
public class Parkour extends Module {
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
        if (event instanceof EventUpdate) {
            if (mc.thePlayer.onGround && !mc.thePlayer.isSneaking() && !mc.gameSettings.keyBindSneak.pressed && !mc.gameSettings.keyBindJump.pressed &&
                    mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001)).isEmpty()) {
                mc.thePlayer.jump();
            }
        }
    }
}