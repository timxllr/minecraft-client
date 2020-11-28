package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Say extends Command {

    String syntax = Client.main().getClientPrefix() + "say <Message>";

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            final StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(arg.concat(" "));
            }
            mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message.toString()));
        }else{
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "say";
    }
}
