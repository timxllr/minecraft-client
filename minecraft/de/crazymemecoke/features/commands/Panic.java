package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;

public class Panic extends Command {
    @Override
    public void execute(String[] args) {
        for (Module mod : Client.instance().modManager().getModules()) {
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
