package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

public class Help extends Command {

    String syntax = Client.main().getClientPrefix() + "help";

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

    @Override
    public String getName() {
        return "help";
    }

}
