package de.crazymemecoke.command.commands;

import java.util.ArrayList;

import de.crazymemecoke.command.Command;
import de.crazymemecoke.utils.Notify;

public class Friend extends Command {

    String syntax = ".friend add <name> | .friend remove <name> | .friend list";
    public static ArrayList<String> Friend = new ArrayList<String>();

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                Notify.chatMessage("Deine Freunde:");
                Notify.chatMessage(Friend.toString());
            } else {
                Notify.chatMessage(syntax);
            }
        } else {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (!Friend.contains(args[1])) {
                        Friend.add(args[1]);
                        Notify.chatMessage(args[1] + " wurde zu deiner Freundesliste hinzugefügt!");
                    } else {
                        if (Friend.contains(args[1])) {
                            Notify.chatMessage(args[1] + " ist bereits in deiner Freundesliste!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (Friend.contains(args[1])) {
                        Friend.remove(args[1]);
                        Notify.chatMessage(args[1] + " wurde aus deiner Freundesliste entfernt!");
                    } else {
                        if (!Friend.contains(args[1])) {
                            Notify.chatMessage(args[1] + " ist nicht in deiner Freundesliste!");
                        }
                    }
                } else {
                    Notify.chatMessage(syntax);
                }
            } else {
                Notify.chatMessage(syntax);
            }
        }
    }

    @Override
    public String getName() {
        return "Friend";
    }

}
