package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.features.modules.ModuleManager;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

public class Reload extends Command {

    String syntax = getClientPrefix() + "reload";

    @Override
    public void execute(String[] args) {
        if(args.length == 0){
            reloadClient();
        }else{
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "Reload";
    }
}
