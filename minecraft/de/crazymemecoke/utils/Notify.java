package de.crazymemecoke.utils;

import de.crazymemecoke.Client;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Notify {

    public static void consoleMessage(String msg) {
        System.out.println(Client.getInstance().getClientName() + " >> " + msg);
    }

    public static void chatMessage(String msg) {
        if (Wrapper.mc.thePlayer != null) {
            Wrapper.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent) new ChatComponentText(
                    "§c" + Client.getInstance().getClientName() + " §7>> §a" + msg));
        }
    }

}
