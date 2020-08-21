package de.crazymemecoke.features.modules.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HUD extends Module {

    ArrayList<String> design = new ArrayList<>();

    public HUD() {
        super("HUD", Keyboard.KEY_NONE, Category.GUI, -1);
        ArrayList<String> arrayListRectMode = new ArrayList<>();

        arrayListRectMode.add("Left");
        arrayListRectMode.add("Right");
        arrayListRectMode.add("None");

        design.add("Ambien");
        design.add("Vortex");
        design.add("Suicide");
        design.add("Apinity");
        design.add("Huzuni");
        design.add("Wurst");
        design.add("Nodus");
        design.add("Saint");
        design.add("Icarus Old");
        design.add("Icarus New");

        Client.instance().setMgr().newSetting(new Setting("Design", this, "Ambien", design));
        Client.instance().setMgr().newSetting(new Setting("ArrayList Rect Mode", this, "Left", arrayListRectMode));
        Client.instance().setMgr().newSetting(new Setting("Hotbar", this, true));
        Client.instance().setMgr().newSetting(new Setting("ArrayList", this, true));
        Client.instance().setMgr().newSetting(new Setting("ArrayList Background", this, true));
        Client.instance().setMgr().newSetting(new Setting("TabGUI", this, true));
        Client.instance().setMgr().newSetting(new Setting("Watermark", this, true));
        Client.instance().setMgr().newSetting(new Setting("Target HUD", this, true));
        Client.instance().setMgr().newSetting(new Setting("KeyStrokes", this, true));
        Client.instance().setMgr().newSetting(new Setting("Developer Mode", this, false));
    }

}
