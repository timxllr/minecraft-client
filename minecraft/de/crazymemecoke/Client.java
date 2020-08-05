package de.crazymemecoke;

import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.clickguimanager.clickgui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.commandmanager.CommandManager;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.manager.notificationmanager.Notification;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.features.ui.guiscreens.GuiFirstUse;
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
    private final String clientBackground = "textures/client/background.jpg";
    private final String clientChangelog = "https://github.com/RealFantaCoke/minecraft_client_1.8.8/commits/master";

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
            mc.displayGuiScreen(new GuiFirstUse());
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

    public String getClientChangelog() {
        return clientChangelog;
    }

    public String getClientBackground() {
        return clientBackground;
    }
}
