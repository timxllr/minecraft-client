package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

@ModuleInfo(name = "NoFall", category = Category.PLAYER, description = "Prevents you from getting fall damage")
public class NoFall extends Module {

    public Setting mode = new Setting("Mode", this, "AAC 1.9.10", new String[] {"AAC 3.3.11", "AAC 3.3.8", "AAC 1.9.10", "Vanilla"});

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
            String mode = Client.main().setMgr().settingByName("Mode", this).getCurrentMode();

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
