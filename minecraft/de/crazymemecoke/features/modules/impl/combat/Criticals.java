package de.crazymemecoke.features.modules.impl.combat;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import net.minecraft.block.material.Material;

@ModuleInfo(name = "Criticals", category = Category.COMBAT, description = "You automatically crit other entities")
public class Criticals extends Module {

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
            if (!mc.thePlayer.isInWater() && !mc.thePlayer.isInsideOfMaterial(Material.lava) && mc.thePlayer.onGround) {
                mc.thePlayer.motionY = 0.2D;
                mc.thePlayer.onGround = false;
            }
            mc.thePlayer.motionY = -0.2D;
        }
    }
}
