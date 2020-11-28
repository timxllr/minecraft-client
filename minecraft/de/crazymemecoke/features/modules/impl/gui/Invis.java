package de.crazymemecoke.features.modules.impl.gui;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;

@ModuleInfo(name = "Invis", category = Category.GUI, description = "Makes the entire Client invisible for screenshares")
public class Invis extends Module {
    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Invis aktiviert", "Drücke §cSHIFT + RCONTROL§r um den §6Invis-Modus§r zu §6deaktivieren§r!", NotificationType.INFO, 5);
    }

    @Override
    public void onDisable() {
        NotifyUtil.notification("Invis deaktiviert", "Alle Funktionen sind nun wieder verfügbar!", NotificationType.INFO, 5);
    }

    @Override
    public void onEvent(Event event) {
    }
}
