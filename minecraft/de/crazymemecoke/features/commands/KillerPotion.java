package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class KillerPotion extends Command {

    String syntax = ".killerpotion";
    String itemName = "§cKiller§6Potion";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
                Notify.chat("Bitte leere den ersten Slot in der Hotbar!");
                return;
            } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
                Notify.chat("Nur in Kreativmodus verfügbar!");
                return;
            }
            ItemStack stack = new ItemStack(Items.potionitem);
            stack.setItemDamage(16384);
            NBTTagList effects = new NBTTagList();
            NBTTagCompound effect = new NBTTagCompound();
            effect.setInteger("Amplifier", 125);
            effect.setInteger("Duration", 2000);
            effect.setInteger("Id", 6);
            effects.appendTag(effect);
            stack.setTagInfo("CustomPotionEffects", effects);
            stack.setStackDisplayName(itemName);
            Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
            Notify.chat("Du hast eine KillerPotion erhalten!");
        } else {
            Notify.chat(syntax);
        }
    }

    @Override
    public String getName() {
        return "KillerPotion";
    }

}
