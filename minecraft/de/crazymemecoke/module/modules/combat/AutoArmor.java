package de.crazymemecoke.module.modules.combat;

import de.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.utils.time.TimeHelper;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {

    public AutoArmor() {
        super("AutoArmor", Keyboard.KEY_NONE, Category.COMBAT, -1);

        Client.getInstance().getSetmgr().rSetting(new Setting("Delay", this, 100, 0, 500, true));
    }

    private int[] bestArmor;
    TimeHelper time = new TimeHelper();

    @Override
    public void onUpdate() {
        double delay = Client.getInstance().getSetmgr().getSettingByName("Delay", this).getValDouble();
        if (getState()) {
            if (mc.thePlayer.capabilities.isCreativeMode
                    || mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof GuiInventory))
                return;

            bestArmor = new int[4];
            for (int i = 0; i < bestArmor.length; i++)
                bestArmor[i] = -1;
            for (int i = 0; i < 36; i++) {
                ItemStack itemstack = mc.thePlayer.inventory.getStackInSlot(i);
                if (itemstack != null && itemstack.getItem() instanceof ItemArmor) {
                    ItemArmor armor = (ItemArmor) itemstack.getItem();
                    if (armor.damageReduceAmount > bestArmor[3 - armor.armorType])
                        bestArmor[3 - armor.armorType] = i;
                }
            }
            for (int i = 0; i < 4; i++) {
                ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(i);
                ItemArmor currentArmor;
                if (itemstack != null && itemstack.getItem() instanceof ItemArmor)
                    currentArmor = (ItemArmor) itemstack.getItem();
                else
                    currentArmor = null;
                ItemArmor bestArmor;
                try {
                    bestArmor = (ItemArmor) mc.thePlayer.inventory.getStackInSlot(this.bestArmor[i]).getItem();
                } catch (Exception e) {
                    bestArmor = null;
                }
                if (bestArmor != null
                        && (currentArmor == null || bestArmor.damageReduceAmount > currentArmor.damageReduceAmount))
                    if (mc.thePlayer.inventory.getFirstEmptyStack() != -1 || currentArmor == null) {
                        if (time.hasReached((long) delay)) {
                            mc.playerController.windowClick(0, 8 - i, 0, 1, mc.thePlayer);
                            mc.playerController.windowClick(0,
                                    this.bestArmor[i] < 9 ? 36 + this.bestArmor[i] : this.bestArmor[i], 0, 1,
                                    Minecraft.getMinecraft().thePlayer);
                            time.reset();
                        }
                    }
            }
        }
    }
}