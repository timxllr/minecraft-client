package de.crazymemecoke.features.commands;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;

public class Bind extends Command {

    String syntax = ".bind <module> <key> | .bind list | .bind clear";

    @Override
    public void execute(String[] args) {
        ModuleManager moduleManager = Client.getInstance().getModuleManager();
        if (args.length == 2) {
            if (Client.getInstance().getModuleManager().getModByName(args[0]) != null) {
                Module mod = moduleManager.getModByName(args[0]);
                if (mod == null) {
                    Notify.chatMessage("Module " + args[0] + " nicht gefunden.");
                    return;
                }

                int bind = Keyboard.getKeyIndex(args[1]);

                if (bind == 0) {
                    Notify.chatMessage("Key " + args[1] + " nicht gefunden.");
                    return;
                }

                mod.setBind(bind);
                moduleManager.saveBinds();
                Notify.chatMessage("Module " + args[0] + " wurde auf " + args[1] + " gebunden.");
                Wrapper.mc.thePlayer.playSound("random.anvil_use", 1f, 1f);
            } else {
                Notify.chatMessage(syntax);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                Notify.chatMessage("Alle Keybinds (Format: MOD : KEY):");
                for (Module mod : Client.getInstance().getModuleManager().getModules()) {
                    if (!(mod.getBind() == 0)) {
                        Notify.chatMessage(mod.getName() + " : " + Keyboard.getKeyName(mod.getBind()));
                    }
                }
            } else if (args[0].equalsIgnoreCase("clear")) {
                for (Module mod : Client.getInstance().getModuleManager().getModules()) {
                    mod.setBind(0);
                }
                Notify.chatMessage("Es wurden alle Keybinds gelöscht.");
            } else {
                Notify.chatMessage(syntax);
            }
        } else {
            Notify.chatMessage(syntax);
        }

    }

    @Override
    public String getName() {
        return "Bind";
    }

}