package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;

public class NoRotation extends Module {

    public NoRotation() {
        super("NoRotation", Keyboard.KEY_NONE, Category.PLAYER, -1);
    }


    @Override
    public void onEvent(Event event) {
    }
}
