package de.crazymemecoke.utils;

import de.crazymemecoke.Client;
import net.minecraft.util.ChatComponentText;

public class Notify {

    public static void debug(String msg) {
        System.out.println(Client.main().getClientName() + " >> " + msg);
    }

    public static void chat(String msg) {
        if (Wrapper.mc.thePlayer != null) {
            Wrapper.mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§c" + Client.main().getClientName() + " §7>> §a" + msg));
        }
    }

}
