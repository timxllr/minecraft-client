package de.crazymemecoke.module.modules.combat;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import net.minecraft.block.material.Material;
import org.lwjgl.input.Keyboard;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", Keyboard.KEY_NONE, Category.COMBAT, -1);
    }

    public void onUpdate() {
        if (getState()) {
            if (!mc.thePlayer.isInWater() && !mc.thePlayer.isInsideOfMaterial(Material.lava) && mc.thePlayer.onGround) {
                mc.thePlayer.motionY = 0.2D;
                mc.thePlayer.onGround = false;
            }
            mc.thePlayer.motionY = -0.2D;
        }
    }

}
