package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class SlimeJump extends Module {

    double jumpHeight;

    public SlimeJump() {
        super("SlimeJump", Keyboard.KEY_NONE, Category.MOVEMENT);

        Client.main().setMgr().addSetting(new Setting("Height", this, 1.5, 1.1, 10, false));
    }

    @Override
    public void onEvent(Event event) {
        jumpHeight = Client.main().setMgr().settingByName("Height", this).getNum();

        if (event instanceof EventUpdate) {
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                BlockPos BlockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
                if (mc.theWorld.getBlockState(BlockPos).getBlock() == Blocks.slime_block) {
                    EntityPlayerSP thePlayer = mc.thePlayer;
                    thePlayer.motionY += jumpHeight;
                }
            }
        }
    }
}
