package de.crazymemecoke.module.modules.world;

import com.darkmagician6.eventapi.EventTarget;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.events.UpdateEvent;
import net.minecraft.item.ItemBlock;
import org.lwjgl.input.Keyboard;

public class Tower extends Module {
    public Tower() {
        super("Tower", Keyboard.KEY_NONE, Category.WORLD, -1);
    }

    public void onDisable() {
        mc.rightClickDelayTimer = 6;
        mc.gameSettings.keyBindUseItem.pressed = false;
    }

    @EventTarget
    public void onUpdate(UpdateEvent e) {
        if (getState()) {
            if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
                mc.thePlayer.rotationYaw = 90.0f;
                mc.thePlayer.rotationPitch = 90.0f;
                mc.rightClickDelayTimer = 0;
                mc.thePlayer.motionY = 0.5;
                mc.gameSettings.keyBindUseItem.pressed = true;
            }
        }
    }
}