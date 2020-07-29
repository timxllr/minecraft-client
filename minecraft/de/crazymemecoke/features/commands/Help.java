package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;

public class Help extends Command {

    String syntax = ".help";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Notify.chatMessage("Alle Befehle:");

            for (Command c : Client.getInstance().getCommandManager().getCommands()) {
                Notify.chatMessage(Client.getInstance().getClientPrefix() + c.getName().toLowerCase());
            }
        } else {
            Notify.chatMessage(syntax);
        }
    }

    @Override
    public String getName() {
        return "help";
    }

}
