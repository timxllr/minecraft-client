package de.crazymemecoke.module.modules.player;

import de.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.inventory.ContainerChest;
import org.lwjgl.input.Keyboard;

public class ChestStealer extends Module {
    TimeHelper time = new TimeHelper();

    public ChestStealer() {
        super("ChestStealer", Keyboard.KEY_NONE, Category.PLAYER, -1);
        Client.getInstance().getSetmgr().rSetting(new Setting("ChestStealer Delay", this, 75, 0, 250, true));
    }

    @Override
    public void onUpdate() {
        long delay = (long) Client.getInstance().getSetmgr().getSettingByName("ChestStealer Delay", this).getValDouble();
        if (getState()) {
            if ((mc.thePlayer.openContainer != null)
                    && ((mc.thePlayer.openContainer instanceof ContainerChest))) {
                ContainerChest container = (ContainerChest) mc.thePlayer.openContainer;
                for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); i++) {
                    if ((container.getLowerChestInventory().getStackInSlot(i) != null)
                            && (time.hasReached(delay))) {
                        mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                        time.reset();
                    }
                }
                if (container.getInventory().isEmpty()) {
                    mc.thePlayer.closeScreen();
                }
            }
        }
    }
}