package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HUD extends Module {

    ArrayList<String> design = new ArrayList<>();
    ArrayList<String> arrayListRectMode = new ArrayList<>();
    ArrayList<String> chatMode = new ArrayList<>();
    ArrayList<String> chatFont = new ArrayList<>();
    ArrayList<String> hitAnimation = new ArrayList<>();

    public HUD() {
        super("HUD", Keyboard.KEY_NONE, Category.GUI);

        design.add("Ambien Old");
        design.add("Ambien New");
        design.add("Ambien Newest");
        design.add("Vortex");
        design.add("Suicide");
        design.add("Apinity");
        design.add("Huzuni");
        design.add("Wurst");
        design.add("Nodus");
        design.add("Saint");
        design.add("Icarus Old");
        design.add("Icarus New");
        design.add("Hero");
        design.add("Klientus");
        design.add("Koks");

        chatMode.add("Normal");
        chatMode.add("Custom");

        chatFont.add("Comfortaa");
        chatFont.add("Bauhaus");
        chatFont.add("Exo");

        hitAnimation.add("Normal");
        hitAnimation.add("Stoned");

        Client.main().setMgr().newSetting(new Setting("Design", this, "Ambien", design));
        Client.main().setMgr().newSetting(new Setting("Chat Mode", this, "Custom", chatMode));
        Client.main().setMgr().newSetting(new Setting("Chat Font", this, "Custom", chatFont));
        Client.main().setMgr().newSetting(new Setting("Hit Animation", this, "Stoned", hitAnimation));
        Client.main().setMgr().newSetting(new Setting("Item Height", this, 0.0F, -0.35F, 1.5F, false));
        Client.main().setMgr().newSetting(new Setting("Hotbar", this, true));
        Client.main().setMgr().newSetting(new Setting("ArrayList", this, true));
        Client.main().setMgr().newSetting(new Setting("TabGUI", this, true));
        Client.main().setMgr().newSetting(new Setting("Watermark", this, true));
        Client.main().setMgr().newSetting(new Setting("Target HUD", this, true));
        Client.main().setMgr().newSetting(new Setting("KeyStrokes", this, true));
        Client.main().setMgr().newSetting(new Setting("GUI Animation", this, true));
        Client.main().setMgr().newSetting(new Setting("GUI Blur", this, true));
        Client.main().setMgr().newSetting(new Setting("Blockhit Animation", this, true));
        Client.main().setMgr().newSetting(new Setting("Developer Mode", this, false));
    }

    @Override
    public void onEvent(Event event) {
    }
}
