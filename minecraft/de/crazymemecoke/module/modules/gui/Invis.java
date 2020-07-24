package de.crazymemecoke.module.modules.gui;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import org.lwjgl.input.Keyboard;

public class Invis extends Module {
    public Invis() {
        super("Invis", Keyboard.KEY_NONE, Category.GUI, -1);
    }
}
