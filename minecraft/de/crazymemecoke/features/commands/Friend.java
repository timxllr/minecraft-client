package de.crazymemecoke.features.commands;

import java.util.ArrayList;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;

public class Friend extends Command {

    String syntax = ".friend add <name> | .friend remove <name> | .friend list";
    public static ArrayList<String> friends = new ArrayList<String>();

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                Notify.chat("Deine Freunde:");
                Notify.chat(friends.toString());
            } else {
                Notify.chat(syntax);
            }
        } else {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (!friends.contains(args[1])) {
                        friends.add(args[1]);
                        Notify.chat(args[1] + " wurde zu deiner Freundesliste hinzugefügt!");
                    } else {
                        if (friends.contains(args[1])) {
                            Notify.chat(args[1] + " ist bereits in deiner Freundesliste!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (friends.contains(args[1])) {
                        friends.remove(args[1]);
                        Notify.chat(args[1] + " wurde aus deiner Freundesliste entfernt!");
                    } else {
                        if (!friends.contains(args[1])) {
                            Notify.chat(args[1] + " ist nicht in deiner Freundesliste!");
                        }
                    }
                } else {
                    Notify.chat(syntax);
                }
            } else {
                Notify.chat(syntax);
            }
        }
    }

    @Override
    public String getName() {
        return "Friend";
    }

}
