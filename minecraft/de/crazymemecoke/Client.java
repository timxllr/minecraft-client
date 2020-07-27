package de.crazymemecoke;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import de.crazymemecoke.command.CommandManager;
import de.crazymemecoke.module.ModuleManager;
import de.crazymemecoke.notification.Notification;
import de.crazymemecoke.ui.alts.AltManager;
import de.crazymemecoke.ui.fontmanager.FontManager;
import net.minecraft.client.Minecraft;

import java.io.File;

public class Client {

    private final static Client instance = new Client();
    private final Minecraft mc = Minecraft.getMinecraft();

    // Naming Stuff
    private final String clientName = "Lifetime";
    private final String clientVersion = "b1";
    private final String clientAuthor = "CrazyMemeCoke";
    private final String clientPrefix = ".";

    // Paths of Files
    private final String clientBackground = "client/background.jpg";

    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private SettingsManager setmgr;
    private ClickGUI clickgui;
    private FontManager fontManager;
    private File clientDir;
    private Notification notification;

    public void startClient() {
        clientDir = new File(Minecraft.getMinecraft().mcDataDir + "/" + getClientName());
        if (!clientDir.exists()) {
            clientDir.mkdir();
        }

        fontManager = new FontManager();
        fontManager.initFonts();
        setmgr = new SettingsManager();
        moduleManager = new ModuleManager();
        setmgr.loadSettings();
        moduleManager.loadModules();
        moduleManager.loadBinds();
        commandManager = new CommandManager();
        AltManager.loadAlts();
        clickgui = new ClickGUI();
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    public static Client getInstance() {
        return instance;
    }

    public File getClientDir() {
        return clientDir;
    }

    public void onShutdown() {
        setmgr.saveSettings();
        moduleManager.saveModules();
        moduleManager.saveBinds();
        AltManager.saveAlts();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getClientAuthor() {
        return clientAuthor;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Minecraft getMc() {
        return mc;
    }

    public SettingsManager getSetmgr() {
        return setmgr;
    }

    public ClickGUI getClickGui() {
        return clickgui;
    }

    public String getClientPrefix() {
        return clientPrefix;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public Notification getNotification() {
        return notification;
    }
}
