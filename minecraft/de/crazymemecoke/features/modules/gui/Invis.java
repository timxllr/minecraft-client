package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import org.lwjgl.input.Keyboard;

public class Invis extends Module {
    public Invis() {
        super("Invis", Keyboard.KEY_NONE, Category.GUI, -1);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Invis aktiviert", "Benutze §c.invis§r um den §6Invis-Modus§r zu §6deaktivieren§r!", NotificationType.INFO, 5);
    }

    @Override
    public void onDisable() {
        NotifyUtil.notification("Invis deaktiviert", "Alle Funktionen sind nun wieder verfügbar!", NotificationType.INFO, 5);
    }

    @Override
    public void onEvent(Event event) {
    }
}
