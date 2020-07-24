package de.crazymemecoke.command.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.command.Command;
import de.crazymemecoke.module.Module;

public class Panic extends Command {
    @Override
    public void execute(String[] args) {
        for (Module mod : Client.getInstance().getModuleManager().getModules()) {
            if (!(mod.getName().equalsIgnoreCase("HUD") || mod.getName().equalsIgnoreCase("ClickGUI"))) {
                if (mod.getState()) {
                    mod.toggleModule();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "panic";
    }
}
