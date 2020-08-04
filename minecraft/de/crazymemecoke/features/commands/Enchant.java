package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class Enchant extends Command {

    String syntax = ".enchant <all/hand>";

    @Override
    public void execute(String[] args) {

        if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode)
            Notify.chat("Nur im Kreativmodus verfügbar!");

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("hand")) {
                ItemStack currentItem = Wrapper.mc.thePlayer.inventory.getCurrentItem();
                if (currentItem == null)
                    Notify.chat("Halte ein Item in der Hand!");
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
                Notify.chat("Erfolgreich alle Items verzaubert!");
            } else {
                Notify.chat(syntax);
            }
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "enchant";
    }

}
