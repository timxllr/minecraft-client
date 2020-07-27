package de.crazymemecoke.module.modules.gui;

import de.crazymemecoke.ui.clickgui.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI, Rainbow.rainbow(1, 1).hashCode());
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<>();
        options.add("JellyLike");
        options.add("New");
        Client.getInstance().getSetmgr().rSetting(new Setting("Design", this, "New", options));
        Client.getInstance().getSetmgr().rSetting(new Setting("Sound", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Client.getInstance().getClickGui());
        super.onDisable();
    }

}

/*package de.crazymemecoke.module.modules.gui;

import de.crazymemecoke.ui.clickgui.CSGOGui;
import org.lwjgl.input.Keyboard;
import de.crazymemecoke.ui.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI, Rainbow.rainbow(1, 1).hashCode());
    }

    @Override
    public void setup() {
        Client.getInstance().getSetmgr().rSetting(new Setting("CSGuiOutline", this, true));
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new CSGOGui());
        super.onDisable();
    }

}
*/