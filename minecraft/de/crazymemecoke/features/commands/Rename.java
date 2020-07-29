package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.item.ItemStack;

public class Rename extends Command {

    String syntax = ".rename <name>";

    @Override
    public void execute(String[] args) {
        if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            Notify.chatMessage("Nur im Kreativmodus verfügbar!");
        }
        if (args.length == 0) {
            Notify.chatMessage(syntax);
        } else {
            if (Wrapper.mc.thePlayer.getHeldItem() == null) {
                Notify.chatMessage("Halte ein Item in der Hand!");
                return;
            }
            String message = args[0];
            for (int i = 1; i < args.length; i++)
                message += " " + args[i];
            message = message.replace("&", "§").replace("&", "§");
            ItemStack item = Wrapper.mc.thePlayer.inventory.getCurrentItem();
            item.setStackDisplayName(message);
            Notify.chatMessage("Item umbenannt zu: \"" + message + "\".");
        }
    }

    @Override
    public String getName() {
        return "Rename";
    }

}
