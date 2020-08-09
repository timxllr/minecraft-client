package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;

public class Help extends Command {

    String syntax = ".help";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Notify.chat("Alle Befehle:");

            for (Command c : Client.instance().getCommandManager().getCommands()) {
                Notify.chat(Client.instance().getClientPrefix() + c.getName().toLowerCase());
            }
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "help";
    }

}
