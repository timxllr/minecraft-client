package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;

public class Panic extends Command {
    @Override
    public void execute(String[] args) {
        for (Module mod : Client.main().modMgr().getModules()) {
            if (!(mod.name().equalsIgnoreCase("HUD") || mod.name().equalsIgnoreCase("ClickGUI"))) {
                if (mod.state()) {
                    mod.toggle();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "panic";
    }
}
