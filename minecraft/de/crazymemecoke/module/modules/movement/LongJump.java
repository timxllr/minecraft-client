package de.crazymemecoke.module.modules.movement;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.entity.PlayerUtil;
import de.crazymemecoke.utils.time.TimeHelper;
import org.lwjgl.input.Keyboard;

public class LongJump extends Module {
    public LongJump() {
        super("LongJump", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
    }

    private TimeHelper timer = new TimeHelper();
    private boolean jump;


    @Override
    public void onUpdate() {
        if(getState()){
            this.mc.gameSettings.keyBindForward.pressed = false;
            if(this.mc.thePlayer.onGround) {
                this.jump = true;
            }

            if(this.mc.thePlayer.onGround && this.timer.isDelayComplete(500L)) {
                this.mc.thePlayer.motionY = 0.42D;
                PlayerUtil.toFwd(2.3D);
                this.timer.reset();
            } else if(!this.mc.thePlayer.onGround && this.jump) {
                this.mc.thePlayer.motionX = this.mc.thePlayer.motionZ = 0.0D;
                this.jump = false;
            }

        }
    }
}
