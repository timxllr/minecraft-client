package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class Parkour extends Module {
    public Parkour() {
        super("Parkour", Keyboard.KEY_NONE, Category.MOVEMENT);
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