package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;

public class Allow extends Command {
    String syntax = Client.main().getClientPrefix() + "allow <flight/edit> <true/false>";

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("flight")) {
                if (args[1].equalsIgnoreCase("true")) {
                    Wrapper.mc.thePlayer.capabilities.allowFlying = true;
                } else if (args[1].equalsIgnoreCase("false")) {
                    Wrapper.mc.thePlayer.capabilities.allowFlying = false;
                } else {
                    NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
                }
            } else if (args[0].equalsIgnoreCase("edit")) {
                if (args[1].equalsIgnoreCase("true")) {
                    Wrapper.mc.thePlayer.capabilities.allowEdit = true;
                } else if (args[1].equalsIgnoreCase("false")) {
                    Wrapper.mc.thePlayer.capabilities.allowEdit = false;
                } else {
                    NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
                }
            } else {
                NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
            }
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "allow";
    }
}
