package de.crazymemecoke.features.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Notify;

public class Toggle extends Command {
	
	String syntax = ".toggle <Module>";

	@Override
	public void execute(String[] args) {
		if (args.length == 1) {
			String modName = args[0];
			for (Module m : Client.main().modMgr().getModules()) {
				if (m.name().equalsIgnoreCase(modName)) {
					if (m.state()) {
						m.setState(false);
					} else {
						if (!m.state()) {
							m.setState(true);
						}
					}
				}
			}
		} else {
			Notify.chat(syntax);
		}
	}

	@Override
	public String getName() {
		return "toggle";
	}

}
