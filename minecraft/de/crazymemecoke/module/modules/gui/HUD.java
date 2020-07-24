package de.crazymemecoke.module.modules.gui;

import de.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HUD extends Module {

    public HUD() {
        super("HUD", Keyboard.KEY_NONE, Category.GUI, Rainbow.rainbowNormal(1, 1).hashCode());

        ArrayList<String> hotbarOptions = new ArrayList<>();
        hotbarOptions.add("Small Hotbar");
        hotbarOptions.add("Big Hotbar");

        ArrayList<String> colorOptions = new ArrayList<>();
        colorOptions.add("Normal");
        colorOptions.add("Candy");

        Client.getInstance().getSetmgr().rSetting(new Setting("Hotbar Design", this, "Big Hotbar", hotbarOptions));
        Client.getInstance().getSetmgr().rSetting(new Setting("Color Scheme", this, "Candy", colorOptions));
        Client.getInstance().getSetmgr().rSetting(new Setting("Hotbar", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("ArrayList", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("TabGUI", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Watermark", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Developer Mode", this, false));
    }

}
