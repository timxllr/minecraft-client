package com.masterof13fps.features.modules.impl.gui;

import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.utils.NotifyUtil;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.features.modules.Category;
import com.masterof13fps.manager.notificationmanager.NotificationType;

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
