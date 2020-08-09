package de.crazymemecoke.features.modules.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {

    ArrayList<String> mode = new ArrayList<>();

    public NoFall() {
        super("NoFall", Keyboard.KEY_NONE, Category.PLAYER, -1);

        mode.add("AAC 1.9.10");
        mode.add("NCP");

        Client.instance().getSetmgr().rSetting(new Setting("Mode", this, "AAC 1.9.10", mode));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            if (Client.instance().getSetmgr().getSettingByName("Mode", this).getMode()
                    .equalsIgnoreCase("AAC 1.9.10")) {
                if (!this.mc.thePlayer.onGround) {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                    this.mc.thePlayer.onGround = true;
                    this.mc.thePlayer.fallDistance = 0.0F;
                } else {
                    this.mc.thePlayer.onGround = false;
                    this.mc.thePlayer.onGround = true;
                }
            } else if (Client.instance().getSetmgr().getSettingByName("Mode", this).getMode()
                    .equalsIgnoreCase("NCP")) {
            }
        }
    }

}
