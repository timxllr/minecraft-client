package com.masterof13fps.features.commands.impl;

import com.masterof13fps.Client;
import com.masterof13fps.features.commands.Command;
import com.masterof13fps.utils.NotifyUtil;
import com.masterof13fps.manager.notificationmanager.NotificationType;

public class Help extends Command {

    String syntax = Client.main().getClientPrefix() + "help";

    public Help() {
        super("help", "helpme");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            NotifyUtil.chat("Alle Befehle:");

            for (Command c : Client.main().getCommandManager().getCommands()) {
                NotifyUtil.chat(Client.main().getClientPrefix() + c.getName().toLowerCase());
            }
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

}
