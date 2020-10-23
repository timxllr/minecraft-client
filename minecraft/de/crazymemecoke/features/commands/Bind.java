package de.crazymemecoke.features.commands;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;

public class Bind extends Command {

    String syntax = ".bind <module> <key> | .bind <module> none | .bind list | .bind clear";

    @Override
    public void execute(String[] args) {
        ModuleManager moduleManager = Client.main().modMgr();
        if (args.length == 2) {
            if (Client.main().modMgr().getByName(args[0]) != null) {
                Module mod = moduleManager.getByName(args[0]);
                if (mod == null) {
                    NotifyUtil.chat("Module §c" + args[0] + "§a nicht gefunden.");
                    return;
                }

                if(args[1].equalsIgnoreCase("none")){
                    mod.setBind(0);
                    NotifyUtil.chat("Module §c" + args[0] + "§a wurde entbunden.");
                    Wrapper.mc.thePlayer.playSound("random.anvil_use", 1f, 1f);
                    return;
                }

                int bind = Keyboard.getKeyIndex(args[1]);

                if (bind == 0) {
                    NotifyUtil.chat("Key §c" + args[1] + "§a nicht gefunden.");
                    return;
                }

                mod.setBind(bind);
                moduleManager.saveBinds();
                NotifyUtil.chat("Module §c" + args[0] + "§a wurde auf §c" + args[1] + "§a gebunden.");
                Wrapper.mc.thePlayer.playSound("random.anvil_use", 1f, 1f);
            } else {
                NotifyUtil.chat(syntax);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                NotifyUtil.chat("Alle Keybinds (Format: MOD : KEY):");
                for (Module mod : Client.main().modMgr().getModules()) {
                    if (!(mod.bind() == 0)) {
                        NotifyUtil.chat(mod.name() + " §8:§a " + Keyboard.getKeyName(mod.bind()));
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