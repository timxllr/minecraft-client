package de.crazymemecoke.manager.modulemanager;

import de.crazymemecoke.utils.events.eventapi.EventManager;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.notificationmanager.Notification;
import de.crazymemecoke.manager.notificationmanager.NotificationManager;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Module {

    private final String name;
    private int bind;
    private final Category category;
    private boolean isEnabled;
    public static Minecraft mc = Minecraft.getMinecraft();
    public boolean enabled;
    private String displayName;

    public Module(String name, int bind, Category category, int color) {
        this.name = name;
        this.bind = bind;
        this.category = category;
        setup();
    }

    public String getName() {
        return name;
    }

    public int getBind() {
        return bind;
    }

    public Category getCategory() {
        return category;
    }

    public boolean getState() {
        return isEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setState(boolean state) {
        this.onToggle();
        if (state) {
            this.onEnable();
            this.isEnabled = true;
            if (!(Client.instance().modManager().getByName("Invis").getState())) {
                if (!(getName().equalsIgnoreCase("ClickGUI")) && !(getName().equalsIgnoreCase("Invis"))) {
                    if (Client.instance().getSetmgr().getSettingByName("Notifications", Client.instance().modManager().getByName("HUD")).getBool()) {
                        NotificationManager.show(new Notification(NotificationType.INFO, "§7[§a+§7] §6" + getName(), "§6Module §aaktiviert", 2));
                    }
                }
            }
        } else {
            this.onDisable();
            this.isEnabled = false;
            if (!(Client.instance().modManager().getByName("Invis").getState())) {
                if (!(getName().equalsIgnoreCase("ClickGUI")) && !(getName().equalsIgnoreCase("Invis"))) {
                    if (Client.instance().getSetmgr().getSettingByName("Notifications", Client.instance().modManager().getByName("HUD")).getBool()) {
                        NotificationManager.show(new Notification(NotificationType.INFO, "§7[§c-§7] §6" + getName(), "§6Module §cdeaktiviert", 2));
                    }
                }
            }
        }
    }

    public void toggleModule() {
        this.setState(!this.getState());
    }

    public void onEnable1() {
        EventManager.register(this);
    }

    public void onToggle() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onRender() {
    }

    public void setup() {
    }

    public void onPreMotionUpdate() {
    }

    public void setBind(int bind) {
        if (!(bind == 0)) {
            System.out.println("Keybind of " + getName() + " was set to: " + Keyboard.getKeyName(bind));
        }
        this.bind = bind;
    }

    public void onPostMotionUpdate() {
    }

    public final boolean isCategory(Category s) {
        return s == category;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


}
