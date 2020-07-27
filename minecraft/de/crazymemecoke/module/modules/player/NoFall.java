package de.crazymemecoke.module.modules.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.ui.clickgui.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {

    ArrayList<String> mode = new ArrayList<>();

    public NoFall() {
        super("NoFall", Keyboard.KEY_NONE, Category.PLAYER, Rainbow.rainbow(1, 1).hashCode());

        mode.add("AAC 1.9.10");
        mode.add("NCP");

        Client.getInstance().getSetmgr().rSetting(new Setting("Mode", this, "AAC 1.9.10", mode));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            if (Client.getInstance().getSetmgr().getSettingByName("Mode", this).getValString()
                    .equalsIgnoreCase("AAC 1.9.10")) {
                if (!this.mc.thePlayer.onGround) {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                    this.mc.thePlayer.onGround = true;
                    this.mc.thePlayer.fallDistance = 0.0F;
                } else {
                    this.mc.thePlayer.onGround = false;
                    this.mc.thePlayer.onGround = true;
                }
            } else if (Client.getInstance().getSetmgr().getSettingByName("Mode", this).getValString()
                    .equalsIgnoreCase("NCP")) {
            }
        }
    }

}
