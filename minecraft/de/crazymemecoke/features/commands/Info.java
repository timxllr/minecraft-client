package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;

public class Info extends Command {

    String syntax = ".info";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Notify.chat("Client-Name: " + Client.getInstance().getClientName());
            Notify.chat("Client-Version: " + Client.getInstance().getClientVersion());
            Notify.chat("Client-Author: " + Client.getInstance().getClientCoder());
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "Info";
    }

}
