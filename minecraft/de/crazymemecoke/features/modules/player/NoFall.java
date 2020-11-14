package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class NoFall extends Module {

    ArrayList<String> mode = new ArrayList<>();

    public NoFall() {
        super("NoFall", Keyboard.KEY_NONE, Category.PLAYER);

        mode.add("AAC 3.3.11");
        mode.add("AAC 3.3.8");
        mode.add("AAC 1.9.10");
        mode.add("Vanilla");

        Client.main().setMgr().newSetting(new Setting("Mode", this, "AAC 1.9.10", mode));
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            String mode = Client.main().setMgr().settingByName("Mode", this).getMode();

            switch (mode) {
                case "Vanilla": {
                    doVanilla();
                    break;
                }
                case "AAC 1.9.10": {
                    doAAC1910();
                    break;
                }
                case "AAC 3.3.8": {
                    doAAC338();
                    break;
                }
                case "AAC 3.3.11": {
                    doAAC3311();
                    break;
                }
            }
        }
    }

    private void doAAC3311() {
        if (mc.thePlayer.fallDistance > 2.0F) {
            mc.thePlayer.motionX = (mc.thePlayer.motionZ = 0.0D);
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 0.001D, mc.thePlayer.posZ, mc.thePlayer.onGround));
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }

    private void doAAC338() {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.motionY = -9.0D;
        }
    }

    private void doAAC1910() {
        if (!mc.thePlayer.onGround) {
            Minecraft.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            mc.thePlayer.onGround = true;
            mc.thePlayer.fallDistance = 0.0F;
        } else {
            mc.thePlayer.onGround = false;
            mc.thePlayer.onGround = true;
        }
    }

    private void doVanilla() {
        if (mc.thePlayer.fallDistance > 2.0F) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            mc.thePlayer.fallDistance = 0.0F;
        }
    }
}
