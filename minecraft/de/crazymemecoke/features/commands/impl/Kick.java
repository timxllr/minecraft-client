package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Kick extends Command {

    String syntax = getClientPrefix() + "kick";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            sendPacket(new C03PacketPlayer.C05PacketPlayerLook(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, false));
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "Kick";
    }
}
