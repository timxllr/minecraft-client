package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.NotifyUtil;

public class Info extends Command {

    String syntax = ".info";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            NotifyUtil.chat("Client-Name: " + Client.main().getClientName());
            NotifyUtil.chat("Client-Version: " + Client.main().getClientVersion());
            NotifyUtil.chat("Client-Author: " + Client.main().getClientCoder());
        } else {
            NotifyUtil.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "Info";
    }

}
