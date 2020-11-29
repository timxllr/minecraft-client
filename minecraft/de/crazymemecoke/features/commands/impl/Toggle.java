package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

public class Toggle extends Command {
	
	String syntax = Client.main().getClientPrefix() + "t <Module>";

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
			NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
		}
	}

	@Override
	public String getName() {
		return "t";
	}

}
