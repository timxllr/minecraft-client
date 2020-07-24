package de.crazymemecoke.command.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.command.Command;
import de.crazymemecoke.utils.Notify;

public class Info extends Command {

    String syntax = ".info";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Notify.chatMessage("Client-Name: " + Client.getInstance().getClientName());
            Notify.chatMessage("Client-Version: " + Client.getInstance().getClientVersion());
            Notify.chatMessage("Client-Author: " + Client.getInstance().getClientAuthor());
        } else {
            Notify.chatMessage(syntax);
        }
    }

    @Override
    public String getName() {
        return "Info";
    }

}
