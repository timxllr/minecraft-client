package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.Wrapper;

public class Fix extends Command {

    String syntax = Client.main().getClientPrefix() + "fix";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Wrapper.mc.thePlayer.motionY = 0.1;
            NotifyUtil.notification("Befehl ausgeführt", "Deine PlayerPos wurde gefixed", NotificationType.INFO, 5);
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "Fix";
    }

}
