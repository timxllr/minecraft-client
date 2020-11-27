package de.crazymemecoke.manager.modulemanager;

import de.crazymemecoke.Client;
import de.crazymemecoke.Methods;
import de.crazymemecoke.features.modules.gui.Invis;
import de.crazymemecoke.features.ui.Interface;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.manager.settingsmanager.SettingsManager;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public abstract class Module extends Methods {

    private String name;
    private String visualName;
    private int bind;
    private final Category category;
    public static Minecraft mc = Minecraft.mc();
    public static SettingsManager s = Client.main().setMgr();
    public boolean enabled;

    public TimeHelper timeHelper = new TimeHelper();

    public Module() {
        ModuleInfo moduleInfo = getClass().getAnnotation(ModuleInfo.class);
        category = moduleInfo.category();
        name = moduleInfo.name();
        visualName = name;
        bind = moduleInfo.bind();
    }

    public String name() {
        return name;
    }

    public String visualName() {
        return visualName;
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
            if (!(this.isCategory(Category.GUI)) && !(Client.main().modMgr().getModule(Invis.class)).state()) {
                NotifyUtil.notification("Modul aktiviert", "§c" + this.name + "§r wurde aktiviert!", NotificationType.INFO, 2);
            }
        } else {
            this.onDisable();
            this.enabled = false;
            if (!(this.isCategory(Category.GUI)) && !(Client.main().modMgr().getModule(Invis.class)).state()) {
                NotifyUtil.notification("Modul deaktiviert", "§c" + this.name + "§r wurde deaktiviert!", NotificationType.INFO, 2);
            }
        }
    }

    public void toggle() {
        this.setState(!this.state());
        System.out.println(this.state());
    }

    public abstract void onToggle();

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onEvent(Event event);

    public void setBind(int bind) {
        if (bind != 0) {
            System.out.println("Keybind of " + name() + " was set to " + Keyboard.getKeyName(bind));
        }
        this.bind = bind;
    }


    public final boolean isCategory(Category s) {
        return s == category;
    }

    public void setDisplayName(String displayName) {
        visualName = displayName;
    }


}
