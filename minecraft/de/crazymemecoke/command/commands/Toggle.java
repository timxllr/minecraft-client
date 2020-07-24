package de.crazymemecoke.command.commands;

import de.crazymemecoke.Client;
import de.crazymemecoke.command.Command;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.Notify;

public class Toggle extends Command {
	
	String syntax = ".toggle <Module>";

	@Override
	public void execute(String[] args) {
		if (args.length == 1) {
			String modName = args[0];
			for (Module m : Client.getInstance().getModuleManager().getModules()) {
				if (m.getName().equalsIgnoreCase(modName)) {
					if (m.getState()) {
						m.setState(false);
					} else {
						if (!m.getState()) {
							m.setState(true);
						}
					}
				}
			}
		} else {
			Notify.chatMessage(syntax);
		}
	}

	@Override
	public String getName() {
		return "toggle";
	}

}
