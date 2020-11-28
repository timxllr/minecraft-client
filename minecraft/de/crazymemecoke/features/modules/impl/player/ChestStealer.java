package de.crazymemecoke.features.modules.impl.player;

import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;

@ModuleInfo(name = "ChestStealer", category = Category.PLAYER, description = "Automatically steals all items inside of containers")
public class ChestStealer extends Module {

    Setting delay = new Setting("Delay", this, 75, 0, 250, true);
    Setting autoClose = new Setting("Auto Close", this, true);

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            long delay = (long) Client.main().setMgr().settingByName("Delay", this).getCurrentValue();
            if (((mc.thePlayer.openContainer instanceof ContainerChest))) {
                ContainerChest container = (ContainerChest) mc.thePlayer.openContainer;
                for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); i++) {
                    if ((container.getLowerChestInventory().getStackInSlot(i) != null)
                            && (timeHelper.hasReached(delay))) {
                        mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                        timeHelper.reset();
                    }
                }
                if (isEmpty(container)) {
                    if (Client.main().setMgr().settingByName("Auto Close", this).isToggled()) {
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