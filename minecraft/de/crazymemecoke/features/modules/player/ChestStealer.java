package de.crazymemecoke.features.modules.player;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.events.Event;
import de.crazymemecoke.manager.events.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class ChestStealer extends Module {
    TimeHelper time = new TimeHelper();

    public ChestStealer() {
        super("ChestStealer", Keyboard.KEY_NONE, Category.PLAYER, -1);
        Client.main().setMgr().newSetting(new Setting("Delay", this, 75, 0, 250, true));
        Client.main().setMgr().newSetting(new Setting("Auto Close", this, true));
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            long delay = (long) Client.main().setMgr().settingByName("Delay", this).getNum();
            if (((mc.thePlayer.openContainer instanceof ContainerChest))) {
                ContainerChest container = (ContainerChest) mc.thePlayer.openContainer;
                for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); i++) {
                    if ((container.getLowerChestInventory().getStackInSlot(i) != null)
                            && (time.hasReached(delay))) {
                        mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                        time.reset();
                    }
                }
                if (isEmpty(container)) {
                    if (Client.main().setMgr().settingByName("Auto Close", this).getBool()) {
                        mc.thePlayer.closeScreen();
                    }
                }
            }
        }
    }

    private boolean isEmpty(ContainerChest chest) {
        for (int i = 0; i < chest.inventorySlots.size() - 36; i++) {
            final ItemStack item = chest.getSlot(i).getStack();
            if (item != null) {
                return false;
            }
        }
        return true;
    }
}