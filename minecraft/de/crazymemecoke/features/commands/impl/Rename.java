package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.item.ItemStack;

public class Rename extends Command {

    String syntax = Client.main().getClientPrefix() + "rename <name>";

    @Override
    public void execute(String[] args) {
        if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            NotifyUtil.chat("Nur im Kreativmodus verfügbar!");
        }
        if (args.length == 0) {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        } else {
            if (Wrapper.mc.thePlayer.getHeldItem() == null) {
                NotifyUtil.chat("Halte ein Item in der Hand!");
                return;
            }
            String message = args[0];
            for (int i = 1; i < args.length; i++)
                message += " " + args[i];
            message = message.replace("&", "§").replace("&", "§");
            ItemStack item = Wrapper.mc.thePlayer.inventory.getCurrentItem();
            item.setStackDisplayName(message);
            NotifyUtil.chat("Item umbenannt zu: \"" + message + "\".");
        }
    }

    @Override
    public String getName() {
        return "Rename";
    }

}
