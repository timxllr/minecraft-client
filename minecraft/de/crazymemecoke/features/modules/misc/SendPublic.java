package de.crazymemecoke.features.modules.misc;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventChat;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import net.minecraft.network.play.client.C01PacketChatMessage;

@ModuleInfo(name = "SendPublic", category = Category.MISC, description = "A module to send some stuff public")
public class SendPublic extends Module {

    public Setting modToggle = new Setting("Mod Toggle", this, false);
    public Setting atAll = new Setting("At All", this, true);

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventChat) {
            if (atAll.isToggled() && !(((EventChat) event).getMessage().startsWith(Client.main().getClientPrefix()))) {
                event.setCanceled(true);
                sendPacket(new C01PacketChatMessage("@a " + ((EventChat) event).getMessage()));
            }
        }
    }
}
