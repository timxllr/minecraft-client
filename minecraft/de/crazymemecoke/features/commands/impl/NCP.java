package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

public class NCP extends Command {

    String syntax = Client.main().getClientPrefix() + "ncp <Name>";

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            sendChatMessage("/testncp input " + args[0]);
            NotifyUtil.notification("Input gesetzt!", "Falls TestNCP auf diesem Server vorhanden ist, wurde dein Input auf \"" + args[0] + "\" gesetzt!", NotificationType.INFO, 5);
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "NCP";
    }
}
