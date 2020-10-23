package de.crazymemecoke.features.commands;

import de.crazymemecoke.features.ui.guiscreens.GuiItems;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.NotifyUtil;

public class Items extends Command {

    String syntax = ".items";

    @Override
    public void execute(String[] args) {
        if(args.length == 0){
            mc.displayGuiScreen(new GuiItems(null));
        }else{
            NotifyUtil.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "items";
    }
}
