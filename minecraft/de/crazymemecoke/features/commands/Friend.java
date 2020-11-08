package de.crazymemecoke.features.commands;

import java.util.ArrayList;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

public class Friend extends Command {

    String syntax = Client.main().getClientPrefix() + "friend add <name> / remove <name> / list";
    public static ArrayList<String> friends = new ArrayList<String>();

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                NotifyUtil.chat("Deine Freunde:");
                NotifyUtil.chat(friends.toString());
            } else {
                NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
            }
        } else {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (!friends.contains(args[1])) {
                        friends.add(args[1]);
                        NotifyUtil.chat(args[1] + " wurde zu deiner Freundesliste hinzugefügt!");
                    } else {
                        if (friends.contains(args[1])) {
                            NotifyUtil.chat(args[1] + " ist bereits in deiner Freundesliste!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (friends.contains(args[1])) {
                        friends.remove(args[1]);
                        NotifyUtil.chat(args[1] + " wurde aus deiner Freundesliste entfernt!");
                    } else {
                        if (!friends.contains(args[1])) {
                            NotifyUtil.chat(args[1] + " ist nicht in deiner Freundesliste!");
                        }
                    }
                } else {
                    NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
                }
            } else {
                NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
            }
        }
    }

    @Override
    public String getName() {
        return "Friend";
    }

}
