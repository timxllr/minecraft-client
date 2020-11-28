package de.crazymemecoke.features.commands.impl;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Command;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class Enchant extends Command {

    String syntax = Client.main().getClientPrefix() + "enchant <all/hand>";

    @Override
    public void execute(String[] args) {

        if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode)
            NotifyUtil.chat("Nur im Kreativmodus verfügbar!");

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("hand")) {
                ItemStack currentItem = Wrapper.mc.thePlayer.inventory.getCurrentItem();
                if (currentItem == null)
                    NotifyUtil.chat("Halte ein Item in der Hand!");
                for (Enchantment enchantment : Enchantment.enchantmentsList)
                    try {
                        if (enchantment == Enchantment.silkTouch)
                            continue;
                        currentItem.addEnchantment(enchantment, 127);
                    } catch (Exception e) {

                    }
            } else if (args[0].equalsIgnoreCase("all")) {
                int items = 0;
                for (int i = 0; i < 40; i++) {
                    ItemStack currentItem =
                            Wrapper.mc.thePlayer.inventory.getStackInSlot(i);
                    if (currentItem == null)
                        continue;
                    items++;
                    for (Enchantment enchantment : Enchantment.enchantmentsList)
                        try {
                            if (enchantment == Enchantment.silkTouch)
                                continue;
                            currentItem.addEnchantment(enchantment, 127);
                        } catch (Exception e) {

                        }
                }
                NotifyUtil.chat("Erfolgreich alle Items verzaubert!");
            } else {
                NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
            }
        } else {
            NotifyUtil.notification("Falscher Syntax!", "Nutze §c" + syntax + "§r!", NotificationType.ERROR, 5);
        }
    }

    @Override
    public String getName() {
        return "enchant";
    }

}
