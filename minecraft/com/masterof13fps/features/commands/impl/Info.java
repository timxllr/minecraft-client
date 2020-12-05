package com.masterof13fps.features.commands.impl;

import com.masterof13fps.Client;
import com.masterof13fps.features.commands.Command;
import com.masterof13fps.utils.NotifyUtil;
import com.masterof13fps.manager.notificationmanager.NotificationType;

public class Info extends Command {

    String syntax = Client.main().getClientPrefix() + "info";

    public Info() {
        super("info", "information");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            NotifyUtil.chat("Client-Name: " + Client.main().getClientName());
            NotifyUtil.chat("Client-Version: " + Client.main().getClientVersion());
            NotifyUtil.chat("Client-Author: " + Client.main().getClientCoder());
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

}
