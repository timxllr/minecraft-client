package de.crazymemecoke.command;

import de.crazymemecoke.Client;
import de.crazymemecoke.command.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {

    public List<Command> commands = new ArrayList<Command>();

    public CommandManager() {
        this.commands.add(new KillerPotion());
        this.commands.add(new TrollPotion());
        this.commands.add(new CrashChest());
        this.commands.add(new Enchant());
        this.commands.add(new Toggle());
        this.commands.add(new Rename());
        this.commands.add(new Friend());
        this.commands.add(new Crash());
        this.commands.add(new Panic());
        this.commands.add(new Allow());
        this.commands.add(new Bind());
        this.commands.add(new Help());
        this.commands.add(new Info());
        this.commands.add(new Fix());
        this.commands.add(new T());
    }

    public boolean execute(String text) {
        if (!text.startsWith(Client.getInstance().getClientPrefix())) {
            return false;
        }

        text = text.substring(1);
        String[] arguments = text.split(" ");
        for (Command cmd : this.commands) {
            if (cmd.getName().equalsIgnoreCase(arguments[0])) {
                String[] args = Arrays.copyOfRange(arguments, 1, arguments.length);
                cmd.execute(args);
                return true;
            }
        }
        return false;
    }

    public List<Command> getCommands() {
        return commands;
    }
}