package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InventoryMove extends Module {

    public InventoryMove() {
        super("InventoryMove", Keyboard.KEY_NONE, Category.MOVEMENT);

        Client.main().setMgr().newSetting(new Setting("Camera Speed", this, 3, 0.1, 5, false));
    }


    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiEditSign)) {
                KeyBinding[] moveKeys = {mc.gameSettings.keyBindForward, mc.gameSettings.keyBindSprint,
                        mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight,
                        mc.gameSettings.keyBindJump};
                KeyBinding[] array;
                int length = (array = moveKeys).length;
                for (int i = 0; i < length; i++) {
                    KeyBinding bind = array[i];
                    KeyBinding.setKeyBindState(bind.getKeyCode(), Keyboard.isKeyDown(bind.getKeyCode()));
                }

                double camera_speed = Client.main().setMgr().settingByName("Camera Speed", this).getNum();

                if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mc.thePlayer.rotationYaw -= camera_speed;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mc.thePlayer.rotationYaw += camera_speed;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mc.thePlayer.rotationPitch += camera_speed;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mc.thePlayer.rotationPitch -= camera_speed;
                }
            }
        }
    }
}