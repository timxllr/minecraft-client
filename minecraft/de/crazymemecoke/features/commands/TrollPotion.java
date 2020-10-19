package de.crazymemecoke.features.commands;

import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class TrollPotion extends Command{

	String syntax = ".trollpotion";
	String itemName = "§Troll§6Potion";

	@Override
	public void execute(String[] args) {
		if(args.length == 0){
			if(Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null)
			{
				NotifyUtil.chat("Bitte leere den ersten Slot in der Hotbar!");
				return;
			}else if(!Wrapper.mc.thePlayer.capabilities.isCreativeMode)
			{
				NotifyUtil.chat("Nur im Kreativmodus verfügbar!");
				return;
			}
			ItemStack stack = new ItemStack(Items.potionitem);
			stack.setItemDamage(16384);
			NBTTagList effects = new NBTTagList();
			for(int i = 1; i <= 23; i++)
			{
				NBTTagCompound effect = new NBTTagCompound();
				effect.setInteger("Amplifier", Integer.MAX_VALUE);
				effect.setInteger("Duration", Integer.MAX_VALUE);
				effect.setInteger("Id", i);
				effects.appendTag(effect);
			}
			stack.setTagInfo("CustomPotionEffects", effects);
			stack.setStackDisplayName(itemName);
			Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
			NotifyUtil.chat("Du hast eine TrollPotion erhalten!");
		}else{
			NotifyUtil.chat(syntax);
		}
	}

	@Override
	public String getName() {
		return "TrollPotion";
	}

}
