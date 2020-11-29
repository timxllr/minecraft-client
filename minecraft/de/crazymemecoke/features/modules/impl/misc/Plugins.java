package de.crazymemecoke.features.modules.impl.misc;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.features.modules.impl.gui.ClickGUI;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S3APacketTabComplete;

@ModuleInfo(name = "Plugins", category = Category.MISC, description = "Finds plugins of a server")
public class Plugins extends Module {

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {
        sendPacket(new C14PacketTabComplete("/"));
        Client.main().modMgr().getModule(Plugins.class).setState(false);
        this.setState(false);
        onDisable();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket) {
            if (((EventPacket) event).getType() == EventPacket.Type.RECEIVE) {
                if ((((EventPacket) event).getPacket() instanceof S3APacketTabComplete)) {
                    S3APacketTabComplete packet = (S3APacketTabComplete) ((EventPacket) event).getPacket();
                    String[] commands = packet.matches();
                    StringBuilder message = new StringBuilder();
                    int size = 0;
                    String[] array;
                    int length = (array = commands).length;
                    for (int i = 0; i < length; i++) {
                        String command = array[i];
                        String pluginName = command.split(":")[0].substring(1);
                        if ((!message.toString().contains(pluginName)) && (command.contains(":")) && (!pluginName.equalsIgnoreCase("minecraft")) &&
                                (!pluginName.equalsIgnoreCase("bukkit"))) {
                            size++;
                            if (message.length() == 0) {
                                message.append(pluginName);
                            } else {
                                message.append("§§8, §§7").append(pluginName);
                            }
                        }
                    }
                    if (message.length() > 0) {
                        NotifyUtil.notification("Plugins gefunden!", "Es wurden Plugins gefunden! Siehe im Chat nach für weitere Informationen!", NotificationType.INFO, 5);
                        NotifyUtil.chat("Plugins (" + size + "): §7" + message);
                    } else {
                        NotifyUtil.notification("Keine Plugins gefunden!", "Es wurden keine Plugins gefunden!", NotificationType.INFO, 5);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
