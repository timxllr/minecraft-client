package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import org.lwjgl.input.Keyboard;

public class NoEXP extends Module {
    public NoEXP() {
        super("NoEXP", Keyboard.KEY_NONE, Category.RENDER, -1);
    }
}
