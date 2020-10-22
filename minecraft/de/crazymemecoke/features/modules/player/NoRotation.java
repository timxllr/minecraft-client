package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class NoRotation extends Module{

	public NoRotation(){
		super("NoRotation", Keyboard.KEY_NONE, Category.PLAYER, -1);
	}


	@Override
	public void onEvent(Event event) {
		if(event instanceof EventPacket) {
			if(((EventPacket) event).getType() == EventPacket.Type.RECEIVE) {
				if (Client.main().setMgr().settingByName("Mode", this).getMode()
						.equalsIgnoreCase("AAC 1.9.10")) {
					if (!this.mc.thePlayer.onGround) {
						Minecraft.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
						this.mc.thePlayer.onGround = true;
						this.mc.thePlayer.fallDistance = 0.0F;
					} else {
						this.mc.thePlayer.onGround = false;
						this.mc.thePlayer.onGround = true;
					}
				} else if (Client.main().setMgr().settingByName("Mode", this).getMode()
						.equalsIgnoreCase("NCP")) {
				}
			}
		}
	}
}
