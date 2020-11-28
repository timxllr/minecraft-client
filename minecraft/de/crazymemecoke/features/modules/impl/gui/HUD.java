package de.crazymemecoke.features.modules.impl.gui;

import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;

@ModuleInfo(name = "HUD", category = Category.GUI, description = "Shows a interface with all visual features of the Client")
public class HUD extends Module {

    public Setting design = new Setting("Design", this, "Ambien Newest", new String[] {"Ambien Old", "Ambien New", "Ambien Newest", "Vortex", "Suicide", "Apinity", "Huzuni",
    "Wurst", "Nodus", "Saint", "Icarus Old", "Icarus New", "Hero", "Klientus", "Koks"});
    public Setting chatMode = new Setting("Chat Mode", this, "Custom", new String[] {"Normal", "Custom"});
    public Setting chatFont = new Setting("Chat Font", this, "Comfortaa", new String[] {"Comfortaa", "Bauhaus", "Exo"});
    public Setting hitAnimation = new Setting("Hit Animation", this, "Stoned", new String[] {"Normal", "Stoned"});
    public Setting itemHeight = new Setting("Item Height", this, 0.0F, -0.35F, 1.5F, false);
    public Setting rainbowOffset = new Setting("Rainbow Offset", this, 200, 50, 1000, true);
    public Setting rainbowSpeed = new Setting("Rainbow Speed", this, 10000, 500, 20000, true);
    public Setting rainbowSaturation = new Setting("Rainbow Saturation", this, 1, 1, 5, true);
    public Setting rainbowBrightness = new Setting("Rainbow Brightness", this, 1, 1, 5, true);
    public Setting hotbar = new Setting("Hotbar", this, true);
    public Setting arrayList = new Setting("ArrayList", this, true);
    public Setting tabGui = new Setting("TabGUI", this, true);
    public Setting watermark = new Setting("Watermark", this, true);
    public Setting keyStrokes = new Setting("KeyStrokes", this, true);
    public Setting guiAnimation = new Setting("GUI Animation", this, true);
    public Setting guiBlur = new Setting("GUI Blur", this, true);
    public Setting blockhitAnimation = new Setting("Blockhit Animation", this, true);
    public Setting developerMode = new Setting("Developer Mode", this, false);

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
    }
}
