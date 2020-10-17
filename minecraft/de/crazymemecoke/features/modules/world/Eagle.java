package de.crazymemecoke.features.modules.world;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class Eagle extends Module {
    public Eagle() {
        super("Eagle", Keyboard.KEY_NONE, Category.WORLD, -1);
    }

    @Override
    public void onUpdate() {
        if (state()) {
            BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D, mc.thePlayer.posZ);
            if (mc.thePlayer.fallDistance <= 4.0F) {
                mc.gameSettings.keyBindSneak.pressed = mc.theWorld.getBlockState(bp).getBlock() == Blocks.air;
            }

        }
    }
}
