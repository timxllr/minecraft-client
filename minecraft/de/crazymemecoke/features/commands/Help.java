package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.NotifyUtil;

public class Help extends Command {

    String syntax = ".help";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            NotifyUtil.chat("Alle Befehle:");

            for (Command c : Client.main().getCommandManager().getCommands()) {
                NotifyUtil.chat(Client.main().getClientPrefix() + c.getName().toLowerCase());
            }
        } else {
            NotifyUtil.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "help";
    }

}
