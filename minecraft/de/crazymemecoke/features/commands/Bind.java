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
        ModuleManager moduleManager = Client.instance().modManager();
        if (args.length == 2) {
            if (Client.instance().modManager().getByName(args[0]) != null) {
                Module mod = moduleManager.getByName(args[0]);
                if (mod == null) {
                    Notify.chat("Module " + args[0] + " nicht gefunden.");
                    return;
                }

                int bind = Keyboard.getKeyIndex(args[1]);

                if (bind == 0) {
                    Notify.chat("Key " + args[1] + " nicht gefunden.");
                    return;
                }

                mod.setBind(bind);
                moduleManager.saveBinds();
                Notify.chat("Module " + args[0] + " wurde auf " + args[1] + " gebunden.");
                Wrapper.mc.thePlayer.playSound("random.anvil_use", 1f, 1f);
            } else {
                Notify.chat(syntax);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                Notify.chat("Alle Keybinds (Format: MOD : KEY):");
                for (Module mod : Client.instance().modManager().getModules()) {
                    if (!(mod.getBind() == 0)) {
                        Notify.chat(mod.getName() + " : " + Keyboard.getKeyName(mod.getBind()));
                    }
                }
            } else if (args[0].equalsIgnoreCase("clear")) {
                for (Module mod : Client.instance().modManager().getModules()) {
                    mod.setBind(0);
                }
                Notify.chat("Es wurden alle Keybinds gelöscht.");
            } else {
                Notify.chat(syntax);
            }
        } else {
            Notify.chat(syntax);
        }

    }

    @Override
    public String getName() {
        return "Bind";
    }

}