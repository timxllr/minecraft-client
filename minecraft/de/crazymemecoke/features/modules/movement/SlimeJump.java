package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class SlimeJump extends Module {
    public SlimeJump() {
        super("SlimeJump", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    @Override
    public void onUpdate() {
        if (state()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                BlockPos BlockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
                if (mc.theWorld.getBlockState(BlockPos).getBlock() == Blocks.slime_block) {
                    EntityPlayerSP thePlayer = mc.thePlayer;
                    thePlayer.motionY += 1.5;
                }
            }
        }
    }
}
