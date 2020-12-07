package com.masterof13fps.utils;

import com.masterof13fps.Client;
import com.masterof13fps.Methods;
import com.masterof13fps.Wrapper;
import com.masterof13fps.manager.notificationmanager.Notification;
import com.masterof13fps.manager.notificationmanager.NotificationManager;
import com.masterof13fps.manager.notificationmanager.NotificationType;
import net.minecraft.util.ChatComponentText;

public class NotifyUtil extends Methods implements Wrapper {

    public static void debug(String msg) {
        System.out.println(methods.getColors().getAnsiPurple() + "[" + methods.getColors().getAnsiCyan() + methods.getTime() + methods.getColors().getAnsiPurple() + "] " + methods.getColors().getAnsiBlue() + Client.main().getClientName() + methods.getColors().getAnsiPurple() + " >> " + methods.getColors().getAnsiRed() + msg + methods.getColors().getAnsiReset());
    }

    public static void chat(String msg) {
        if (Wrapper.mc.thePlayer != null) {
            Wrapper.mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§c" + Client.main().getClientName() + " §7>> §a" + msg));
        }
    }

    public static void notification(String title, String message, NotificationType type, long seconds){
        NotificationManager.show(new Notification(type, title, message, seconds));
    }

}
