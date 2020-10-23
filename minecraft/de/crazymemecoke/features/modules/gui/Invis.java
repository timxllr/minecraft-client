package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.NotifyUtil;
import org.lwjgl.input.Keyboard;

public class Invis extends Module {
    public Invis() {
        super("Invis", Keyboard.KEY_NONE, Category.GUI, -1);
    }

    @Override
    public void onEnable() {
        NotifyUtil.chat("Benutze \"" + Client.main().getClientPrefix() + "invis\" um");
        NotifyUtil.chat("den Invis-Mode zu deaktivieren!");
    }

    @Override
    public void onEvent(Event event) {
    }
}
