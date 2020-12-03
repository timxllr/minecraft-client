package de.crazymemecoke.utils;

import de.crazymemecoke.Client;
import de.crazymemecoke.Wrapper;
import de.crazymemecoke.manager.notificationmanager.Notification;
import de.crazymemecoke.manager.notificationmanager.NotificationManager;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import net.minecraft.util.ChatComponentText;

public class NotifyUtil {

    public static void debug(String msg) {
        System.out.println(Client.main().getClientName() + " >> " + msg);
    }

    public static void chat(String msg) {
        if (Wrapper.mc.thePlayer != null) {
            Wrapper.mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§c" + Client.main().getClientName() + " §7>> §a" + msg));
        }
    }

    public static void notification(String title, String message, NotificationType type, int seconds){
        NotificationManager.show(new Notification(type, title, message, seconds));
    }

}
