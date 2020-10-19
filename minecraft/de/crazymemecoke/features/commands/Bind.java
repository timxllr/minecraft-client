package de.crazymemecoke.features.commands;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;

public class Bind extends Command {

    String syntax = ".bind <module> <key> | .bind list | .bind clear";

    @Override
    public void execute(String[] args) {
        ModuleManager moduleManager = Client.main().modMgr();
        if (args.length == 2) {
            if (Client.main().modMgr().getByName(args[0]) != null) {
                Module mod = moduleManager.getByName(args[0]);
                if (mod == null) {
                    NotifyUtil.chat("Module " + args[0] + " nicht gefunden.");
                    return;
                }

                int bind = Keyboard.getKeyIndex(args[1]);

                if (bind == 0) {
                    NotifyUtil.chat("Key " + args[1] + " nicht gefunden.");
                    return;
                }

                mod.setBind(bind);
                moduleManager.saveBinds();
                NotifyUtil.chat("Module " + args[0] + " wurde auf " + args[1] + " gebunden.");
                Wrapper.mc.thePlayer.playSound("random.anvil_use", 1f, 1f);
            } else {
                NotifyUtil.chat(syntax);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                NotifyUtil.chat("Alle Keybinds (Format: MOD : KEY):");
                for (Module mod : Client.main().modMgr().getModules()) {
                    if (!(mod.bind() == 0)) {
                        NotifyUtil.chat(mod.name() + " : " + Keyboard.getKeyName(mod.bind()));
                    }
                }
            } else if (args[0].equalsIgnoreCase("clear")) {
                for (Module mod : Client.main().modMgr().getModules()) {
                    mod.setBind(0);
                }
                NotifyUtil.chat("Es wurden alle Keybinds gelöscht.");
            } else {
                NotifyUtil.chat(syntax);
            }
        } else {
            NotifyUtil.chat(syntax);
        }

    }

    @Override
    public String getName() {
        return "Bind";
    }

}