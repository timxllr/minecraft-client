package de.crazymemecoke.manager.modulemanager;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public abstract class Module {

    private final String name;
    private int bind;
    private final Category category;
    public static Minecraft mc = Minecraft.mc();
    public boolean enabled;

    public Module(String name, int bind, Category category, int color) {
        this.name = name;
        this.bind = bind;
        this.category = category;
        setup();
    }

    public String name() {
        return name;
    }

    public int bind() {
        return bind;
    }

    public Category category() {
        return category;
    }

    public boolean state() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setState(boolean state) {
        this.onToggle();
        if (state) {
            this.onEnable();
            this.enabled = true;
            if (!(this.isCategory(Category.GUI))) {
                NotifyUtil.notification("Modul aktiviert", "§c" + this.name + "§r wurde aktiviert!", NotificationType.INFO, 2);
            }
        } else {
            this.onDisable();
            this.enabled = false;
            if (!(this.isCategory(Category.GUI))) {
                NotifyUtil.notification("Modul deaktiviert", "§c" + this.name + "§r wurde deaktiviert!", NotificationType.INFO, 2);
            }
        }
    }

    public void toggle() {
        this.setState(!this.state());
    }

    public void onToggle() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public abstract void onEvent(Event event);

    public void setup() {
    }

    public void setBind(int bind) {
        if (!(bind == 0)) {
            System.out.println("Keybind of " + name() + " was set to: " + Keyboard.getKeyName(bind));
        }
        this.bind = bind;
    }


    public final boolean isCategory(Category s) {
        return s == category;
    }

    public void setDisplayName(String displayName) {
    }


}
