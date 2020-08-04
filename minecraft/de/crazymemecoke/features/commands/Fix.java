package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;

public class Fix extends Command {

    String syntax = ".fix";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Wrapper.mc.thePlayer.motionY = 0.1;
            Notify.chat("Deine PlayerPos wurde gefixed");
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "Fix";
    }

}
