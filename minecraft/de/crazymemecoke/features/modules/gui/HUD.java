package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
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

        SettingsManager sM = Client.main().setMgr();
        sM.addSetting(new Setting("Design", this, "Ambien", design));
        sM.addSetting(new Setting("Chat Mode", this, "Custom", chatMode));
        sM.addSetting(new Setting("Chat Font", this, "Custom", chatFont));
        sM.addSetting(new Setting("Hit Animation", this, "Stoned", hitAnimation));
        sM.addSetting(new Setting("Item Height", this, 0.0F, -0.35F, 1.5F, false));
        sM.addSetting(new Setting("Hotbar", this, true));
        sM.addSetting(new Setting("ArrayList", this, true));
        sM.addSetting(new Setting("TabGUI", this, true));
        sM.addSetting(new Setting("Watermark", this, true));
        sM.addSetting(new Setting("KeyStrokes", this, true));
        sM.addSetting(new Setting("GUI Animation", this, true));
        sM.addSetting(new Setting("GUI Blur", this, true));
        sM.addSetting(new Setting("Blockhit Animation", this, true));
        sM.addSetting(new Setting("Developer Mode", this, false));
    }

    @Override
    public void onEvent(Event event) {
    }
}
